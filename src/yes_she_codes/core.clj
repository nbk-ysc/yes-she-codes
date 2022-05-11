(ns yes-she-codes.core)

(def conta-clients
  (atom {}))

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn lista-clients []
  (let [clients [["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                 ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
                 ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
                 ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]]]
               (swap! conta-clients assoc-in [:clients] (vec (map novo-cliente clients)))))

(lista-clients)
(println  @conta-clients)

(defn get-client
  [field  value data]
  (filter #(= value (field %)) data))

(defn add-cartao [conta]
  (swap! conta-clients update :clients conj conta))

(defn novo-cartao
  [numero cvv validate limite cpf]
  (let [client (into {} (get-client :cpf cpf (:clients @conta-clients)))]
  (let [cartao {:cartao {:numero numero, :cvv cvv, :validate validate :limite limite}}]
  (let [conta (conj client cartao)]
    (add-cartao conta)))))

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  (let [client (into {} (get-client #(:numero (:cartao %)) cartao (:clients @conta-clients)))]
  (let [compra {:compras {:data data, :valor valor, :estabelecimento estabelecimento :categoria categoria}}]
  (let [conta (assoc-in client [:cartao] (conj (:cartao client) compra))]
    conta))))

(println (get-client :cpf "000.111.222-33" (:clients @conta-clients)))

(println (novo-cartao 12819289182918 233 "2022/12" 1000.0 "000.111.222-33"))

(println (get-client (comp :numero :cartao) 12819289182918 (:clients @conta-clients)))
(println (get-client #(:numero (:cartao %)) 12819289182918 (:clients @conta-clients)))

(println (nova-compra "01/2012" 1000 "Americanas" "Alimentacao" 12819289182918))
(println (nova-compra "01/2013" 1000 "Ifood" "Alimentacao" 12819289182918))