(ns yes-she-codes.core)

(defn novo-cliente [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn novo-cartao [numero cvv validade limite cliente]
  {
   :numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})

(defn nova-compra [data valor estabelecimento categoria cartao]
  {
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})


(defn lista-clientes []
  [{:nome "Feiticeira Escarlate", :cpf "000.111.222-33", :email "feiticeira.poderosa@vingadoras.com.br"}
   {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}
   {:nome "Hermione Granger", :cpf "666.777.888-99", :email "hermione.salvadora@hogwarts.com"}
   {:nome "Daenerys Targaryen", :cpf "999.123.456-78", :email "mae.dos.dragoes@got.com"}])

(println (map lista-clientes))

(defn lista-cartoes []
  [{:numero 1234123412341234, :cvv 111, :validade "2023-01", :limite 1.000, :cliente "000.111.222-33"}
   {:numero 4321432143214321, :cvv 222, :validade "2024-02", :limite 2.000, :cliente "333.444.555-66"}
   {:numero 1598159815981598, :cvv 333, :validade "2021-03" :limite 3.000, :cliente "666.777.888-99"}
   {:numero 6655665566556655, :cvv 444, :validade "2025-04", :limite 4.000, :cliente "666.777.888-99"}
   {:numero 3939393939393939, :cvv 555, :validade "2026-05", :limite 5.000, :cliente "999.123.456-78"}])

(defn lista-compras []
  [{:data "2022-01-01", :valor 129.90, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234}
   {:data "2022-01-02", :valor 260.00, :estabelecimento "Dentista", :categoria "Saúde", :cartao 1234123412341234}
   {:data "2022-02-01", :valor 20.00, :estabelecimento "Cinema", :categoria "Lazer", :cartao 1234123412341234}
   {:data "2022-01-10", :valor 150.00, :estabelecimento "Show", :categoria "Lazer", :cartao 4321432143214321}
   {:data "2022-02-10", :valor 289.99, :estabelecimento "Posto de gasolina", :categoria "Automóvel", :cartao 4321432143214321}
   {:data "2022-02-20", :valor 79.90, :estabelecimento "iFood", :categoria "Alimentação", :cartao 4321432143214321}
   {:data "2022-03-01", :valor 85.00, :estabelecimento "Alura", :categoria "Educação", :cartao 4321432143214321}
   {:data "2022-01-30", :valor 85.00, :estabelecimento "Alura", :categoria "Educação", :cartao 1598159815981598}
   {:data "2022-01-31", :valor 350.00, :estabelecimento "Tok&Stok", :categoria "Casa", :cartao 1598159815981598}
   {:data "2022-02-01", :valor 400.00, :estabelecimento "Leroy Merlin", :categoria "Casa", :cartao 1598159815981598}
   {:data "2022-03-01", :valor 50.00, :estabelecimento "Madero", :categoria "Alimentação", :cartao 6655665566556655}
   {:data "2022-03-01", :valor 70.00, :estabelecimento "Teatro", :categoria "Lazer", :cartao 6655665566556655}
   {:data "2022-03-04", :valor 250.00, :estabelecimento "Hospital", :categoria "Saúde", :cartao 6655665566556655}
   {:data "2022-04-10", :valor 130.00, :estabelecimento "Drogaria", :categoria "Saúde", :cartao 6655665566556655}
   {:data "2022-03-10", :valor 100.00, :estabelecimento "Show de pagode", :categoria "Lazer", :cartao 3939393939393939}
   {:data "2022-03-11", :valor 25.90, :estabelecimento "Dogão", :categoria "Alimentação", :cartao 3939393939393939}
   {:data "2022-03-12", :valor 215.87, :estabelecimento "Praia", :categoria "Lazer", :cartao 3939393939393939}
   {:data "2022-04-01", :valor 976.88, :estabelecimento "Oficina", :categoria "Automóvel", :cartao 3939393939393939}
   {:data "2022-04-10", :valor 85.00, :estabelecimento "Alura", :categoria "Educação", :cartao 3939393939393939}])

(defn total-gasto [compras]
  (reduce + (map :valor compras)))

(defn mes-da-data [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))


(defn filtra-compras [predicado compras]
  (vec (filter predicado compras)))


(defn filtra-compras-no-mes [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %)))
                  compras))

(defn filtra-compras-no-estabelecimento [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %))
                  compras))

(defn filtra-compras-por-valor [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo))
                  compras))

(defn agrupa-gastos-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))