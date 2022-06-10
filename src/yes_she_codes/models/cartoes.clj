(ns yes-she-codes.models.cartoes
  (:require [schema.core :as s]
            [yes-she-codes.models.common :as y.models.common]))

(defn cvv-valido? [cvv]
  (and (> cvv 0) (< cvv 1000)))

(defn validade-valida? [validade]
  (= (re-find #"^[0-9]{4}\-(?:[0][1-9]|[1][0-2])$" validade)
     validade))

(defn limite-valido? [limite]
  (>= limite 0))

(def CVVSchema (s/constrained s/Int cvv-valido?))
(def ValidadeSchema (s/constrained s/Str validade-valida?))
(def LimiteSchema (s/constrained BigDecimal limite-valido?))

(def CartaoSchema
  {:numero   y.models.common/NumeroDoCartaoSchema,
   :cvv      CVVSchema,
   :validade ValidadeSchema,
   :limite   LimiteSchema,
   :cliente  y.models.common/CPFSchema})

(def CartoesSchema [CartaoSchema])
