(ns yes-she-codes.cliente)

(def dados_clientes [["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                    ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
                    ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
                    ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]])

(defn novo-cliente
  "criar uma estrutura de cliente"
  [nome cpf email]
    {:Nome nome
    :CPF cpf
    :Email email})

(defn adiciona-cliente
  "Adicionar um novo cliente"
  [item]
  (novo-cliente (get item 0 "") (get item 1 "") (get item 2 "")))

(defn lista-clientes
  "Lista os clientes"
  [dados]
  (map adiciona-cliente dados))

(println (lista-clientes dados_clientes))