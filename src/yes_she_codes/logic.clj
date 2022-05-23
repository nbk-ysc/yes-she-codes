(ns yes_she_codes.logic
  (:require [yes-she-codes.db :as y.db])
  [java-time :as t])

(defn str->long
  [string]
  (Long/parseLong (clojure.string/replace string #" " "")))

(defn novo-cliente
  [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

;(defn novo-cartao
;  [numero cvv validade limite cliente]
;  {:numero   (str->long numero)
;   :cvv      (str->long cvv)
;   :validade (t/year-month validade)
;   :limite   (bigdec limite)
;   :cliente  cliente})

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero   (str->long numero)
   :cvv      (str->long cvv)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cliente})

;(defn nova-compra
;  [data valor estabelecimento categoria cartao]
;  {:data            (t/local-date data)
;   :valor           (bigdec valor)
;   :estabelecimento estabelecimento
;   :categoria       categoria
;   :cartao          (str->long cartao)})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (mapv funcao-mapeamento)))

;(defn lista-clientes
;  []
;  (vec (map (fn [[nome cpf email]]
;              (novo-cliente nome cpf email))
;            (y.db/lista-registros-clientes))))

(defn lista-clientes
  []
  (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                       (novo-cliente nome cpf email))))

;(defn lista-cartoes
;  []
;  (vec (map (fn [[numero cvv validade limite cliente]]
;              (novo-cartao numero cvv validade limite cliente))
;            (y.db/lista-registros-cartoes))))

(defn lista-cartoes
  []
  (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                      (novo-cartao numero cvv validade limite cliente))))

;(defn lista-compras
;  []
;  (vec (map (fn [[data valor estabelecimento categoria cartao]]
;              (nova-compra data valor estabelecimento categoria cartao))
;            (y.db/lista-registros-compras))))

(defn lista-compras
  []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                      (nova-compra data valor estabelecimento categoria cartao))))

(defn total-gasto
  [compras]
  (reduce + (map :valor compras)))

(defn mes-da-data
  [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

;(defn mes-da-data
;  [data]
;  (.getValue (t/month data)))

(defn filtra-compras
  [predicado compras]
  (vec (filter predicado compras)))

(defn filtra-compras-no-mes
  [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %))) compras))

(defn filtra-compras-no-estabelecimento
  [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %)) compras))

(defn total-gasto-no-mes
  [mes compras]
  (total-gasto (filtra-compras-no-mes mes compras)))

; (def total-gasto-no-mes (comp total-gasto filtra-compras-no-mes))

(defn filtra-compras-por-valor
  [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo)) compras))

(defn agrupa-compras-por-categoria
  [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))

(println (lista-clientes))
(println (lista-cartoes))
(println (lista-compras))
(println (total-gasto (lista-compras)))
(println (mes-da-data "2021-04-05"))
(println (filtra-compras-no-mes "04" (lista-compras)))
(println (filtra-compras-no-estabelecimento "Outback" (lista-compras)))
(println (total-gasto-no-mes "04" (lista-compras)))
(println (filtra-compras-por-valor 100 1000 (lista-compras)))
(println (agrupa-compras-por-categoria (lista-compras)))