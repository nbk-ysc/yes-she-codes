(ns yes-she-codes.cliente)

(def clientes [])

(defn novo-cliente
  "retorna um novo cliente com os dados informados"
  [nome cpf email]
  (let [novo-cliente {:nome nome
                      :cpf cpf
                      :email email}]
    (def clientes (conj clientes novo-cliente))
    novo-cliente))

(defn lista-clientes
  ([arg1] clientes)
  ([] clientes))