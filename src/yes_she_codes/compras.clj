(ns yes-she-codes.compras
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as s]
            [yes-she-codes.logic :as y.logic]
            [java-time :as t]))





(println "\n\n ---------------- nova compra ---------------- ")

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            (t/format (t/local-date (str data)))
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (y.logic/str-to-float cartao)}
  )


(let [data "2022-05-10"
      valor 199.99
      estabelecimento "Nucleo Artistico"
      categoria "Lazer"
      cartao "1111 2222 3333 4444"
      ]
  (println (nova-compra data valor estabelecimento categoria cartao)))

;aqui usamos a funcao str-to-float para transformar strings
;primeiro removemos os espacos ou pontos (. -) entre os numeros
;e depois transformamos a string em tipo long





(println "\n\n ---------------- lista compras ---------------- ")

(defn lista-compras
  []
  (let [compras (y.logic/csv-reader "/Users/maria.carneiro/Documents/yes-she-codes-alura/yes-she-codes/src/yes_she_codes/csv/compras.csv")]
    (println compras))
  )

(lista-compras)





(println "\n\n ---------------- total gasto ---------------- ")

(defn total-gasto
  [compras]
  (->> compras
       (map :valor)
       (reduce +)
       )
  )

(let [compras (y.db/todas-compras)]
  (println (total-gasto compras)))





(println "\n\n ---------------- lista compras/mes ---------------- ")

(defn compras-por-mes
  [mes compras]
  (let [parsed-mes (format "%02d" mes)]
    (->> compras
         (filter #(s/includes? (:data %) (str "-" parsed-mes "-"))))))
; poderiamos usar
; (re-matches #"\d{4}-\d{2}-\d{2}" "2022-04-10")
; (essa expressao acima confirma se essa data tem 4 numeros em ano, 2 numeros em mes e dois numeros em dia

; para capturar o valor de mes teria que fazer:
; (re-matches #"\d{4}-(\d{2})-\d{2}" "2022-04-10") -> retorno ["2022-04-10""04"]
; acima voce consegue capturar o valor "mes"

; entao seria apenas pegar a segunda posicao desse vetor usando "second"
; (second (re-matches #"\d{4}-(\d{2})-\d{2}" "2022-04-10")) -> retorno "04"

(let [compras (y.db/todas-compras)
      mes 3]
  (->> (compras-por-mes mes compras)
       println))





(println "\n\n -------- estabelecimento -------- ")

(defn compras-por-estabelecimento
  [estabelecimento compras]
  (->> compras
       (filter #(s/includes? (:estabelecimento %) (str estabelecimento)))))


(let [compras (y.db/todas-compras)
      estabelecimento "Alura"]
  (->> (compras-por-estabelecimento estabelecimento compras)
       println))





(println "\n\n -------- total gasto/mes em 1 cartao -------- ")

; filtrar por um cartao
(defn compras-cartao
  [compras cartao]
  (->> compras (filter #(s/includes? (:cartao %) (str cartao))))
  )


(defn total-gasto-no-mes
  [mes compras cartao]
  (-> (compras-por-mes mes compras)
      (compras-cartao cartao)
      (total-gasto))
  )

(let [compras (y.db/todas-compras)
      mes 3
      cartao "6655 6655 6655 6655"]
  (println (total-gasto-no-mes mes compras cartao)))





(println "\n\n -------- filtrar compras num intervalo de valores -------- ")

(defn compras-em-intervalo-de-valor
  [compras valMax valMin]
  (->> compras
       (filter #(and (< (:valor %) valMax) (> (:valor %) valMin)))
       (map :valor))
  )


(let [compras (y.db/todas-compras)]
  (println (compras-em-intervalo-de-valor compras 150 69)))





(println "\n\n -------- agrupar gastos por categoria -------- ")

(defn preco-total-por-categoria
  [compras]
  (->> compras
       (map :categoria)
       (map :valor compras)
       (reduce +)))


(defn total-por-categoria
  [[key compras]]
  {:categoria key
   :total (preco-total-por-categoria compras)
   }
  ; se quiser que saia apenas os valores, basta remover o :categoria e :total do retorno
  ; assim:
  ;{key
  ; (total-price-per-user compras)
  ; }
  )

(defn agrupados-por-categoria
  [compras]
  (->> compras
       (group-by :categoria)
       ; retorno: {Alimentação [{:data 2022-01-01 ...}], Saúde [{:data 2022-01-02...}],
       ;           Lazer [{:data 2022-02-01,...}],
       ;           Automóvel [{:data 2022-02-10, ...}],
       ;           Educação [{:data 2022-03-01, ...}],
       ;           Casa [{:data 2022-01-31,...}]}

       (map total-por-categoria)
       )
  )
;({:key Alimentação, :total 285.7} {:key Saúde, :total 640.0} {:key Lazer, :total 555.87} {:key Automóvel, :total 1266.87} {:key Educação, :total 255.0} {:key Casa, :total 750.0})
;({Alimentação 285.7} {Saúde 640.0} {Lazer 555.87} {Automóvel 1266.87} {Educação 255.0} {Casa 750.0})


(let [compras (y.db/todas-compras)]
  (println (agrupados-por-categoria compras)))





