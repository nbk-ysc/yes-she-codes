(ns yes_she_codes.compras
  (:require [yes_she_codes.db :as y.db]))

(defn nova-compra [data valor estabelecimento categoria cartao]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

(defn transforma-compra [[data valor estabelecimento categoria cartao]]
  (nova-compra data valor estabelecimento categoria cartao))

(defn transforma-compras [compras]
  (map transforma-compra compras))

(defn lista-compras []
  (transforma-compras (y.db/registros-de-compras)))

; ======================================================
; ==================== USANDO ATOM =====================
; ======================================================

(def repositorio-de-compras (atom []))

(defrecord Compra [id, data, valor, estabelecimento, categoria, cartao])

; ======================================================

(defn compra-com-id [compras-cadastradas compra-sem-id]
  (assoc compra-sem-id :id (count compras-cadastradas)))

(defn insere-compra [compras-cadastradas compra-sem-id]
  (conj compras-cadastradas (compra-com-id compras-cadastradas compra-sem-id)))

(defn insere-compra! [compra]
  (swap! repositorio-de-compras insere-compra compra))

; ======================================================

(defn reset-compras! []
  (reset! repositorio-de-compras []))

(defn transforma-compra! [[data valor estabelecimento categoria cartao]]
  (insere-compra! (map->Compra {:data            data,
                                :valor           valor,
                                :estabelecimento estabelecimento,
                                :categoria       categoria,
                                :cartao          cartao})))

(defn carrega-compras! []
  (reset-compras!)
  (doseq
    [compra (y.db/registros-de-compras)]
    (transforma-compra! compra)))

; ======================================================

(defn lista-compras! []
  @repositorio-de-compras)

; ======================================================

(defn compara-ids [id-buscado compra]
  (if (= id-buscado (:id compra))
    compra))

(defn pesquisa-compra-por-id [compras-cadastradas id-buscado]
  (some #(compara-ids id-buscado %) compras-cadastradas))

(defn pesquisa-compra-por-id! [id-buscado]
  (pesquisa-compra-por-id @repositorio-de-compras id-buscado))

; ======================================================

(defn exclui-compra [compras-cadastradas id-para-excluir]
  (remove #(compara-ids id-para-excluir %) compras-cadastradas))

(defn exclui-compra! [id-para-excluir]
  (swap! repositorio-de-compras exclui-compra id-para-excluir))
