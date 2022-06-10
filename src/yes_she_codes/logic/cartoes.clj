(ns yes-she-codes.logic.cartoes
  (:require [schema.core :as s]
            [yes-she-codes.db.db :as y.db]
            [yes-she-codes.models.cartoes :as y.models.cartoes]
            [yes-she-codes.models.common :as y.models.common]))

(s/set-fn-validation! true)

(s/defn novo-cartao :- y.models.cartoes/CartaoSchema
  [numero :- y.models.common/NumeroDoCartaoSchema,
   cvv :- y.models.cartoes/CVVSchema,
   validade :- y.models.cartoes/ValidadeSchema,
   limite :- y.models.cartoes/LimiteSchema,
   cliente :- y.models.common/CPFSchema]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

; To do: Colocar schema no parâmetro
(s/defn transforma-cartao :- y.models.cartoes/CartaoSchema [[numero cvv validade limite cliente]]
  (novo-cartao numero cvv validade limite cliente))

; To do: Colocar schema no parâmetro
(s/defn transforma-cartoes :- y.models.cartoes/CartoesSchema [cartoes]
  (map transforma-cartao cartoes))

(s/defn lista-cartoes :- y.models.cartoes/CartoesSchema []
  (transforma-cartoes (y.db/registros-de-cartoes)))
