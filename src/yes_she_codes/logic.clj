(ns yes-she-codes.logic)

(defn novo-cliente
  [nome, cpf, email]
  (let [cliente {:nome nome, :cpf cpf, :email email}]
    cliente))

(defn novo-cartao
  [numero, cvv, validade, limite, cliente]
  (let [cartao {:numero numero, :cvv cvv, :validade validade, :limite limite, :cliente cliente}]
    cartao))


(defn compra-realizada
  [data, valor, estabelecimento, categoria, cartao]
  (let [compra {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao}]
    compra))


(defn total-gasto
  [compras]
  (reduce + (map #(:VALOR %) compras)))

(defn lista-clientes []
  (csv-data->maps (take-csv "/Users/gabriela.rezzo/Documents/Cursos/Bootcamp/yes-she-codes/clientes.csv")))

(println "cliente" lista-clientes)

(defn lista-compras []
  (csv-data->maps (take-csv "/Users/gabriela.rezzo/Documents/Cursos/Bootcamp/yes-she-codes/compras.csv")))


(defn lista-cartoes []
  (csv-data->maps (take-csv "/Users/gabriela.rezzo/Documents/Cursos/Bootcamp/yes-she-codes/cartoes.csv")))


(defn compras-feitas-mes [mes compras]
  (filter (fn [compra] (cstr/includes? (compra :DATA) mes)) compras))


(defn compras-feitas-estabelecimento [estabelecimento compras]
  (filter (fn [compra] (= (compra :ESTABELECIMENTO) estabelecimento)) compras))

(defn entre-min-max? [valor min max]
  (and (<= valor max) (>= valor min)))

(defn compras-intervalo-valores [min max compras]
  (filter (fn [compra] (and (<= (bigdec (compra :VALOR)) max) (>= (bigdec (compra :VALOR)) min))) compras))

(defn total-gasto-no-mes [mes compras]
  (let [compras-mes (compras-feitas-mes mes compras)]
    (total-gasto compras-mes)))

(defn somar-total [compras]
  (->> compras
       (map :VALOR)
       (map bigdec)
       (reduce +)))

(defn total-por-categoria [[categoria compras]]
  {:CATEGORIA categoria
   :TOTAL (somar-total compras)})

(defn agrupar-por-categoria [compras]
  (->> compras
       (group-by :CATEGORIA)
       (map total-por-categoria)))