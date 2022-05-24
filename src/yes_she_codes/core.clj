(ns yes-she-codes.core)

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))


(defn novo-cliente [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})


(defn novo-cartao [numero cvv validade limite cliente]
  {:numero  (str->long numero)
   :cvv     (str->long cvv)
   :email   validade
   :limite  (bigdec limite)
   :cliente cliente})


(defn nova-compra [data valor estabelecimento categoria cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})


(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (mapv funcao-mapeamento)))


(defn lista-clientes []
  (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                       (novo-cliente nome cpf email))))

(defn lista-cartoes []
  (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                       (novo-cartao numero cvv validade limite cliente))))


(defn lista-compras []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
            (nova-compra data valor estabelecimento categoria cartao))))


(defn total-gasto [compras]
  (reduce + (map :valor compras)))


(defn mes-da-data [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))


(defn filtra-compras [predicado compras]
  (vec (filter predicado compras)))


(defn filtra-compras-no-mes [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %)))
                  compras))


(defn filtra-compras-no-estabelecimento [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %))
                  compras))


;(defn total-gasto-no-mes [mes compras]
;  (total-gasto (filtra-compras-no-mes mes compras)))


(def total-gasto-no-mes-com-composition (comp total-gasto filtra-compras-no-mes))

(defn filtra-compras-por-valor [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo))
                  compras))


(defn agrupa-gastos-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))











































