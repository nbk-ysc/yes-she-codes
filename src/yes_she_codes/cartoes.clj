(ns yes_she_codes.cartoes
  (:require [yes_she_codes.db :as y.db]
            [schema.core :as s]))

(s/set-fn-validation! true)

(defn numero-do-cartao-valido? [numero-do-cartao]
  (and (> numero-do-cartao 0) (< numero-do-cartao 10000000000000000)))

(defn cvv-valido? [cvv]
  (and (> cvv 0) (< cvv 1000)))

(defn validade-valida? [validade]
  (= (re-find #"^[0-9]{4}\-(?:[0][1-9]|[1][0-2])$" validade)
     validade))

(defn limite-valido? [limite]
  (>= limite 0))

(defn cliente-valido? [cpf]
  (= (re-find #"^\d{3}\.\d{3}\.\d{3}\-\d{2}$" cpf)
     cpf))

(def NumeroDoCartaoSchema (s/constrained s/Int numero-do-cartao-valido?))
(def CVVSchema (s/constrained s/Int cvv-valido?))
(def ValidadeSchema (s/constrained s/Str validade-valida?))
(def LimiteSchema (s/constrained BigDecimal limite-valido?))
(def ClienteSchema (s/constrained s/Str cliente-valido?))

(def CartaoSchema
  {:numero   NumeroDoCartaoSchema,
   :cvv      CVVSchema,
   :validade ValidadeSchema,
   :limite   LimiteSchema,
   :cliente  ClienteSchema})

(def CartoesSchema [CartaoSchema])

(s/defn novo-cartao :- CartaoSchema
  [numero :- NumeroDoCartaoSchema,
   cvv :- CVVSchema,
   validade :- ValidadeSchema,
   limite :- LimiteSchema,
   cliente :- ClienteSchema]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

; To do: Colocar schema no parâmetro
(s/defn transforma-cartao :- CartaoSchema [[numero cvv validade limite cliente]]
  (novo-cartao numero cvv validade limite cliente))

; To do: Colocar schema no parâmetro
(s/defn transforma-cartoes :- CartoesSchema [cartoes]
  (map transforma-cartao cartoes))

(s/defn lista-cartoes :- CartoesSchema []
  (transforma-cartoes (y.db/registros-de-cartoes)))
