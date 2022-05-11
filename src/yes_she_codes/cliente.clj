(ns yes-she-codes.cliente)

(defn novo-cliente [parametro-cliente]
  (if-let [[nome cpf email] parametro-cliente]
    {:nome nome
     :cpf cpf
     :email email}
    (throw (ex-info "Cliente invalido" {:cliente parametro-cliente}))))

(defn lista-clientes [parametros-clientes]
  (map novo-cliente parametros-clientes))