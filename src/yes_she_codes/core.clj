(ns yes-she-codes.core)

(defn lista-registros-clientes []
  [["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
   ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
   ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
   ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]])


(defn lista-registros-cartoes []
  [["1234 1234 1234 1234" "111" "2023-01" "1.000" "000.111.222-33"]
   ["4321 4321 4321 4321" "222" "2024-02" "2.000" "333.444.555-66"]
   ["1598 1598 1598 1598" "333" "2021-03" "3.000" "666.777.888-99"]
   ["6655 6655 6655 6655" "444" "2025-04" "4.000" "666.777.888-99"]
   ["3939 3939 3939 3939" "555" "2026-05" "5.000" "999.123.456-78"]])


(defn lista-registros-compras []
  [["2022-01-01" "129.90" "Outback" "Alimentação" "1234 1234 1234 1234"]
   ["2022-01-02" "260.00" "Dentista" "Saúde" "1234 1234 1234 1234"]
   ["2022-02-01" "20.00" "Cinema" "Lazer" "1234 1234 1234 1234"]
   ["2022-01-10" "150.00" "Show" "Lazer" "4321 4321 4321 4321"]
   ["2022-02-10" "289.99" "Posto de gasolina" "Automóvel" "4321 4321 4321 4321"]
   ["2022-02-20" "79.90" "iFood" "Alimentação" "4321 4321 4321 4321"]
   ["2022-03-01" "85.00" "Alura" "Educação" "4321 4321 4321 4321"]
   ["2022-01-30" "85.00" "Alura" "Educação" "1598 1598 1598 1598"]
   ["2022-01-31" "350.00" "Tok&Stok" "Casa" "1598 1598 1598 1598"]
   ["2022-02-01" "400.00" "Leroy Merlin" "Casa" "1598 1598 1598 1598"]
   ["2022-03-01" "50.00" "Madero" "Alimentação" "6655 6655 6655 6655"]
   ["2022-03-01" "70.00" "Teatro" "Lazer" "6655 6655 6655 6655"]
   ["2022-03-04" "250.00" "Hospital" "Saúde" "6655 6655 6655 6655"]
   ["2022-04-10" "130.00" "Drogaria" "Saúde" "6655 6655 6655 6655"]
   ["2022-03-10" "100.00" "Show de pagode" "Lazer" "3939 3939 3939 3939"]
   ["2022-03-11" "25.90" "Dogão" "Alimentação" "3939 3939 3939 3939"]
   ["2022-03-12" "215.87" "Praia" "Lazer" "3939 3939 3939 3939"]
   ["2022-04-01" "976.88" "Oficina" "Automóvel" "3939 3939 3939 3939"]
   ["2022-04-10" "85.00" "Alura" "Educação" "3939 3939 3939 3939"]])


(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))


(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})


(defn novo-cartao [numero, cvv, validade, limite, cliente]
  {:numero  (str->long numero)
   :cvv     (str->long cvv)
   :email   validade
   :limite  (bigdec limite)
   :cliente cliente})


(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})


(defn lista-clientes []
  (vec (map (fn [[nome, cpf, email]]
              (novo-cliente nome, cpf, email))
            (lista-registros-clientes))))

(defn lista-cartoes []
  (vec (map (fn [[numero, cvv, validade, limite, cliente]]
              (novo-cartao numero, cvv, validade, limite, cliente))
            (lista-registros-cartoes))))


(defn lista-compras []
  (vec (map (fn [[data, valor, estabelecimento, categoria, cartao]]
              (nova-compra data, valor, estabelecimento, categoria, cartao))
            (lista-registros-compras))))


(defn total-gasto [compras]
  (->> compras
       (map :valor)
       (reduce +)))

(defn filtra-compras [predicado, compras]
  (vec (filter predicado compras)))

(defn compras-por-estabelecimento [estabelecimento, compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %))
                  compras))

;(println (lista-clientes))
;(println (lista-cartoes))
;(println (lista-compras))
;(println "Total gasto:" (total-gasto (lista-compras)))
;(println (compras-por-estabelecimento "Alura" (lista-compras)))































