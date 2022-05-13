(ns yes-she-codes.core)




(defn get-valor
  [compra]
  (get compra :valor 0))

(defn get-data
  [compra]
  (get compra :data 0))

(defn total-gasto
  [ compras ]
  (->> compras
       (map get-valor)
       (reduce +)))

(defn captura-mes
  [data]
  (subs data 5 7))

;(defn gasto-no-mes
;  ([mes compras]
;   (gasto-no-mes mes compras []))
;
;  ([mes compras compras-naquele-mes]
;   (let [compra (first compras)]
;     (if  (some? compra)
;       (do
;         (if (= mes (captura-mes (get-data compra)))
;           (recur mes (next compras) (conj compras-naquele-mes compra))
;           (recur mes (next compras) compras-naquele-mes )))
;       compras-naquele-mes))))
;
;(def lista-compra [compra1 compra2 compra3 ] )
;(gasto-no-mes "01" lista-compra)

;(->> lista-compra
;     (filter #(= (captura-mes (get-data %)) "01" )))

(defn gasto-no-mes
  [mes lista-compras]
  (->> lista-compras
       (filter #(= (captura-mes (get-data %)) mes ))))

(def lista-compra [compra1 compra2 compra3 ] )
(gasto-no-mes "01" lista-compra)































(captura-mes (get-data compra1))

(defn compras-no-estabelecimento
  ([estabelecimento compras]
   (compras-no-estabelecimento estabelecimento compras []))

  ([estabelecimento compras compras-naquele-estabelecimento]
   (let [compra (first compras)]
     (if  (some? compra)
       (do
         (if (= estabelecimento (:estabelecimento compra))
           (recur estabelecimento (next compras) (conj compras-naquele-estabelecimento compra))
           (recur estabelecimento (next compras) compras-naquele-estabelecimento )))
       compras-naquele-estabelecimento))))

;lista-compra
(compras-no-estabelecimento "Cinema" lista-compra)


(->> lista-compra
     (filter #(= (:estabelecimento %) "Cinema") ))

(fn [compra] (= (:estabelecimento compra) "Alura"))

(filter (fn [compra] (= (:estabelecimento compra) "Alura")) lista-compra)

 #(= (:estabelecimento % "Alura"))