(ns yes-she-codes.logic.compras
  (:require [schema.core :as s]
            [yes-she-codes.db.db :as y.db]
            [yes-she-codes.models.compras :as y.models.compras]
            [yes-she-codes.models.common :as y.models.common]))

(s/defn nova-compra :- y.models.compras/CompraSchema
  [data :- y.models.compras/DataSchema,
   valor :- y.models.compras/ValorNoCartaoSchema,
   estabelecimento :- y.models.compras/EstabelecimentoSchema,
   categoria :- y.models.compras/CategoriaSchema,
   cartao :- y.models.common/NumeroDoCartaoSchema]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

; To do: Colocar schema no parâmetro
(s/defn transforma-compra :- y.models.compras/CompraSchema [[data valor estabelecimento categoria cartao]]
  (nova-compra data valor estabelecimento categoria cartao))

; To do: Colocar schema no parâmetro
(s/defn transforma-compras :- y.models.compras/ComprasSchema [compras]
  (map transforma-compra compras))

(s/defn lista-compras :- y.models.compras/ComprasSchema []
  (transforma-compras (y.db/registros-de-compras)))

; ======================================================
; ==================== USANDO ATOM =====================
; ======================================================

(def repositorio-de-compras (atom []))

(s/defrecord Compra [id :- y.models.compras/IdSchema,
                     data :- y.models.compras/DataSchema,
                     valor :- y.models.compras/ValorNoCartaoSchema,
                     estabelecimento :- y.models.compras/EstabelecimentoSchema,
                     categoria :- y.models.compras/CategoriaSchema,
                     cartao :- y.models.common/NumeroDoCartaoSchema])

; ======================================================

(s/defn compra-com-id :- y.models.compras/CompraSchema [compras-cadastradas :- y.models.compras/ComprasSchema, compra-sem-id :- y.models.compras/CompraSchema]
  (assoc compra-sem-id :id (count compras-cadastradas)))

(s/defn insere-compra :- y.models.compras/ComprasSchema [compras-cadastradas :- y.models.compras/ComprasSchema, compra-sem-id :- y.models.compras/CompraSchema]
  (conj compras-cadastradas (compra-com-id compras-cadastradas compra-sem-id)))

(s/defn insere-compra! :- y.models.common/Nil [compra :- y.models.compras/CompraSchema]
  (swap! repositorio-de-compras insere-compra compra)
  nil)

; ======================================================

(s/defn reset-compras! :- y.models.common/Nil []
  (reset! repositorio-de-compras [])
  nil)

; To do: Colocar schema no parâmetro
(s/defn transforma-compra! :- y.models.common/Nil [[data valor estabelecimento categoria cartao]]
  (insere-compra! (map->Compra {:data            data,
                                :valor           valor,
                                :estabelecimento estabelecimento,
                                :categoria       categoria,
                                :cartao          cartao}))
  nil)

(s/defn carrega-compras! :- y.models.common/Nil []
  (reset-compras!)
  (doseq
    [compra (y.db/registros-de-compras)]
    (transforma-compra! compra))
  nil)

; ======================================================

(s/defn lista-compras! :- y.models.compras/ComprasSchema []
  @repositorio-de-compras)

; ======================================================

(s/defn compara-ids :- (s/maybe y.models.compras/CompraSchema) [id-buscado :- y.models.compras/IdSchema, compra :- y.models.compras/CompraSchema]
  (if (= id-buscado (:id compra))
    compra))

(s/defn pesquisa-compra-por-id :- (s/maybe y.models.compras/CompraSchema) [compras-cadastradas :- y.models.compras/ComprasSchema, id-buscado :- y.models.compras/IdSchema]
  (some #(compara-ids id-buscado %) compras-cadastradas))

(s/defn pesquisa-compra-por-id! :- (s/maybe y.models.compras/CompraSchema) [id-buscado :- y.models.compras/IdSchema]
  (pesquisa-compra-por-id @repositorio-de-compras id-buscado))

; ======================================================

(s/defn exclui-compra :- y.models.compras/ComprasSchema [compras-cadastradas :- y.models.compras/ComprasSchema, id-para-excluir :- y.models.compras/IdSchema]
  (remove #(compara-ids id-para-excluir %) compras-cadastradas))

(s/defn exclui-compra! :- y.models.compras/ComprasSchema [id-para-excluir :- y.models.compras/IdSchema]
  (swap! repositorio-de-compras exclui-compra id-para-excluir))
