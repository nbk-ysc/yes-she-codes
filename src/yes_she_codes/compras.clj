(ns yes_she_codes.compras
  (:require [yes_she_codes.db :as y.db]
            [schema.core :as s]))

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

(defn numero-do-cartao-valido? [numero-do-cartao]
  (and (> numero-do-cartao 0) (< numero-do-cartao 10000000000000000)))

(def Nil (s/pred nil?))
(def IdSchema (s/maybe (s/constrained s/Int id-valido?)))
(def DataSchema (s/constrained s/Str data-valida?))
(def ValorNoCartaoSchema (s/constrained BigDecimal valor-no-cartao-valido?))
(def EstabelecimentoSchema (s/constrained s/Str estabelecimento-valido?))
(def CategoriaSchema (s/constrained s/Str categoria-valida?))
(def CartaoSchema (s/constrained s/Int numero-do-cartao-valido?))

(def CompraSchema
  {(s/optional-key :id) IdSchema,
   :data                DataSchema,
   :valor               ValorNoCartaoSchema,
   :estabelecimento     EstabelecimentoSchema,
   :categoria           CategoriaSchema,
   :cartao              CartaoSchema})

(def ComprasSchema [CompraSchema])

(s/defn nova-compra :- CompraSchema
  [data :- DataSchema,
   valor :- ValorNoCartaoSchema,
   estabelecimento :- EstabelecimentoSchema,
   categoria :- CategoriaSchema,
   cartao :- CartaoSchema]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

; To do: Colocar schema no parâmetro
(s/defn transforma-compra :- CompraSchema [[data valor estabelecimento categoria cartao]]
  (nova-compra data valor estabelecimento categoria cartao))

; To do: Colocar schema no parâmetro
(s/defn transforma-compras :- ComprasSchema [compras]
  (map transforma-compra compras))

(s/defn lista-compras :- ComprasSchema []
  (transforma-compras (y.db/registros-de-compras)))

; ======================================================
; ==================== USANDO ATOM =====================
; ======================================================

(def repositorio-de-compras (atom []))

(s/defrecord Compra [id :- IdSchema,
                     data :- DataSchema,
                     valor :- ValorNoCartaoSchema,
                     estabelecimento :- EstabelecimentoSchema,
                     categoria :- CategoriaSchema,
                     cartao :- CartaoSchema])

; ======================================================

(s/defn compra-com-id :- CompraSchema [compras-cadastradas :- ComprasSchema, compra-sem-id :- CompraSchema]
  (assoc compra-sem-id :id (count compras-cadastradas)))

(s/defn insere-compra :- ComprasSchema [compras-cadastradas :- ComprasSchema, compra-sem-id :- CompraSchema]
  (conj compras-cadastradas (compra-com-id compras-cadastradas compra-sem-id)))

(s/defn insere-compra! :- Nil [compra :- CompraSchema]
  (swap! repositorio-de-compras insere-compra compra)
  nil)

; ======================================================

(s/defn reset-compras! :- Nil []
  (reset! repositorio-de-compras [])
  nil)

; To do: Colocar schema no parâmetro
(s/defn transforma-compra! :- Nil [[data valor estabelecimento categoria cartao]]
  (insere-compra! (map->Compra {:data            data,
                                :valor           valor,
                                :estabelecimento estabelecimento,
                                :categoria       categoria,
                                :cartao          cartao}))
  nil)

(s/defn carrega-compras! :- Nil []
  (reset-compras!)
  (doseq
    [compra (y.db/registros-de-compras)]
    (transforma-compra! compra))
  nil)

; ======================================================

(s/defn lista-compras! :- ComprasSchema []
  @repositorio-de-compras)

; ======================================================

(s/defn compara-ids :- (s/maybe CompraSchema) [id-buscado :- IdSchema, compra :- CompraSchema]
  (if (= id-buscado (:id compra))
    compra))

(s/defn pesquisa-compra-por-id :- (s/maybe CompraSchema) [compras-cadastradas :- ComprasSchema, id-buscado :- IdSchema]
  (some #(compara-ids id-buscado %) compras-cadastradas))

(s/defn pesquisa-compra-por-id! :- (s/maybe CompraSchema) [id-buscado :- IdSchema]
  (pesquisa-compra-por-id @repositorio-de-compras id-buscado))

; ======================================================

(s/defn exclui-compra :- ComprasSchema [compras-cadastradas :- ComprasSchema, id-para-excluir :- IdSchema]
  (remove #(compara-ids id-para-excluir %) compras-cadastradas))

(s/defn exclui-compra! :- ComprasSchema [id-para-excluir :- IdSchema]
  (swap! repositorio-de-compras exclui-compra id-para-excluir))
