(ns yes-she-codes.core)

(defn novo-client
  [nome cpf email]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    [client] ))

(println (novo-client "Emillyn" "8239283298" "fulano@hotmail.com"))





