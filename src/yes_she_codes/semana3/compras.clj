(ns yes-she-codes.semana3.compras
  (:require [schema.core :as s])
  (:use [clojure pprint])
  )

(def CompraSchema
  "Schema de uma compra"
  {:data s/Int,                                            ; Se você estiver usando a data como String, então ela deve ter o formato yyyy-mm-dd;
   :valor s/Int,                                           ; deve ser um BigDecimal positivo.
   :estabelecimento s/Str,                                 ; Deve ter pelo menos 2 caracteres.
   :categoria s/Str,                                       ; Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.
   :cartao s/Int,                                          ; inteiro entre 0 e 1 0000 0000 0000 0000.
   }
  )