(ns yes-she-codes.project.simulator
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            )

  )


(s/set-fn-validation! true)

;;;;;; acessa o arquivo externo
;;;; nesse fomrato obriga o usuário a saber qual aruivo é de cada dominio e então direcionar ao adapter correto

(def raw-data-cartoes (diplomat.csv/ler-csv "data/in/cartoes.csv"))

;;;;;; transforma no model do domínio

(def cartoes-dominio (adapter.cartao/csv->cartoes raw-data-cartoes))




(def raw-data-compras (diplomat.csv/ler-csv "data/in/compras.csv"))
(def compras-dominio (adapter.compra/csv->compras raw-data-compras))
(clojure.pprint/pprint compras-dominio)


(def raw-data-clientes (diplomat.csv/ler-csv "data/in/clientes.csv"))
(def clientes-dominio (adapter.cliente/csv->clientes raw-data-clientes))
clientes-dominio







