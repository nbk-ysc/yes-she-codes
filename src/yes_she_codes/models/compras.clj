(ns yes-she-codes.models.compras
  (:require [schema.core :as s]
            [yes-she-codes.models.common :as y.models.common]))

(defn id-valido? [id]
  (>= id 0))

(defn data-valida? [data]
  (= (re-find #"^[0-9]{4}\-(?:[0][1-9]|[1][0-2])\-(?:0[1-9]|[12][0-9]|3[01])$" data)
     data))

(defn valor-no-cartao-valido? [valor-no-cartao]
  (>= valor-no-cartao 0))

(defn estabelecimento-valido? [estabelecimento]
  (>= (count estabelecimento) 2))

(defn categoria-valida? [categoria]
  (let [categorias ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"]]
    (not (nil? (some #{categoria} categorias)))))

(def IdSchema (s/maybe (s/constrained s/Int id-valido?)))
(def DataSchema (s/constrained s/Str data-valida?))
(def ValorNoCartaoSchema (s/constrained BigDecimal valor-no-cartao-valido?))
(def EstabelecimentoSchema (s/constrained s/Str estabelecimento-valido?))
(def CategoriaSchema (s/constrained s/Str categoria-valida?))

(def CompraSchema
  {(s/optional-key :id) IdSchema,
   :data                DataSchema,
   :valor               ValorNoCartaoSchema,
   :estabelecimento     EstabelecimentoSchema,
   :categoria           CategoriaSchema,
   :cartao              y.models.common/NumeroDoCartaoSchema})

(def ComprasSchema [CompraSchema])
