(ns yes-she-codes.logic
  (:require [yes-she-codes.model :as ysc.model]))

(defn lista-clientes
  "Retorna um vetor de clientes."
  []
  [(ysc.model/novo-cliente "Feiticeira Escalarte", "000.111.222-33", "feiticeira.poderosa@vingadoras.com.br")
   (ysc.model/novo-cliente "Viúva Negra", "333.444.555-66", "viuva.casca.grossa@vingadoras.com.br")
   (ysc.model/novo-cliente "Hermione Granger", "666.777.888-99", "hermione.salvadora@hogwarts.com")
   (ysc.model/novo-cliente "Daenerys Targaryen", "999.123.456-78", "mae.dos.dragoes@got.com")])

(defn lista-cartoes
  "Retorna um vetor de cartões de crédito."
  []
  [(ysc.model/novo-cartao 1234123412341234, 111, "2023-01", 1.000, "000.111.222-33")
   (ysc.model/novo-cartao 4321432143214321, 222, "2024-02", 2.000, "333.444.555-66")
   (ysc.model/novo-cartao 1598159815981598, 333, "2021-03", 3.000, "666.777.888-99")
   (ysc.model/novo-cartao 6655665566556655, 444, "2025-04", 4.000, "666.777.888-99")
   (ysc.model/novo-cartao 3939393939393939, 555, "2026-05", 5.000, "999.123.456-78")])

(defn lista-compras
  "Retorna um vetor de compras."
  []
  [(ysc.model/nova-compra "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234)
   (ysc.model/nova-compra "2022-01-02", 260.00, "Dentista", "Saúde", 1234123412341234)
   (ysc.model/nova-compra "2022-02-01", 20.00, "Cinema", "Lazer", 1234123412341234)
   (ysc.model/nova-compra "2022-01-10", 150.00, "Show", "Lazer", 4321432143214321)
   (ysc.model/nova-compra "2022-02-10", 289.99, "Posto de gasolina", "Automóvel", 4321432143214321)
   (ysc.model/nova-compra "2022-02-20", 79.90, "iFood", "Alimentação", 4321432143214321)
   (ysc.model/nova-compra "2022-03-01", 85.00, "Alura", "Educação", 4321432143214321)
   (ysc.model/nova-compra "2022-01-30", 85.00, "Alura", "Educação", 1598159815981598)
   (ysc.model/nova-compra "2022-01-31", 350.00, "Tok&Stok", "Casa", 1598159815981598)
   (ysc.model/nova-compra "2022-02-01", 400.00, "Leroy Merlin", "Casa", 1598159815981598)
   (ysc.model/nova-compra "2022-03-01", 50.00, "Madero", "Alimentação", 6655665566556655)
   (ysc.model/nova-compra "2022-03-01", 70.00, "Teatro", "Lazer", 6655665566556655)
   (ysc.model/nova-compra "2022-03-04", 250.00, "Hospital", "Saúde", 6655665566556655)
   (ysc.model/nova-compra "2022-04-10", 130.00, "Drogaria", "Saúde", 6655665566556655)
   (ysc.model/nova-compra "2022-03-10", 100.00, "Show de pagode", "Lazer", 3939393939393939)
   (ysc.model/nova-compra "2022-03-11", 25.90, "Dogão", "Alimentação", 3939393939393939)
   (ysc.model/nova-compra "2022-03-12", 215.87, "Praia", "Lazer", 3939393939393939)
   (ysc.model/nova-compra "2022-04-01", 976.88, "Oficina", "Automóvel", 3939393939393939)
   (ysc.model/nova-compra "2022-04-10", 85.00, "Alura", "Educação", 3939393939393939)])

(defn total-gasto
  "Recebe uma lista de compras e retorna a soma dos valores gastos."
  [compras]
  (reduce + (map :valor compras)))

; (println (total-gasto [{:valor 100.00},
;                        {:valor 250.00},
;                        {:valor 400.00}]))

(defn compra-feita-no-estabelecimento?
  [compra estabelecimento]
  (= (get compra :estabelecimento) estabelecimento))

(defn compras-por-estabelecimento
  "Recebe uma lista de compras e um estabelecimento e retorna uma lista de compras feitas somente naquele estabelecimento."
  [compras estabelecimento]
  (vec (filter #(compra-feita-no-estabelecimento? % estabelecimento) compras)))

(defn compra-feita-no-mes?
  [compra mes]
  (= (subs (get compra :data) 5 7) mes))

(defn compras-por-mes
  "Recebe uma lista de compras e um mês e retorna uma lista de compras feitas somente naquele mês."
  [compras mes]
  (vec (filter #(compra-feita-no-mes? % mes) compras)))

(defn compra-feita-no-cartao?
  [compra numero-cartao]
  (= (get compra :cartao) numero-cartao))

(defn compras-por-cartao
  "Recebe uma lista de compras e um número de cartão e retorna uma lista de compras feitas somente naquele cartão."
  [compras numero-cartao]
  (vec (filter #(compra-feita-no-cartao? % numero-cartao) compras)))

(defn total-gasto-no-mes
  "Calcula a soma dos valores gastos em um determinado cartão em um mês."
  [compras numero-cartao mes]
  (let [compras-do-cartao (compras-por-cartao compras numero-cartao)]
    (compras-por-mes compras-do-cartao mes)))

(defn esta-no-intervalo?
  [compra min max]
  (let [valor (get compra :valor)]
    (and (<= min valor) (<= valor max))))                   ; min <= valor <= max

(defn compras-por-intervalo-de-valor
  "Retorna as compras que estão dentro de um intervalo de valor máximo e mínimo."
  [compras valor-min valor-max]
  (vec (filter #(esta-no-intervalo? % valor-min valor-max) compras)))

(defn compra-da-categoria?
  [compra categoria]
  (= (get compra :categoria) categoria))

(defn compras-por-categoria
  "Recebe uma lista de compras e uma categoria e retorna uma lista de compras feitas somente naquela categoria."
  [compras categoria]
  (vec (filter #(compra-da-categoria? % categoria) compras)))

(defn total-gasto-por-categoria
  "Calcula a soma dos valores gastos em uma única categoria."
  [compras categoria]
  (let [compras-da-categoria (compras-por-categoria compras categoria)]
    (reduce + (map :valor compras-da-categoria))))

; (println (ysc.logic/total-gasto-por-categoria [{:categoria "Educação" :valor 700.00},
;                                                {:categoria "Saúde" :valor 1500.00},
;                                                {:categoria "Educação" :valor 50.00},
;                                                {:categoria "Alimentação" :valor 100.00},
;                                                {:categoria "Alimentação" :valor 89.90}] "Saúde"))