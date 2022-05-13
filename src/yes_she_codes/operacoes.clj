(ns yes-she-codes.operacoes
  (:require [yes-she-codes.compras :as y.compras]
            [yes-she-codes.cartao :as y.cartao]
            [clojure.string :as str]
            [java-time :as t])
  )

(defn leitura-dos-cartoes [filepath]
  "Le os registros dos cartoes
  In : nil
  Out: (PersistentVector)
  "
  (y.cartao/lista-cartoes filepath))

(println "---REGISTRO DE CARTOES--- \n" (leitura-dos-cartoes "cartoes.csv"))

(defn leitura-das-compras [filepath]
  "Le os registros das compras
  In : nil
  Out: (PersistentVector)
  "
  (y.compras/lista-compras filepath))

(println "---REGISTRO DE COMPRAS--- \n" (leitura-das-compras "compras.csv"))

(defn total-gasto [lista-compras]
  "Calcula o gasto total segundo a lista-compras enviada
  In : lista-compras (PersistentVector)
  Out: (BigDecimal)"
  (->> lista-compras
       (map :VALOR)
       (apply +)
       ))

(println "---TOTAL GASTO--- \n" (total-gasto (leitura-das-compras "compras.csv")))

(defn busca-compras-por-mes [mes registro]
  "Lista as compras feitas por mes dado um registro
  In : mes(String) registro(PersistentVector)
  Out: (PersistentVector)
  "
  (->> registro
       (filter #(= (t/as (t/local-date (:DATA %)) :month-of-year) (Long/valueOf mes))))
  )
(println "---BUSCA COMPRAS POR MES--- \n" (busca-compras-por-mes "01" (leitura-das-compras "compras.csv")))

(defn busca-compras-por-estabelecimento [estabelecimento registro]
  "Lista as compras feitas por estabelecimento dado um registro
  In : estabelecimento(String) registro(PersistentVector)
  Out: (PersistentVector)
  "
  (->> registro
       (filter #(= (str/upper-case (:ESTABELECIMENTO %)) (str/upper-case estabelecimento))))
  )

(println "---BUSCA COMPRAS POR ESTABELECIMENTO--- \n" (busca-compras-por-estabelecimento "alura" (leitura-das-compras "compras.csv")))

(defn gasto-no-mes [[cartao compras]]
  "Auxilia à função total-gasto-no-mes ao realizar o cálculo das compras por cartao
  In : (PersistentArrayMap)
  Out: (PersistentVector)
  "
  {:CARTÃO      cartao
   :GASTO-TOTAL (total-gasto compras)}
  )

(defn total-gasto-por-mes [mes registro]
  "Calcula o gasto por mes feito por cada cartao do registro
  In : mes(String) registro(PersistentVector)
  Out: (PersistentVector)"
  (->> (busca-compras-por-mes mes registro)
       (group-by :CARTÃO)
       (map gasto-no-mes)
       )
  )
(println "---TOTAL GASTO POR MES--- \n" (total-gasto-por-mes "01" (leitura-das-compras "compras.csv")))

(defn compras-dentro-de-intervalo [min max registro]
  "Lista as compras feitas dentro de um intervalo de valores dado um registrO
    In : min(BigDecimal) max(BigDecimal) registro(PersistentVector)
    Out: (PersistentVector)"
  (->> registro
       (filter #(<= (bigdec min) (:VALOR %) (bigdec max))))
  )

(println "---COMPRAS DENTRO DO INTERVALO--- \n" (compras-dentro-de-intervalo 50 300 (leitura-das-compras "compras.csv")))

(defn gasto-por-categoria [[categoria compras]]
  "Auxilia à função total-gasto-por-categoria ao realizar o cálculo das compras por categoria
  In : (PersistentArrayMap)
  Out: (PersistentVector)
  "
  {:CATEGORIA   categoria
   :GASTO-TOTAL (total-gasto compras)}
  )

(defn total-gasto-por-categoria [registro]
  "Calcula o gasto total por categoria do registro
    In : registro(PersistentVector)
    Out: (PersistentVector)
  "
  (->> registro
       (group-by :CATEGORIA)
       (map gasto-por-categoria)
       )
  )
(println "---TOTAL GASTO POR CATEGORIA--- \n" (total-gasto-por-categoria (leitura-das-compras "compras.csv")))

(defn converte-datas-cartoes [cartoes]
  "Utilizando a API Java-time padroniza-se as datas de validade ao formato yyyy-MM
  In : cartoes(PersistentVector)
  Out: (PersistentVector)
  "
  (->> cartoes
       (map (fn [x] (update x :VALIDADE #(if (not-empty %) (t/year-month (:VALIDADE x)) "error"))))
       vec)
  )
(println "---PADRONIZANDO DATAS DE VALIDADE DE CARTOES--- \n" (converte-datas-cartoes (leitura-dos-cartoes "cartoes.csv")))

(defn converte-datas-compras [compras]
  "Utilizando a API Java-time padroniza-se as datas de compras ao formato yyyy-MM-dd
  In : compras(PersistentVector)
  Out: (PersistentVector)
  "
  (->> compras
       (map (fn [x] (update x :DATA #(if (not-empty %) (t/local-date "yyyy-MM-dd" (:DATA x)) "error"))))
       vec)
  )

(println "---PADRONIZANDO DATAS DAS COMPRAS--- \n" (converte-datas-compras (leitura-das-compras "compras.csv")))