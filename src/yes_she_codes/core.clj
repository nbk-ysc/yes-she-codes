(ns yes-she-codes.core)

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (mapv funcao-mapeamento)))

(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn novo-cartao [numero, cvv, validade, limite, cliente]
  {:numero  (str->long numero)
   :cvv     (str->long cvv)
   :email   validade
   :limite  (bigdec limite)
   :cliente cliente})


(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})


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
  (->> compras
       (map :valor)
       (reduce +)))

(defn filtra-compras [predicado, compras]
  (vec (filter predicado compras)))

(defn filtra-compras-no-estabelecimento [estabelecimento, compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %))
                  compras))

(defn mes-da-data [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

(defn filtra-compras-no-mes [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %)))
                  compras))

(defn total-gasto-no-mes [mes compras]
  (total-gasto (filtra-compras-no-mes mes compras)))

;(println (lista-clientes))
;(println (lista-cartoes))
;(println (lista-compras))
;(println "Total gasto:" (total-gasto (lista-compras)))
;(println (filtra-compras-no-estabelecimento "Alura" (lista-compras)))































