(ns yes-she-codes.data
  (:use clojure.data)
  (:require [yes-she-codes.db :as y.db]
            [clojure.java.io :as io]
            [java-time :as t]))


(require '[java-time :as t])

(println (t/format "MM/yyyy" (t/year-month "2022-05"))) ; vai imprimir 05/2022