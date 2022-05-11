(ns yes-she-codes.date
  (use 'java-time)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.main :as y.main])
  )

(def hora-entrada  (local-date-time 2021))
