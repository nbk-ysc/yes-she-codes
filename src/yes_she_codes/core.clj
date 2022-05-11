(ns yes-she-codes.core)

(def clientes [])

(defn novo-cliente
  "retorna um novo cliente com os dados informados"
  [nome cpf email]
  (let [novo-cliente {:nome  nome
                      :cpf   cpf
                      :email email}]
    (def clientes (conj clientes novo-cliente))
    novo-cliente))

(defn lista-clientes
  ([arg1] clientes)
  ([] clientes))



(def cartoes [])

(defn novo-cartao
  "retorna um cartão de crédito"
  [numero cvv validade limite cpf-cliente]
  (let [novo-cartao {:numero   numero
                     :cvv      cvv
                     :validade validade
                     :limite   limite
                     :cliente  cpf-cliente}]
    (def cartoes (conj cartoes novo-cartao))
    novo-cartao))

(defn lista-cartoes
  ([arg1] cartoes)
  ([] cartoes))



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

(defn teste
  ([arg1] arg1)
  ([arg1 arg2] "socorro deus")
  ([] "aaaaaaaaaa")
  )

(comment
  (defn mes-igual
    [compra]
    (if (= (subs (:data compra) 5 7) "02")
      compra))

  (defn lista-compras
    ([mes] (filter mes-igual compras))
    ([] compras)))

(defn lista-compras
  ([arg1] compras)
  ([] compras))

(defn lista-compras-por-mes
  [mes compras]
  (filter #(if (= (subs (:data %) 5 7) mes) true) compras))

(defn total-gasto-errado [compras]
  (let [soma 0]
    (+ soma (:valor compras))))

(defn total-gasto-anonima [compras]
  (reduce #(+ %1 (:valor %2)) 0 compras))

(defn total-gasto [compras]
  (reduce (fn [soma compra]
            (+ soma (:valor compra)))
          0 compras))

;(println (map total-gasto (lista-compras)))
;(println (total-gasto (lista-compras)))

(defn compras-mes
  [mes compras]
  (lista-compras-por-mes mes compras))