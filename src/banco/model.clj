(ns banco.model)

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defn nova-compra [data valor estabelecimento categoria cartao]
  {:compra/data  data
   :compra/valor  (bigdec valor)
   :compra/estabelecimento estabelecimento
   :compra/categoria categoria
   :compra/cartao (str->long cartao)})


(defn novo-cartao [numero cvv validade limite cliente]
  {:cartao/numero  (str->long numero)
   :cartao/cvv     (str->long cvv)
   :cartao/validade validade
   :cartao/limite  (bigdec limite)
   :cartao/cliente cliente})
