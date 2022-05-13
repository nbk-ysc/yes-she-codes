(ns yes-she-codes.compras
  (:require [yes-she-codes.csvload :as y.csv]
            [clojure.string :as str])
  )

(defn leitura-de-novas-compras []
  "Le os novos cartoes
  In : nil
  Out: (PersistentVector)
  "
  [["2022-01-02" "260.00" "Dentista" "Saúde" "1234123412341234"]
   ["2022-02-01" "20.00" "Cinema" "Lazer" "1234123412341234"]
   ["2022-01-10" "150.00" "Show" "Lazer" "4321432143214321"]
   ["2022-02-10" "289.99" "Posto de gasolina" "Automóvel" "4321432143214321"]
   ["2022-01-30" "85.00" "Alura" "Educação" "1598159815981598"]
   ["2022-01-31" "350.00" "Tok&Stok" "Casa" "1598159815981598"]
   ["2022-03-04" "250.00" "Hospital" "Saúde" "6655665566556655"]
   ["2022-04-10" "130.00" "Drogaria" "Saúde" "6655665566556655"]
   ["2022-03-10" "100.00" "Show de pagode" "Lazer" "3939393939393939"]
   ["2022-03-11" "25.90" "Dogão" "Alimentação" "3939393939393939"]
   ])

(defn cria-mapa-compra [data valor estabelecimento categoria cartao]
  "Cria a estrutura de uma nova compra
  In : data (String: yyyy-mm-dd) valor (BigDecimal) estabelecimento (String) categoria (String) cartao (Long)
  Out: (hashmap)
  "
  {:data data :valor (bigdec valor) :estabelecimento estabelecimento :categoria categoria :cartao (Long/valueOf cartao)})


(defn nova-compra [lista-compras]
  "Mapeia o novo registro da lista-compras na estrutura do sistema
  In : lista-compras (PersistentVector)
  Out: (hashmap)
  "
  (map (fn [[data valor estabelecimento categoria cartao]]
         (cria-mapa-compra data valor estabelecimento categoria cartao)
         )
       lista-compras))

(defn lista-compras []
  "Obtem as informações das compras em um vetor
  In : nil
  Out: (PersistentVector)"
  (-> (nova-compra (leitura-de-novas-compras))
      vec))

(defn lista-compras [filepath]
  (->> (->> (y.csv/lee-e-formata-csv filepath)
            (map (fn [x] (update x :VALOR #(BigDecimal. %)))))
       (map (fn [x] (update x :CARTÃO #(Long/valueOf (str/replace % #" " "")))))
       vec
       )
  )

(println (lista-compras "compras.csv"))
