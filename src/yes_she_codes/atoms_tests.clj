(ns yes-she-codes.atoms-tests
  (:require [yes-she-codes.persistence.database-atoms :as y.bd]
            [yes-she-codes.domain.client :as y.client]
            [yes-she-codes.domain.card :as y.card]
            [yes-she-codes.domain.purchase :as y.purchase]
            [java-time :as time])
  (:use [clojure pprint]))


(y.bd/load-database!!)


(println "========= CLIENTS LIST ==========")
(pprint (y.bd/list-clients!))

(println "\n========= INSERT CLIENT OF ID 5 ==========")
(y.bd/insert-client! (y.client/->client nil
                                           "Mulher Maravilha"
                                           "321.321.321-32"
                                           "maisfortedaliga@ligadajustica.dc.br"))

(println "\n========= SEARCH CLIENT OF ID 5 ==========")
(pprint (y.bd/search-client-by-id! 5))

(println "\n========= DELETE CLIENT OF ID 5 ==========")
(y.bd/delete-client! 5)


(println "\n========= CARDS LIST ==========")
(pprint (y.bd/list-cards!))

(println "\n========= INSERT CARD OF ID 6 ==========")
(y.bd/insert-card! (y.card/->card nil
                                        1111222233334444
                                        999
                                        (time/year-month "2025-05")
                                        1500M
                                        "000.111.222-33"))

(println "\n========= SEARCH CARD OF ID 6 ==========")
(pprint (y.bd/search-card-by-id! 6))

(println "\n========= DELETE CLIENT OF ID 5 ==========")
(y.bd/delete-card! 6)


(println "\n========= PURCHASES LIST ==========")
(pprint (y.bd/list-purchases!))

(println "\n========= INSERT PURCHASE OF ID 20 ==========")
(y.bd/insert-purchase! (y.purchase/->purchase nil
                                        (time/local-date "2022-05-20")
                                        123.99M
                                        "Carrefour"
                                        "Alimentação"
                                        3939393939393939))

(println "\n========= SEARCH PURCHASE OF ID 20 ==========")
(pprint (y.bd/search-purchase-by-id! 20))

(println "\n========= DELETE PURCHASE OF ID 20 ==========")
(y.bd/delete-purchase! 20)

(println "\n========= INSPECTING VIUVA NEGRA PURCHASES (card4321432143214321)")
(def viuva-purchases (y.purchase/filter-purchases-by-card 4321432143214321
                                                          (y.bd/list-purchases!)))

(println "\n========= PURCHASES TOTAL EXPENSES =========")
(pprint (y.purchase/total-spent viuva-purchases))

(println "\n========= FEBRUARY TOTAL SPENT =========")
(pprint (y.purchase/total-spent-month 2 viuva-purchases))
(pprint (y.purchase/month-purchases-filter 2 viuva-purchases))

(println "\n========= PURCHASES GROUPED BY CATEGORY =========")
(pprint (y.purchase/group-spends-by-category viuva-purchases))


(println "\n========= TRY TO INSERT INVALID PURCHASE ==========")
(try
  (y.bd/insert-purchase! (y.purchase/->purchase nil
                                          (time/local-date "2022-05-20")
                                          -100M
                                          "Carrefour"
                                          "Alimentação"
                                          3939393939393939))
  (catch Exception e
    (println "Ir worked!")))
