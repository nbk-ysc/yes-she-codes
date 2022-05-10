(ns yes-she-codes.db)

(println "testando")

(def novo-cliente {:nome "Feiticeira Escarlate"
                     :cpf "000.111.222-33"
                     :email "feiticeira.poderosa@vingadoras.com.br"})
;
;(def novo-cartao {:numero 1234 1234 1234 1234
;
;                  :validade (Long)
;                  :limite (BigDecimal)
;                  :cliente (String)})
;
;(def nova-compra {:data (String: yyyy-mm-dd)
;                  :valor (BigDecimal)
;                  :estabelecimento (String)
;                  :categoria (String)
;                  :cartão (Long)})

;novo-cartao, nova-compra

(defn banco-de-dados []
  [novo-cliente])