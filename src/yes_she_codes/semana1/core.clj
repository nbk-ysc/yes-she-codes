(ns yes-she-codes.semana1.core
  (:use clojure.pprint))

(defn busca-registros-de-clientes []
  [["Feiticeira Escarlate", "000.111.222-33", "feiticeira.poderosa@vingadoras.com.br"]
   ["Viúva Negra", "333.444.555-66", "viuva.casca.grossa@vingadoras.com.br"]
   ["Hermione Granger", "666.777.888-99", "hermione.salvadora@hogwarts.com"]
   ["Daenerys Targaryen", "999.123.456-78", "mae.dos.dragoes@got.com"]])

(defn massa-de-dados-cartao []
  [[1234123412341234 111 "2023-01" 1.000M "000.111.222-33"]
   [4321432143214321 222 "2024-02" 2.000M "333.444.555-66"]
   [1598159815981598 333 "2021-03" 3.000M "666.777.888-99"]
   [6655665566556655 444 "2025-04" 4.000M "666.777.888-99"]
   [3939393939393939 555 "2026-05" 5.000M "999.123.456-78"]])

(defn massa-de-dados-compras []
  [
   ["2022-01-01" 129.90M "Outback" "Alimentação" 1234123412341234]
   ["2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234]
   ["2022-02-01" 20.00M "Cinema" "Lazer" 1234123412341234]
   ["2022-01-10" 150.00M "Show" "Lazer" 4321432143214321]
   ["2022-02-10" 289.99M "Posto de gasolina" "Automóvel" 4321432143214321]
   ["2022-02-20" 79.90M "iFood" "Alimentação" 4321432143214321]
   ["2022-03-01" 85.00M "Alura" "Educação" 4321432143214321]
   ["2022-01-30" 85.00M "Alura" "Educação" 1598159815981598]
   ["2022-01-31" 350.00M "Tok&Stok" "Casa" 1598159815981598]
   ["2022-02-01" 400.00M "Leroy Merlin" "Casa" 1598159815981598]
   ["2022-03-01" 50.00M "Madero" "Alimentação" 6655665566556655]
   ["2022-03-01" 70.00M "Teatro" "Lazer" 6655665566556655]
   ["2022-03-04" 250.00M "Hospital" "Saúde" 6655665566556655]
   ["2022-04-10" 130.00M "Drogaria" "Saúde" 6655665566556655]
   ["2022-03-10" 100.00M "Show de pagode" "Lazer" 3939393939393939]
   ["2022-03-11" 25.90M "Dogão" "Alimentação" 3939393939393939]
   ["2022-03-12" 215.87M "Praia" "Lazez" 3939393939393939]
   ["2022-04-01" 976.88M "Oficina" "Automóvel" 3939393939393939]
   ["2022-04-10" 85.00M "Alura" "Educação" 3939393939393939]])


(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn transforma-clientes [registros]
  (vec (map (fn [[nome cpf email]]
              (novo-cliente nome, cpf, email))
            registros)))

(def clientes (transforma-clientes (busca-registros-de-clientes)))


(defn cria-cartao
  [numero cvc validade limite cliente-cpf]
  {:numero      numero,
   :cvc         cvc,
   :validade    validade,
   :limite      (bigdec limite),
   :cliente-cpf cliente-cpf})

(defn transforma-cartao [registros]
  (vec (map (fn [[numero cvc validade limite cliente-cpf]]
              (cria-cartao numero cvc validade limite cliente-cpf))
            registros)))

(def cartaoes (transforma-cartao (massa-de-dados-cartao)))


(defn cria-compra [data valor estabeleciemnto categoria cartao]
  {
   :data            data,
   :valor           (bigdec valor),
   :estabelecimento estabeleciemnto,
   :categoria       categoria,
   :cartao          cartao})


(defn transforma-compra [registros]
  (vec (map (fn [[data valor estabeleciemnto categoria cartao]]
              (cria-compra data valor estabeleciemnto categoria cartao))
            registros)))

(def compras (transforma-compra (massa-de-dados-compras)))

(defn listar-clientes []
  (map println clientes))

(defn listar-compra []
  (map println compras))

(defn listar-cartoes []
  (map println cartaoes))

;(listar-compra)

;Calcular valor de compras de um cartao
(defn total-gasto [compras]
(reduce + (map :valor compras)))

(defn criando-novo-map-com-total-de-gastos
  [[chave valor]]
  {:cartao       chave,
   :total-gastos (total-gasto valor)})

(println
  (->> compras
       (group-by :cartao ,,,,)
       (map criando-novo-map-com-total-de-gastos  ,,,,)))


;Buscar compras por mes
(defn compara-string-data [mes data]
  (= mes (subs data 5 7)))

(defn buscar-compras-mes-filter [mes compras]
  (filter (fn [compra] (compara-string-data mes (:data compra))) compras))
(println (buscar-compras-mes-filter "02" compras))

;Buscando compras por estabelecimento
(defn filtrando-estabelecimento-upper [estabelecimento compra]
  (= (clojure.string/upper-case estabelecimento) (clojure.string/upper-case compra) )
  )

(defn compras-por-estabelecimento [estabelecimento compras]
  (filter (fn [compra] (filtrando-estabelecimento-upper estabelecimento (:estabelecimento compra)))compras )
  )

;(println (compras-por-estabelecimento "Outback" compras))

;Calculando total de uma fatura por mes
(defn total-gasto-no-mes [mes compras]
  (total-gasto (buscar-compras-mes-filter mes compras)))

;(println (total-gasto-no-mes "02" compras))
































