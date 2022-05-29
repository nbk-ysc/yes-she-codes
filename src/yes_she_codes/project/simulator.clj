(ns yes-she-codes.project.simulator
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.compra :as diplomat.compra]
            [yes-she-codes.project.controllers.compra :as controllers.compra]
            )
  (:use (clojure.pprint)))


(s/set-fn-validation! true)
(s/set-fn-validation! false)


;(diplomat.compra/consumir-dados-de-compras! "../../../data/in/compras.csv")


(def compras-dominio (atom []))
(diplomat.compra/inserir-compras-no-dominio "data/in/compras.csv" compras-dominio)




(deref compras-dominio)



