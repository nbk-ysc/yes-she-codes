(ns yes-she-codes.project.wire.out.compra
  (:require [schema.core :as s]))

(s/defschema CompraCsv
  {:data            s/Str
   :valor           s/Str
   :estabelecimento s/Str
   :categoria       s/Str
   :cartao          s/Str
   :validade        s/Str
   :limite          s/Str
   :nome            s/Str
   :cpf             s/Str
   :email           s/Str})
