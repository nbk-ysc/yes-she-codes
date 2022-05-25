(ns yes-she-codes.semana3.compras
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logic :as y.3.logic])
  (:use [clojure pprint])
  )



(s/set-fn-validation! true)



; ---------- COMPRA SCHEMA ----------
(def CompraSchema
  "Schema de uma compra"
  {:data y.3.logic/DateFormatString,                     ; Se você estiver usando a data como String, então ela deve ter o formato yyyy-mm-dd;
   :valor y.3.logic/BigDecimalBiggerEqual0,              ; deve ser um BigDecimal positivo
   :estabelecimento y.3.logic/MinDoisCaracteresStr,      ; Deve ter pelo menos 2 caracteres
   :categoria y.3.logic/MatchCategory,                   ; Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.
   :cartao y.3.logic/IntBetween0And,                     ; inteiro entre 0 e 1 0000 0000 0000 0000.
   }
  )



; ---------- NEW COMPRA FUNCTION ----------
(s/defn nova-compra :- CompraSchema ; funcao retorna uma nova compra
  [data :-  y.3.logic/DateFormatString
   valor :- y.3.logic/BigDecimalBiggerEqual0
   estabelecimento :- y.3.logic/MinDoisCaracteresStr
   categoria :- y.3.logic/MatchCategory
   cartao :- y.3.logic/IntBetween0And
   ]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao}
  )



(pprint (nova-compra "2012-02", 3.0, "Araujo", "Alimentação", 1000))
;(pprint (nova-compra "2012-02-03", "x", "x", "x", "x"))     ; da erro por causa da data
;(pprint (nova-compra "2012-02", -3.0, "x", "x", "x"))     ; da erro por causa do valor
;(pprint (nova-compra "2012-02", 3.0, "A", "x", "x"))     ; da erro por causa do estabelecimento com menos de 2 caracteres
;(pprint (nova-compra "2012-02", 3.0, "Araujo", "x", 1000))     ; da erro por causa da categoria
;(pprint (nova-compra "2012-02", 3.0, "Araujo", "x", 10000000000000000))     ; da erro por causa do cartao