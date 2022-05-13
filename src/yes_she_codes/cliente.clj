(ns yes-she-codes.cliente
  (:require [yes-she-codes.csvload :as y.csv])
  )

(defn leitura-de-novos-clientes []
  "Le os novos clientes
  In : nil
  Out: (PersistentVector)
  "
  [["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
   ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
   ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
   ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]])


(defn cria-mapa-cliente [nome cpf email]
  "Cria a estrutura do novo cliente
  In : nome (String) cpf (String) email (String)
  Out: (hashmap)
  "
  {:nome nome :cpf cpf :email email})

(defn novo-cliente [lista-clientes]
  "Mapeia o novo registro da lista-clientes na estrutura do sistema
  In : lista-clientes (PersistentVector)
  Out: (hashmap)
  "
  (map (fn [[nome cpf email]]
         (cria-mapa-cliente nome cpf email))
       lista-clientes))

(defn lista-clientes []
  "Obtem as informações dos clientes em um vetor
  In : nil
  Out: (PersistentVector)"
  (-> (novo-cliente (leitura-de-novos-clientes))
      vec ))

(defn lista-clientes [filepath]
  (vec (y.csv/lee-e-formata-csv filepath))
  )

(println (lista-clientes "clientes.csv"))
