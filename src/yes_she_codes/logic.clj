(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as s]))



(let [clientes (y.db/todos-clientes)
      cartoes (y.db/todos-cartoes)
      compras (y.db/todas-compras)]
  (println "clientes ---" (sort-by :nome clientes))
  (println "cartoes ---" (sort-by :cliente cartoes))
  (println "compras ---" (reverse (sort-by :cartao compras)))
)



(println "\n\n -------- novo cliente -------- ")

(defn novo-cliente
  [nome cpf email]
  (let [cliente {:nome  nome
                 :cpf   cpf
                 :email email}]
    cliente))

(println (novo-cliente "Gabriel", "114.918.436-16", "gabrielvcbessa@gmail.com"))






(println "\n\n -------- novo cartao -------- ")

(defn str-to-float
  [string]
  (if (string? string)
           (->> string
                (remove #{\ \.\-})
                (s/join)
                (Long/valueOf))
           string))


(defn novo-cartao
  [numero cvv validade limite cliente]
  (let [cartao {:numero   (str-to-float numero)
                :cvv      cvv
                :validade validade
                :limite   limite
                :cliente  (str-to-float cliente)}]
    cartao))

(println (novo-cartao "1111 2222 3333 4444", 555, "2022-09", 5.000, "114.918.436-16"))

;aqui usamos a funcao str-to-float para transformar strings
;primeiro removemos os espacos ou pontos (. -) entre os numeros
;e depois transformamos a string em tipo long




(println "\n\n -------- nova compra -------- ")

(defn str-to-float
  [string]
  (if (string? string)
    (->> string
         (remove #{\ \.\-})
         (s/join)
         (Long/valueOf))
    string))


(defn nova-compra
  [data valor estabelecimento categoria cartao]
  (let [compra {:data            data
                :valor           (bigdec valor)
                :estabelecimento estabelecimento
                :categoria       categoria
                :cartao          (str-to-float cartao)}]
    compra))

(println (nova-compra "2022-05-10", 199.99, "Nucleo Artistico", "Lazer", "1111 2222 3333 4444"))

;aqui usamos a funcao str-to-float para transformar strings
;primeiro removemos os espacos ou pontos (. -) entre os numeros
;e depois transformamos a string em tipo long





(println "\n\n -------- lista clientes -------- ")

(defn lista-clientes
  []
  (let [clientes (y.db/todos-clientes)]
    (println clientes))
  )

(lista-clientes)





(println "\n\n -------- lista cartoes -------- ")

(defn lista-cartoes
  []
  (let [cartoes (y.db/todos-cartoes)]
    (println cartoes))
  )

(lista-cartoes)





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











