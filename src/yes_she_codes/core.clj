(ns yes-she-codes.core
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.cliente :as y.cliente]
            [yes-she-codes.cartao :as y.cartao]
            [yes-she-codes.compra :as y.compra]
            [clojure.string :as str]))

(def lista-compras y.db/compras)

;CALCULAR O TOTAL GASTO EM COMPRAS DE UMA LISTA DE COMPRAS
(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +)))

;(println "Total Gasto R$" (total-gasto lista-compras))

;BUSCAR COMPRAS POR MES
(defn get-moth [mes]
  (get (str/split mes #"-") 1))

(defn lista-compras-por-mes [mes lista-compras]
  ;(println "Todas as compras no mês" mes)
  (filter #(= (get-moth (:data %)) mes) lista-compras))

;(pprint (lista-compras-por-mes "02" lista-compras))


;BUSCAR COMPRAS POR ESTABELECIMENTO
(defn lista-compras-por-estabelecimento [estabelecimento lista-compras]
  (println "Todas as compras n@" estabelecimento)
  (-> :estabelecimento
      (group-by lista-compras)
      (get estabelecimento)))

;(pprint (lista-compras-por-estabelecimento "Cinema" lista-compras))

;CALCULAR O TOTAL DA FATURA DE UM MÊS
(defn get-extrato-mes [cartao mes lista-compras]
  (->> lista-compras
       (lista-compras-por-mes mes)
       (filter #(= (:cartao %) cartao))))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (println "Todas as compras do cartão" cartao "no mês" mes ": R$ ")
  (->> lista-compras
       (get-extrato-mes cartao mes)
       (map :valor)
       (reduce +)))

;(println (total-gasto-no-mes 1234123412341234 "01" lista-compras))

;FILTRAR COMPRAS NUM INTERVALO DE VALORES
(defn filtro-maximo-minimo [lista-compras valormax valormin]
  (println "Compras realizadas entre R$" valormin "e R$" valormax)
  (->> lista-compras
       (filter #(and (> (:valor %) valormin) (< (:valor %) valormax)))))

;(pprint (filtro-maximo-minimo lista-compras 130.0 84.0))

;AGRUPAR GASTOS POR CATEGORIA
(defn total-categoria [[categoria compras]]
  (->> compras
       (map :valor)
       (reduce +)
       (println categoria "R$")))

(defn gastos-por-categoria [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map total-categoria)))

;(gastos-por-categoria lista-compras)

;LER ARQUIVO
(defn processa-csv [arquivo funcao-mapeamento]
  (->> (slurp arquivo)
       str/split-lines
       rest
       (map #(str/split % #","))
       (mapv funcao-mapeamento)))

(defn lista-clientes []
  (pprint (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                               (y.cliente/novo-cliente nome cpf email)))))
;(lista-clientes)

(defn lista-cartoes []
  (pprint (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                              (y.cartao/novo-cartao numero cvv validade limite cliente)))))
;(lista-cartoes)

(defn lista-compras []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                      (y.compra/nova-compra data valor estabelecimento categoria cartao))))

;SEMANA 2

;Definir átomo como banco de dados em memória
(def repositorio-de-compras (atom {}))

;Criar Record para Compra
(defrecord Compra [^Long id
                   ^String data
                   ^BigDecimal valor
                   ^String estabelecimento
                   ^String categoria
                   ^Long cartao])

;(pprint (Compra. nil "2022-01-01" 10M "Alura" "Educação" 4321432143214321))
;(pprint compra-exemplo)

;DEFINE UMA COMPRA
(def compra-exemplo (Compra. nil "2022-01-01" 100 "Alura" "Educação" 4321432143214321))
(def compra-exemplo2 (Compra. nil "2022-01-01" 300 "Hamburguer" "Alimentação" 4321432143214321))

(def lista-de-compras (lista-compras))

;FUNÇÃO INSERIR COMPRA
(defn insere-compra [lista-de-compras compra]
  (conj lista-de-compras (assoc compra :id (count lista-de-compras))))

;(pprint (insere-compra lista-de-compras compra-exemplo))

;FUNÇÃO LISTAR COMPRA NO ÁTOMO
(defn lista-compras! [repositorio-de-compras]
  (pprint @repositorio-de-compras))

;(lista-compras! repositorio-de-compras)

;STOP

;FUNÇÃO INSERIR COMPRA NO ÁTOMO

(defn insere-compra! [lista-de-compras compra]
  (swap! lista-de-compras insere-compra compra)
  )

;(lista-compras! repositorio-de-compras)
(insere-compra! repositorio-de-compras compra-exemplo)
(insere-compra! repositorio-de-compras compra-exemplo2)
(lista-compras! repositorio-de-compras)

;FUNÇÃO PESQUISAR COMPRA POR ID
(defn pesquisa-compra-por-id [id compras]
  (let [compra-id (-> :id
                      (group-by compras)
                      (get id))]
    (if compra-id
      (map->Compra compra-id))))

(defn pesquisa-compra-por-id2 [id compras]
  (map->Compra (filter (fn [compra]
                         (= id  (:id compra))) compras)))

;(pprint (pesquisa-compra-por-id 19 (insere-compra lista-de-compras compra-exemplo)))
;(pprint (pesquisa-compra-por-id2 19 (insere-compra lista-de-compras compra)))

;FUNÇÃO PESQUISAR COMPRA POR ATOMO
(defn pesquisa-compra-por-id! [id repositorio-de-compras]
  (pesquisa-compra-por-id id @repositorio-de-compras)
  )

;(insere-compra! repositorio-de-compras compra)
;(lista-compras! repositorio-de-compras)
;(pprint (pesquisa-compra-por-id! 0 repositorio-de-compras))



;FUNÇÃO EXCLUIR COMPRA
(defn exclui-compra [id lista-compras]
  ;(pprint lista-compras)
  (remove (pesquisa-compra-por-id2 id lista-compras) lista-compras)
  )

;(pprint (insere-compra lista-de-compras compra-exemplo))
;(pprint (exclui-compra 19 (insere-compra lista-de-compras compra-exemplo)))
;(pprint (pesquisa-compra-por-id 19 (insere-compra lista-de-compras compra-exemplo)))

(defn exclui-compra! [id lista-compras]
  (swap! lista-compras exclui-compra id))


