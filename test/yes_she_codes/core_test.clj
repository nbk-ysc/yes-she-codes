(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.core :as y.core]
            [yes_she_codes.compras :as y.compras]))


(def lista-compras
  [["2022-01-01"	129.90 "Outback" "Alimentação" "1234123412341234"]
   ["2022-01-02"	260.00 "Dentista" "Saúde" "1234123412341234"]
   ["2022-02-01"	20.00	 "Cinema" "Lazer" "1234123412341234"]
   ["2022-01-10"	150.00 "Show" "Lazer" "4321432143214321"]
   ["2022-02-10"	289.99 "Posto de gasolina" "Automóvel" "4321432143214321"]
   ["2022-02-20"	79.90	 "iFood" "Alimentação" "4321432143214321"]
   ["2022-03-01"	85.00	 "Alura" "Educação" "4321432143214321"]
   ["2022-01-30"	85.00	 "Alura" "Educação" "1598159815981598"]
   ["2022-01-31"	350.00 "Tok&Stok" "Casa" "1598159815981598"]
   ["2022-02-01"	400.00 "Leroy Merlin" "Casa" "1598159815981598"]
   ["2022-03-01"	50.00	 "Madero" "Alimentação" "6655665566556655"]
   [ "2022-03-01"	70.00	 "Teatro" "Lazer" "6655665566556655"]
   ["2022-03-04"	250.00 "Hospital" "Saúde" "6655665566556655"]
   ["2022-04-10"	130.00 "Drogaria"	"Saúde" "6655665566556655"]
   ["2022-03-10"	100.00 "Show de pagode" "Lazer" "3939393939393939"]
   ["2022-03-11"	25.90	 "Dogão" "Alimentação" "3939393939393939"]
   ["2022-03-12"	215.87 "Praia" "Lazer" "3939393939393939"]
   ["2022-04-01"	976.88 "Oficina" "Automóvel" "3939393939393939"]
   ["2022-04-10"	85.00	 "Alura" "Educação" "3939393939393939"]])

(deftest compras-cartao
  (let [cartao 1234123412341234
        compras (y.compras/lista-compra lista-compras)]
    (is (= [:gasto-total 409.0] (y.core/soma-compras-cartao cartao compras)))))

(clojure.test/run-all-tests)