(ns yes-she-codes.core)

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn lista-clients []
  (let [clients [["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                 ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
                 ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
                 ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]]]
               (vec (map novo-cliente clients))))


(println (lista-clients))

