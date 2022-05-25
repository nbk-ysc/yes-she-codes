(ns yes-she-codes.semana3.cartoes
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logic :as y.3.logic])
  (:use [clojure pprint])
  )



(s/set-fn-validation! true)



; ---------- CARD SCHEMA ----------
(def CartaoSchema
  "Schema de um cartao"
  {:numero y.3.logic/IntBetween0And,                              ; inteiro entre 0 e 1 0000 0000 0000 0000 OK
   :cvv y.3.logic/IntBetween0And999,                              ; inteiro entre 0 e 999 OK
   :validade y.3.logic/DateFormatString,                          ; se estiver usando String, deve ter o formato "yyyy-mm" OK
   :limite y.3.logic/BigDecimalBiggerEqual0,                      ; BigDecimal maior ou igual a zero OK
   :cliente y.3.logic/CpfFormatStr                                ; String com o formato 000.000.000-00.
   })



; ---------- NEW CARD FUNCTION ----------
(s/defn novo-cartao :- CartaoSchema ; funcao retorna um novo cartao
  [numero :- y.3.logic/IntBetween0And
   cvv :- y.3.logic/IntBetween0And999
   validade :- y.3.logic/DateFormatString
   limite :- y.3.logic/BigDecimalBiggerEqual0
   cliente :- y.3.logic/CpfFormatStr
   ]
  {:numero numero, :cvv cvv, :validade validade, :limite limite, :cliente cliente}
  )



(pprint (novo-cartao 1000000000000000, 999, "2019-02", 1.0, "115.314.506-52"))
;(pprint (novo-cartao 1000000000000000, 1000, "x", "x", "x")) ; da erro por causa do cvv
;(pprint (novo-cartao 1000000000000000, 999, "2019-02-03", "x", "x")) ; da erro por causa da data
;(pprint (novo-cartao 1000000000000000, 999, "2019-02", -1.0, "x")) ; da erro por causa do limite
;(pprint (novo-cartao 1000000000000000, 999, "2019-02", 1.0, "11531450652")); da erro por causa do cpf
