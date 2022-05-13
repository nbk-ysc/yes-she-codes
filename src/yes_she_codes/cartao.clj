(ns yes-she-codes.cartao
  (:require [yes-she-codes.csvload :as y.csv]
            [clojure.string :as str])
  )

(defn leitura-de-novos-cartoes []
  "Le os novos cartoes
  In : nil
  Out: (PersistentVector)
  "
  [["1234123412341234" "111" "2023-01" "1000" "000.111.222-33"]
   ["4321432143214321" "222" "2024-02" "2000" "000.111.222-33"]
   ["1598159815981598" "333" "2021-03" "3000" "666.777.888-99"]
   ["6655665566556655" "444" "2025-04" "4000" "666.777.888-99"]
   ["3939393939393939" "555" "2026-05" "5000" "999.123.456-78"]])

(defn cria-mapa-cartao [numero cvv validade limite cliente]
  "Cria a estrutura do novo cartao
  In : numero (Long) cvv (Long) validade (String: yyyy-mm) limite (BigDecimal) cliente (String)
  Out: (hashmap)
  "
  {:numero (Long/valueOf numero) :cvv (Long/valueOf cvv) :validade validade :limite (bigdec limite) :cpf cliente}
  )

(defn novo-cartao [lista-cartoes]
  "Mapeia o novo registro da lista-cartoes na estrutura do sistema
  In : lista-cartoes (PersistentVector)
  Out: (hashmap)
  "
  (map (fn [[numero cvv validade limite cliente]]
         (cria-mapa-cartao numero cvv validade limite cliente)
         )
       lista-cartoes))

(defn lista-cartoes []
  "Obtem as informações dos cartoes em um vetor
  In : nil
  Out: (PersistentVector)"
  (-> (novo-cartao (leitura-de-novos-cartoes))
      vec))

(defn lista-cartoes [filepath]
  (->> (->> (y.csv/lee-e-formata-csv filepath)
            (map (fn [x] (update x :LIMITE #(BigDecimal. %)))))
       (map (fn [x] (update x :CVV #(Long/valueOf %))
              (update x :NÚMERO #(Long/valueOf (str/replace % #" " "")))))
       vec)
  )

(println (lista-cartoes "cartoes.csv"))