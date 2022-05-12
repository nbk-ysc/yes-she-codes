(ns yes-she-codes.compras
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as s]
            [yes-she-codes.logic :as y.logic]))





(println "\n\n -------- nova compra -------- ")

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            data
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







(println "\n\n -------- lista compras -------- ")

(defn lista-compras
  []
  (let [compras (y.db/todas-compras)]
    (println compras))
  )

(lista-compras)








(println "\n\n -------- total gasto -------- ")

(defn total-gasto
  [compras]
  (->> compras
       (map :valor)
       (reduce +))
  )

(let [compras (y.db/todas-compras)]
  (println (total-gasto compras)))







(println "\n\n -------- lista compras/mes -------- ")

(defn compras-por-mes
  [mes compras]
  (let [parsed-mes (format "%02d" mes)]
    (->> compras
         (filter #(s/includes? (:data %) (str "-" parsed-mes "-"))))))


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
       ; retorno: {Alimentação [{:data 2022-01-01, :valor 129.9, :estabelecimento Outback, :categoria Alimentação, :cartao 1234 1234 1234 1234} {:data 2022-02-20, :valor 79.9, :estabelecimento iFood, :categoria Alimentação, :cartao 4321 4321 4321 4321} {:data 2022-03-01, :valor 50.0, :estabelecimento Madero, :categoria Alimentação, :cartao 6655 6655 6655 6655} {:data 2022-03-11, :valor 25.9, :estabelecimento Dogão, :categoria Alimentação, :cartao 3939 3939 3939 3939}], Saúde [{:data 2022-01-02, :valor 260.0, :estabelecimento Dentista, :categoria Saúde, :cartao 1234 1234 1234 1234} {:data 2022-03-04, :valor 250.0, :estabelecimento Hospital, :categoria Saúde, :cartao 6655 6655 6655 6655} {:data 2022-04-10, :valor 130.0, :estabelecimento Drogaria, :categoria Saúde, :cartao 6655 6655 6655 6655}], Lazer [{:data 2022-02-01, :valor 20.0, :estabelecimento Cinema, :categoria Lazer, :cartao 1234 1234 1234 1234} {:data 2022-01-10, :valor 150.0, :estabelecimento Show, :categoria Lazer, :cartao 4321 4321 4321 4321} {:data 2022-03-01, :valor 70.0, :estabelecimento Teatro, :categoria Lazer, :cartao 6655 6655 6655 6655} {:data 2022-03-10, :valor 100.0, :estabelecimento Show de pagode, :categoria Lazer, :cartao 3939 3939 3939 3939} {:data 2022-03-12, :valor 215.87, :estabelecimento Praia, :categoria Lazer, :cartao 3939 3939 3939 3939}], Automóvel [{:data 2022-02-10, :valor 289.99, :estabelecimento Posto de gasolina, :categoria Automóvel, :cartao 4321 4321 4321 4321} {:data 2022-04-01, :valor 976.88, :estabelecimento Oficina, :categoria Automóvel, :cartao 3939 3939 3939 3939}], Educação [{:data 2022-03-01, :valor 85.0, :estabelecimento Alura, :categoria Educação, :cartao 4321 4321 4321 4321} {:data 2022-01-30, :valor 85.0, :estabelecimento Alura, :categoria Educação, :cartao 1598 1598 1598 1598} {:data 2022-04-10, :valor 85.0, :estabelecimento Alura, :categoria Educação, :cartao 3939 3939 3939 3939}], Casa [{:data 2022-01-31, :valor 350.0, :estabelecimento Tok&Stok, :categoria Casa, :cartao 1598 1598 1598 1598} {:data 2022-02-01, :valor 400.0, :estabelecimento Leroy Merlin, :categoria Casa, :cartao 1598 1598 1598 1598}]}

       (map total-por-categoria)
       )
  )
;({:key Alimentação, :total 285.7} {:key Saúde, :total 640.0} {:key Lazer, :total 555.87} {:key Automóvel, :total 1266.87} {:key Educação, :total 255.0} {:key Casa, :total 750.0})
;({Alimentação 285.7} {Saúde 640.0} {Lazer 555.87} {Automóvel 1266.87} {Educação 255.0} {Casa 750.0})


(let [compras (y.db/todas-compras)]
  (println (agrupados-por-categoria compras)))





