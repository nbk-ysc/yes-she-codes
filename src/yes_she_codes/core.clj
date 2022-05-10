(ns yes-she-codes.core
  (:use clojure.pprint))

(defn novo-cliente
  [nome, cpf, email]

  {:nome nome :cpf cpf :email email})

(defn novo-cartao
  [numero, cvv, validade, limite, cliente ]

  {:numero numero :cvv cvv :validade validade :limite limite :cliente (cliente :cpf)})

(defn nova-compra
  [data, valor, estabalecimento, categoria, cartao]

  {:data data :valor valor :estabelecimento estabalecimento :categoria categoria :cartao (cartao :numero)})


(def Feiticeira (novo-cliente "Feiticeira Escarlate" "000.111.22-33" "feiticeira.poderosa@vingadoras.com.br"))

(def Viuva (novo-cliente "Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"))

(def Hermione (novo-cliente "Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"))

(def Daenerys (novo-cliente "Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"))

(defn lista-clientes
  []
  [Feiticeira, Viuva, Hermione, Daenerys])


(def cartao-feiticeira (novo-cartao 1234123412341234 111 "2023-01" 1000 Feiticeira))

(def cartao-viuva (novo-cartao 4321432143214321 222 "2024-02" 2000 Viuva))

(def cartao-hermione (novo-cartao 1598159815981598 333 "2021-03" 3000 Hermione))

(def cartao-hermione2 (novo-cartao 6655665566556655 444 "2025-04" 4000 Hermione))

(def cartao-daenerys (novo-cartao 3939393939393939 555 "2026-05" 5000 Daenerys))

(defn lista-cartoes
  []
  [cartao-feiticeira, cartao-viuva, cartao-hermione, cartao-hermione2, cartao-daenerys])


(def compra1 (nova-compra "2022-01-01" 129.90 "Outback" "Alimentação" cartao-feiticeira))
(def compra2 (nova-compra "2022-01-02" 260.00 "Dentista" "Saúde" cartao-feiticeira))
(def compra3 (nova-compra "2022-02-01" 20.00 "Cinema" "Lazer" cartao-feiticeira))

(def compra4 (nova-compra "2022-01-10" 150.00 "Show" "Lazer" cartao-viuva))
(def compra5 (nova-compra "2022-02-10" 289.99 "Posto de gasolina" "Automóvel" cartao-viuva))
(def compra6 (nova-compra "2022-02-20" 79.90 "iFood" "Alimentação" cartao-viuva))
(def compra7 (nova-compra "2022-03-01" 85.00 "Alura" "Educação" cartao-viuva))


(def compra8 (nova-compra "2022-01-30" 85.00 "Alura" "Educação" cartao-hermione))
(def compra9 (nova-compra "2022-01-31" 350.00 "Tok&Stok" "Casa" cartao-hermione))
(def compra10 (nova-compra "2022-02-01" 400.00 "Leroy Merlin" "Casa" cartao-hermione))

(def compra11 (nova-compra "2022-03-01" 50.00 "Madero" "Alimentação" cartao-hermione2))
(def compra12 (nova-compra "2022-03-01" 70.00 "Teatro" "Lazer" cartao-hermione2))
(def compra13 (nova-compra "2022-03-04" 250.00 "Hospital" "Saúde" cartao-hermione2))
(def compra14 (nova-compra "2022-04-10" 130.00 "Drogaria" "Saúde" cartao-hermione2))

(def compra15 (nova-compra "2022-03-10" 100.00 "Show de pagode" "Lazer" cartao-daenerys))
(def compra16 (nova-compra "2022-03-11" 25.90 "Dogão" "Alimentação" cartao-daenerys))
(def compra17 (nova-compra "2022-03-12" 215.87 "Praia" "Lazer" cartao-daenerys))
(def compra18 (nova-compra "2022-04-01" 976.88 "Oficina" "Automóvel" cartao-daenerys))
(def compra19 (nova-compra "2022-04-10" 85.00 "Alura" "Educação" cartao-daenerys))


(defn lista-compras
  []
  [compra1, compra2, compra3, compra4, compra5,compra6,compra7,compra8,compra9,compra10,compra11,
           compra12,compra13,compra14,compra15,compra16,compra17,compra18,compra19])


;assumindo que a função receberia uma lista de hashmaps de compra como os acima
(defn total-gasto
  [lista]
  (reduce + (map :valor lista)))

(def minhas-compras (lista-compras))

(defn buscar-por-estabelecimento
  [estabelecimento lista]
  (filter #(= estabelecimento (:estabelecimento %)) lista))

(defn buscar-por-mes
  [mes lista]
  (let [nova-lista (map #(assoc % :mes (subs (% :data) 5 7)) lista)]
    (filter #(= mes (:mes  %)) nova-lista)))

(defn total-gasto-no-mes
  [mes lista]
  (let [nova-lista (buscar-por-mes mes lista)]
    (reduce + (map :valor nova-lista))))

(defn filtro-intervalo
  [valor-minimo valor-maximo lista]
  (filter #(and (<= valor-minimo (:valor %)) (>= valor-maximo (:valor %))) lista))

(defn gastos-por-categoria
  [lista]
  (map
    (fn [[grp-key values]]
      {:categoria grp-key
       :total (reduce + (map :valor values))})
    (group-by :categoria lista)))



































