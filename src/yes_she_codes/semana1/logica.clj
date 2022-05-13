(ns yes-she-codes.semana1.logica)

(defn get-valor
  [compra]
  (get compra :valor 0))

(defn get-data
  [compra]
  (get compra :data 0))

(defn get-mes
  [data]
  (subs data 5 7))
