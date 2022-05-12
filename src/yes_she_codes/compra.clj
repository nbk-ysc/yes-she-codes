(ns yes-she-codes.compra)

(def compras [])

(def compras [{:data "2022-01-01", :valor 150, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234}
              {:data "2022-01-01", :valor 150, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341235}
              {:data "2022-02-01", :valor 150, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341236}])

(defn nova-compra
  "retorna uma nova compra"
  [data valor estabelecimento categoria numero-cartao]
  (let [nova-compra {:data            data
                     :valor           valor
                     :estabelecimento estabelecimento
                     :categoria       categoria
                     :cartao          numero-cartao}]
    (def compras (conj compras nova-compra))
    nova-compra))

(defn lista-compras
  ([arg1] compras)
  ([] compras))

(defn lista-compras-por-mes
  [mes compras]
  (filter #(if (= (subs (:data %) 5 7) mes) true) compras))

(defn lista-compras-por-estabelecimento
  [estabelecimento compras]
  (filter #(if (= (:estabelecimento %) estabelecimento) true) compras))

(defn total-gasto [compras]
  (reduce (fn [soma compra]
            (+ soma (:valor compra)))
          0 compras))

(defn compras-mes
  [mes compras]
  (lista-compras-por-mes mes compras))

(defn compras-estabelecimento
  [estabelecimento compras]
  (lista-compras-por-estabelecimento estabelecimento compras))

(defn total-gasto-por-mes
  [mes compras]
  (let [lista-compras-mes (lista-compras-por-mes mes compras)]
    (total-gasto lista-compras-mes)))