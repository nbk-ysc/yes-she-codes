(ns yes_she_codes.semana2.logic)

(defn valida-categoria-compra
  [categoria]
  (let [categorias ["Alimentação" "Saúde" "Lazer" "Automóvel" "Lazer" "Educação" "Casa"]]
    (if-not (some #(= categoria %) categorias)
      (throw (ex-info "Categoria da compra não existe" {:categoria categoria})))))

(defn valida-estabelecimento-compra
  [estabelecimento]
  (if (< (count estabelecimento) 2)
    (throw (ex-info "Estabelecimento da compra deve possuir pelo menos 2 caracteres" {:estabelecimento estabelecimento}))))

(defn valida-valor-compra
  [valor]
  (if (< valor 0)
    (throw (ex-info "Valor da compra deve ser positivo" {:valor valor}))))

(defn valida-data-compra
  [data]
  (if (.before (java.util.Date.) data)
    (throw (ex-info "Data da compra deve ser uma data válida" {:data data}))))

(defn valida-compra
  [compra]
  (let [data-compra (:data compra)
        valor-compra (:valor compra)
        estabelecimento-compra (:estabelecimento compra)
        categoria-compra (:categoria compra)]

    (valida-data-compra data-compra)
    (valida-valor-compra valor-compra)
    (valida-estabelecimento-compra estabelecimento-compra)
    (valida-categoria-compra categoria-compra)))

(defn proximo-id
  [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn insere-compra
  [lista-de-compras compra]
  (let [id (proximo-id lista-de-compras)
        compra-com-id (assoc compra :id id)]
    (conj lista-de-compras compra-com-id)))

(defn insere-compra!
  [lista-de-compras compra]
  (valida-compra compra)
  (swap! lista-de-compras insere-compra compra))

(defn lista-compras!
  [repositorio-de-compras]
  (mapv println @repositorio-de-compras))

(defn possui-id?
  [entidade id]
  (= id (:id entidade)))

(defn pesquisa-compra-por-id
  [compras id]
  (let [compra (filter #(possui-id? % id) compras)]
    (if (not (empty? compra))
      compra)))

(defn pesquisa-compra-por-id!
  [compras id]
  (println (pesquisa-compra-por-id @compras id)))

(defn exclui-compra
  [compras id]
  (remove #(possui-id? % id) compras))

(defn exclui-compra!
  [compras id]
  (println (swap! compras exclui-compra id)))

(defn insere-cliente
  [lista-de-clientes cliente]
  (let [id (proximo-id lista-de-clientes)
        cliente-com-id (assoc cliente :id id)]
    (conj lista-de-clientes cliente-com-id)))

(defn insere-cliente!
  [lista-de-clientes cliente]
  (swap! lista-de-clientes insere-cliente cliente))

(defn lista-clientes!
  [repositorio-de-clientes]
  (mapv println @repositorio-de-clientes))

(defn pesquisa-cliente-por-id
  [clientes id]
  (let [cliente (filter #(possui-id? % id) clientes)]
    (if (not (empty? cliente))
      cliente)))

(defn pesquisa-cliente-por-id!
  [clientes id]
  (println (pesquisa-cliente-por-id @clientes id)))

(defn exclui-cliente
  [clientes id]
  (remove #(possui-id? % id) clientes))

(defn exclui-cliente!
  [clientes id]
  (println (swap! clientes exclui-cliente id)))

(defn insere-cartao
  [lista-de-cartoes cartao]
  (let [id (proximo-id lista-de-cartoes)
        cartao-com-id (assoc cartao :id id)]
    (conj lista-de-cartoes cartao-com-id)))

(defn insere-cartao!
  [lista-de-cartoes cartao]
  (swap! lista-de-cartoes insere-cartao cartao))

(defn lista-cartoes!
  [repositorio-de-cartoes]
  (mapv println @repositorio-de-cartoes))

(defn pesquisa-cartao-por-id
  [cartoes id]
  (let [cartao (filter #(possui-id? % id) cartoes)]
    (if (not (empty? cartao))
      cartao)))

(defn pesquisa-cartao-por-id!
  [cartoes id]
  (println (pesquisa-cartao-por-id @cartoes id)))

(defn exclui-cartao
  [cartoes id]
  (remove #(possui-id? % id) cartoes))

(defn exclui-cartao!
  [cartoes id]
  (println (swap! cartoes exclui-cartao id)))