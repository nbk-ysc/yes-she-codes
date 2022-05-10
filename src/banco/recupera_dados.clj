(ns banco.recupera-dados)

(defn csv-seq [rdr]
  (let [entries (map #(clojure.string/split % #",")
                     (line-seq rdr))
        header (map keyword (first entries))]
    (map #(apply hash-map (interleave header %))
         (rest entries))
    ))

(defn recupera-clientes []
  (csv-seq (clojure.java.io/reader "/Users/marilia.marques/Downloads/clientes.csv")))

(defn recupera-cartoes []
  (csv-seq (clojure.java.io/reader "/Users/marilia.marques/Downloads/cartoes.csv")))

(defn recupera-compras []
  (csv-seq (clojure.java.io/reader "/Users/marilia.marques/Downloads/compras.csv")))

;(doseq [clientes (csv-seq (clojure.java.io/reader "/Users/marilia.marques/Downloads/clientes.csv"))] (println clientes))
