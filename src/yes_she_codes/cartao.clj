(ns yes_she_codes.cartao
  (:require [yes_she_codes.db :as y.db]))

;todo Criar uma funçao de validaçao dos dados que vao ser utilizados para acadastrar o cartao

(defn novo-cartao
  "Cadastra um novo cartao de crédito"
  [dados-cartao]
  (if-let [[cartao cvv validade limite cliente] dados-cartao]
    {:cartao   cartao
     :cvv      cvv
     :validade validade
     :limite   limite
     :cliente  cliente}
    (println "ERRO - Validadar dados do cartao" :cartao dados-cartao)))

(defn lista-cartao [lista-dados-cartao]
  (mapv novo-cartao lista-dados-cartao))



