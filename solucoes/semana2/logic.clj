(ns yes-she-codes.logic)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))



(def candy {})
(println candy)
(println "ola" (proximo-id candy))
;(defn atualiza-compra
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))