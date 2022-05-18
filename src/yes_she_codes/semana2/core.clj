(ns yes_she_codes.semana2.core
  (:require [yes_she_codes.semana2.db :as y.db]
            [yes_she_codes.semana2.logic :as y.logic])
  (:use clojure.pprint)
  (:import (yes_she_codes.semana2.model Compra)))

(let [compras (y.db/lista-compras)
      compra1 (Compra. nil #inst "2022-04-10T03:00:00.000-00:00" 85.00M "Alura" "Educação" 3939393939393939)
      atomo-compra y.db/repositorio-de-compras]
  (y.logic/insere-compra! atomo-compra compra1)
  (y.logic/insere-compra! atomo-compra compra1)
  (pprint atomo-compra))


