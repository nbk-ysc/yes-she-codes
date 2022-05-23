(ns yes-she-codes.semana2.compra
  (:import (java.time LocalDate))
  (:use clojure.pprint))


(def repositorio-de-compras (atom []))

(println @repositorio-de-compras)

(defrecord Compra [^Long id,
                   ^LocalDate data,
                   ^BigDecimal valor,
                   ^String estabelecimento,
                   ^String categoria,
                   ^Long cartao])


(def vetor [(Compra. 1 LocalDate 1000.00 "outback" "comida" 1235468459) (Compra. 2 LocalDate 1000.00 "outback" "comida" 1235468459)])

(defn proximo-id
  [compras]
  (if-not (empty? compras)
     (+ 1 (apply max (map :id compras)))
     1))

(defn insere-compra [compras nova-compra]
  (vec (let [id (proximo-id compras)
             compra-com-id (assoc nova-compra :id id)]
         (conj compras compra-com-id))))

(def compra1 (Compra. nil LocalDate 1000.00 "outback" "comida" 1235468459))
(def compra2 (Compra. nil LocalDate 100.00 "cobasi" "pet" 1235468459))

(defn insere-compra! [compra atomo-compras]
  ; swap passa o atomo como primeiro parametro para a função insere-compra
       (swap! atomo-compras insere-compra compra))

(insere-compra! compra1 repositorio-de-compras)

(insere-compra! compra2 repositorio-de-compras)
(println @repositorio-de-compras)

;(defn lista-compras!
;  [atomo-compras]
;  (->> atomo-compras
;       (get @repositorio-de-compras :compras)
;       (map println)))

(defn lista-compras!
  [atomo-compras]
  @atomo-compras )

(lista-compras! repositorio-de-compras)


;(defn verifica-vazio [vetor]
;  (if (= (count vetor) 0) nil vetor))
;
;(defn pesquisa-compra-por-id
;  [id, compras]
;  (->> compras
;       (filter #(= id (:id %)))
;       (verifica-vazio)))
;;

(defn pesquisa-compra-por-id
  [id, compras]
  (->> compras
       (filter #(= id (:id %)))
       first))
; first pega o primerio e retornar no filter, se n tiver nada retorna nil
; transofrmar pra seq tbm funciona

(defn pesquisa-compra-por-id!
  [id atomo-compras]
  (let [lista-de-compras  (lista-compras! atomo-compras) ]
    (pesquisa-compra-por-id id lista-de-compras)))

(pesquisa-compra-por-id! 2 repositorio-de-compras)


(defn deve-remover?
  [id item]
  (->> item
       (:id )
       (= id )))

(defn exclui-compra
  [ compras id]
  (remove #(deve-remover? id %) compras))

(exclui-compra vetor 15)

(defn exclui-compra!
  [id atomo-compras]
       (swap! atomo-compras exclui-compra id ))

(insere-compra! compra2 repositorio-de-compras)
(lista-compras! repositorio-de-compras)
(exclui-compra! 2 repositorio-de-compras)


