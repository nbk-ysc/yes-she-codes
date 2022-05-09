(ns yes-she-codes.core)

(defn novo-cliente
  "retorna um novo cliente com os dados informados"
  [nome cpf email]
  (let [cliente {:nome nome
                 :cpf cpf
                 :email email}]
    (println "novo cliente ->" cliente)
    cliente))

