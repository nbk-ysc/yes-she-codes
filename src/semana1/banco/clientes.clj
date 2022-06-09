(ns semana1.banco.clientes)

(def cliente1 {:nome "Feiticeira Escarlate"
              :cpf "000.111.222-33"
              :email "feiticeira.poderosa@vingadoras.com.br"})

(def cliente2 {:nome "Viúva Negra"
              :cpf "333.444.555-66"
              :email "viuva.casca.grossa@vingadoras.com.br"})

(def cliente3 {:nome "Hermione Granger"
              :cpf "666.777.888-99"
              :email "hermione.salvadora@hogwarts.com"})

(def cliente4 {:nome "Daenerys Targaryen"
              :cpf "999.123.456-78"
              :email "mae.dos.dragoes@got.com"})

(defn todos-os-clientes []
  [cliente1, cliente2, cliente3, cliente4])