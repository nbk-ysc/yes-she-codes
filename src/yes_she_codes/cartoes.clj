(ns yes-she-codes.cartoes
  (:require [yes-she-codes.db :as y.db]
            ;[clojure.string :as s]
            [yes-she-codes.logic :as y.logic]))



(println "\n\n -------- novo cartao -------- ")
(defn novo-cartao
  [numero cvv validade limite cliente]
  (let [cc-info      {:numero   (y.logic/str-to-float numero)
                      :cvv      cvv
                      :validade validade
                      :limite   limite
                      :cliente  (y.logic/str-to-float cliente)}
        client-info  (y.logic/validar-cliente cliente)]
    (if (nil? client-info)
      "cpf nao encontrado"
      cc-info))
  )
;aqui usamos a funcao str-to-float para transformar strings
;primeiro removemos os espacos ou pontos (. -) entre os numeros
;e depois transformamos a string em tipo long

(let [numero "1111 2222 3333 4444"
      cvv 555
      validade "2022-09"
      limite 5.000
      cliente "114.918.436-16"
      ;cliente "000.111.222-33"
      ]
  (println (novo-cartao numero cvv validade limite cliente)))






(println "\n\n -------- lista cartoes -------- ")

(defn lista-cartoes
  []
  (let [cartoes (y.db/todos-cartoes)]
    (println cartoes))
  )

(lista-cartoes)


