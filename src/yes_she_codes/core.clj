(ns yes-she-codes.core)





























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