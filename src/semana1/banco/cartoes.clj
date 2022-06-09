(ns semana1.banco.cartoes)

(def cartao1 {:numero   1234 1234 1234 1234
              :cvv      111
              :validade "2023-01"
              :limite   1.000
              :cliente  "000.111.222-33"})                  ;ou (:cpf b.clientes/cliente1)

(def cartao2 {:numero   4321 4321 4321 4321
              :cvv      222
              :validade "2024-02"
              :limite   2.000
              :cliente  "333.444.555-66"})

(def cartao3 {:numero   1598 1598 1598 1598
              :cvv      333
              :validade "2021-03"
              :limite   3.000
              :cliente  "666.777.888-99"})

(def cartao4 {:numero   6655 6655 6655 6655
              :cvv      444
              :validade "2025-04"
              :limite   4.000
              :cliente  "666.777.888-99"})

(def cartao5 {:numero   3939 3939 3939 3939
              :cvv      555
              :validade "2026-05"
              :limite   5.000
              :cliente  "999.123.456-78"})


(defn todos-os-cartoes []
  [cartao1, cartao2, cartao3, cartao4, cartao5])