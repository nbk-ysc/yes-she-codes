(ns yes-she-codes.cartao_db)

;1234 1234 1234 1234	111	2023-01	1.000	000.111.222-33
;4321 4321 4321 4321	222	2024-02	2.000	333.444.555-66
;1598 1598 1598 1598	333	2021-03	3.000	666.777.888-99
;6655 6655 6655 6655	444	2025-04	4.000	666.777.888-99
;3939 3939 3939 3939	555	2026-05	5.000	999.123.456-78

;{:nome Jaiane, :cpf 11122233445577, :email jaiane@email.com, :cartao {:numero 1231458272, :cvv 432, :validade 12, :limite 10000}}



(def cartao1 {:numero   "1234 1234 1234 1234"
              :cvv      "111"
              :validade "2023-01"}
              :limite 1.000
              :cliente "000.111.222-33"
  )
(def cartao2 {
               :nome  "Viúva Negra"
               :cpf   "333.444.555-66"
               :email "viuva.casca.grossa@vingadoras.com.br"
               })
(def cartao3 {
               :nome  "Hermione Granger"
               :cpf   "666.777.888-99"
               :email "hermione.salvadora@hogwarts.com"})

(def cartao4 {:nome  "Daenerys Targaryen"
               :cpf   "999.123.456-78"
               :email "mae.dos.dragoes@got.com"})


(defn todos-os-clientes []
  [cartao1, cartao2, cartao3,cartao4])