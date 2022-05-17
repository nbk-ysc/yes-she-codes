(ns yes-she-codes.leituracsv)

(defn novo-cliente
  [nome cpf email]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn novo-cartao
  [numero cvv limite validate cpf]
  (let [formatValidate (t/format "MM/yyyy" (t/year-month validate))]
    (let [client (conj {:numero numero, :cvv cvv, :limite limite :validate formatValidate :cpf cpf})]
      client)))

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  (let [formatData (t/format "dd/MM/yyyy" (t/local-date data))]
    (conj {:data formatData, :valor valor, :estabelecimento estabelecimento :categoria categoria :cartao cartao})))

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


