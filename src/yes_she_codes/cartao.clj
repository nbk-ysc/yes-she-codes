(ns yes-she-codes.cartao)

(def dados_cartoes [[1234123412341234 111 "2023-01" 1.000 "000.111.222-33"]
                   [4321432143214321 222 "2024-02" 2.000 "333.444.555-66"]
                   [1598159815981598 333 "2021-03" 3.000 "666.777.888-99"]
                   [6655665566556655 444 "2025-04" 4.000 "666.777.888-99"]
                   [3939393939393939 555 "2026-05" 5.000 "999.123.456-78"]])

(defn novo-cartao
  "criar uma estrutura de cartao"
  [numero cvv validade limite cliente]
  {:Numero numero
   :CVV cvv
   :Validade validade
   :Limite limite
   :Cliente cliente})

(defn adiciona-cartao
  "Adicionar um novo cliente"
  [item]
  (novo-cartao (get item 0 "") (get item 1 "") (get item 2 "") (get item 3 "") (get item 4 "")))

(defn lista-cartaooes
  "Lista os clientes"
  [dados]
  (map adiciona-cartao dados))