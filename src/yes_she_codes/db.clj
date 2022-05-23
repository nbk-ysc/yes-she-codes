(ns yes-she-codes.db
  (:require [clojure.string :as str]))


(defn processa-csv [arquivo funcao-mapeamento]
  (->> (slurp arquivo)
       str/split-lines
       rest
       (map #(str/split % #","))
       (mapv funcao-mapeamento)))


(def clientes [
               {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}
               {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}
               {:nome "Hermione Granger" :cpf "666.777.888-99" :email "hermione.salvadora@hogwarts.com"}
               {:nome "Daenerys Targaryen" :cpf "999.123.456-78" :email "mae.dos.dragoes@got.com"}])

(def cartoes [
              {:numero 1234123412341234 :cvv 111 :validade "2023-01" :limite 1000 :cliente "000.111.222-33"}
              {:numero 4321432143214321 :cvv 222 :validade "2024-02" :limite 2000 :cliente "333.444.555-66"}
              {:numero 1598159815981598 :cvv 333 :validade "2021-03" :limite 3000 :cliente "666.777.888-99"}
              {:numero 6655665566556655 :cvv 444 :validade "2025-04" :limite 4000 :cliente "666.777.888-99"}
              {:numero 3939393939393939 :cvv 555 :validade "2026-05" :limite 5000 :cliente "999.123.456-78"}])

(def compras [
              {:data "2022-01-01" :valor 129.90 :estabelecimento "Outback" :categoria "Alimentação" :cartao 1234123412341234}
              {:data "2022-01-02" :valor 260.00 :estabelecimento "Dentista" :categoria "Saúde" :cartao 1234123412341234}
              {:data "2022-02-01" :valor 20.00 :estabelecimento "Cinema" :categoria "Lazer" :cartao 1234123412341234}
              {:data "2022-01-10" :valor 150.00 :estabelecimento "Show" :categoria "Lazer" :cartao 4321432143214321}
              {:data "2022-02-10" :valor 289.99 :estabelecimento "Posto de gasolina" :categoria "Automóvel" :cartao 4321432143214321}
              {:data "2022-02-20" :valor 79.90 :estabelecimento "iFood" :categoria "Alimentação" :cartao 4321432143214321}
              {:data "2022-03-01" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 4321432143214321}
              {:data "2022-01-30" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 1598159815981598}
              ])

