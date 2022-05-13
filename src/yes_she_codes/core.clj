(ns yes-she-codes.core)


(defn novo-cliente [nome cpf email]
  {
     :nome  nome
     :cpf   cpf
     :email email
     })

(defn novo-cartao [numero cvv validade limite cliente]
  {
    :numero   numero
    :cvv      cvv
    :validade validade
    :limite   limite
    :cliente  cliente
    })

(defn nova-compra [data valor estabelecimento categoria cartao]
  {
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao
   })

(def lista-clientes
  [
   (novo-cliente "Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br")
   (novo-cliente "Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br")
   (novo-cliente "Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com")
   (novo-cliente "Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com")
   ])

(def lista-cartoes
  [
   (novo-cartao 1234123412341234 111 "2023-01" 1000 "000.111.222-33")
   (novo-cartao 4321432143214321 222 "2024/02" 2000 "333.444.555-66")
   (novo-cartao 1598159815981598 333 "2021/03" 3000 "666.777.888-99")
   (novo-cartao 6655665566556655 444 "2025/04" 4000 "666.777.888-99")
   (novo-cartao 3939393939393939 555 "2026/05" 5000 "999.123.456-78")
   ])

(def lista-compras
  [
   (nova-compra "2022-01-02" 260.00 "Dentista" "Saúde" 1234123412341234)
   (nova-compra "2022-02-01" 20.00 "Cinema" "Lazer" 1234123412341234)
   (nova-compra "2022-01-10" 150.00 "Show" "Lazer" 1234123412341234)
   (nova-compra "2022-02-10" 289.99 "Posto de gasolina" "Automóvel" 4321432143214321)
   (nova-compra "2022-02-20" 79.90 "iFood" "Alimentação" 4321432143214321)
   (nova-compra "2022-03-01" 85.00 "Alura" "Educação" 4321432143214321)
   (nova-compra "2022-01-30" 85.00 "Alura" "Educação" 1598159815981598)
   (nova-compra "2022-01-31" 350.00 "Tok&Stok" "Casa" 1598159815981598)
   (nova-compra "2022-02-01" 400.00 "Leroy Merlin" "Casa" 1598159815981598)
   (nova-compra "2022-03-01" 50.00 "Madero" "Alimentação" 6655665566556655)
   (nova-compra "2022-03-01" 70.00 "Teatro" "Lazer" 6655665566556655)
   (nova-compra "2022-03-04" 250.00 "Hospital" "Saúde" 6655665566556655)
   (nova-compra "2022-04-10" 130.00 "Drogaria" "Saúde" 6655665566556655)
   ])

(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +))
  )

(defn pega-data [compra]
  (->> compra
       (:data)))

(defn pega-mes [data]
  (str (get data 5) (get data 6)))

(defn compara-mes-compra [mes compra]
  (= (read-string (pega-mes (pega-data compra))) mes))

(defn lista-compras-por-mes [mes lista-compras]
  (filter (fn [compra] (compara-mes-compra mes compra)) lista-compras))

(defn compara-estabelecimento [estabelecimento compra]
  (= estabelecimento (:estabelecimento compra)))

(defn lista-compras-estabelecimento [estabelecimento lista-compras]
  (filter (fn [compra] (compara-estabelecimento estabelecimento compra)) lista-compras))

(defn total-gasto-no-mes [mes lista-compras]
  (def lista (lista-compras-por-mes mes lista-compras))
  (total-gasto lista))

(defn esta-entre-os-valores? [valor-maximo valor-minimo compra]
  (and (> (:valor compra) valor-minimo)
       (< (:valor compra) valor-maximo))  )

(defn compras-dentro-do-intervalo [valor-maximo valor-minimo lista-compras]
  (filter (fn [compra] (esta-entre-os-valores? valor-maximo valor-minimo compra)) lista-compras))





(println (compras-dentro-do-intervalo 200 50 lista-compras))
(println (total-gasto-no-mes 02 lista-compras))
(println (lista-compras-por-mes 1 lista-compras))
(println (lista-compras-estabelecimento "Alura" lista-compras))












