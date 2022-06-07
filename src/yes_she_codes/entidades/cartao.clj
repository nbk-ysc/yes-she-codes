(ns yes-she-codes.entidades.cartao
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.entidades.cliente :as y.cliente])
  (:import [java.time YearMonth]))

(def NumeroDeCartaoValido (s/constrained s/Int (partial y.util/entre-valores 1 9999999999999999)))
(def CvvValido (s/constrained s/Int (partial y.util/entre-valores 0 999)))

(def CartaoSchema {(s/optional-key :id) y.util/IdOpcional
                   :numero              NumeroDeCartaoValido
                   :cvv                 CvvValido
                   :validade            YearMonth
                   :limite              y.util/ValorPositivo
                   :cliente             y.cliente/CpfValido})

(s/defn ->Cartao :- CartaoSchema
  [id :- y.util/IdOpcional
   numero :- NumeroDeCartaoValido
   cvv :- CvvValido
   validade :- YearMonth
   limite :- y.util/ValorPositivo
   cliente :- y.cliente/CpfValido]

  {:id       id
   :numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})

; fazer uma filtro de cartão para chamar na validação da compra
; caso o cartão n exista, entra no except de compra inválida