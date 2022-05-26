(ns yes_she_codes.logic
  (:require [yes-she-codes.db :as y.db]
  [java-time :as t]))

(defn str->long
  "Recebe uma String e retorna um Long."
  [string]
  (Long/parseLong (clojure.string/replace string #" " "")))

(defn novo-cliente
  "Retorna um mapa que representa os dados de um cliente."
  [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

;(defn novo-cartao
;  "Retorna um mapa que representa os dados de um cartão."
;  [numero cvv validade limite cliente]
;  {:numero   (str->long numero)
;   :cvv      (str->long cvv)
;   :validade (t/year-month validade)
;   :limite   (bigdec limite)
;   :cliente  cliente})

(defn novo-cartao
  "Retorna um mapa que representa os dados de um cartão."
  [numero cvv validade limite cliente]
  {:numero   (str->long numero)
   :cvv      (str->long cvv)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cliente})

;(defn nova-compra
;  "Retorna um mapa que representa os dados de uma compra."
;  [data valor estabelecimento categoria cartao]
;  {:data            (t/local-date data)
;   :valor           (bigdec valor)
;   :estabelecimento estabelecimento
;   :categoria       categoria
;   :cartao          (str->long cartao)})

(defn nova-compra
  "Retorna um mapa que representa os dados de uma compra."
  [data valor estabelecimento categoria cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  "Recebe o caminho do arquivo e a função de mapeamento e retorna um vetor."
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (mapv funcao-mapeamento)))

;(defn lista-clientes
;  "Retorna um vetor de clientes."
;  []
;  (vec (map (fn [[nome cpf email]]
;              (novo-cliente nome cpf email))
;            (y.db/lista-registros-clientes))))

(defn lista-clientes
  "Retorna um vetor de clientes."
  []
  (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                       (novo-cliente nome cpf email))))

;(defn lista-cartoes
;  "Retorna um vetor de cartões."
;  []
;  (vec (map (fn [[numero cvv validade limite cliente]]
;              (novo-cartao numero cvv validade limite cliente))
;            (y.db/lista-registros-cartoes))))

(defn lista-cartoes
  "Retorna um vetor de cartões."
  []
  (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                      (novo-cartao numero cvv validade limite cliente))))

;(defn lista-compras
;  "Retorna um vetor de compras."
;  []
;  (vec (map (fn [[data valor estabelecimento categoria cartao]]
;              (nova-compra data valor estabelecimento categoria cartao))
;            (y.db/lista-registros-compras))))

(defn lista-compras
  "Retorna um vetor de compras."
  []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                      (nova-compra data valor estabelecimento categoria cartao))))

(defn total-gasto
  "Recebe uma lista de compras e retorna o total gasto."
  [compras]
  (reduce + (map :valor compras)))

(defn mes-da-data
  "Recebe uma data e retorna seu mês."
  [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

;(defn mes-da-data
;  "Recebe uma data e retorna seu mês."
;  [data]
;  (.getValue (t/month data)))

(defn filtra-compras
  "Recebe uma lista de compras e uma função e retorna uma lista de compras filtrada."
  [predicado compras]
  (vec (filter predicado compras)))

(defn filtra-compras-no-mes
  "Recebe um mês e uma lista de compras e retorna uma lista de compras naquele mês."
  [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %))) compras))

(defn filtra-compras-no-estabelecimento
  "Recebe um estabelecimento e uma lista de compras e retorna uma lista de compras naquele estabelecimento."
  [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %)) compras))

(defn total-gasto-no-mes
  "Recebe um mês e uma lista de compras e retorna o valor total gasto naquele mês."
  [mes compras]
  (total-gasto (filtra-compras-no-mes mes compras)))

; (def total-gasto-no-mes (comp total-gasto filtra-compras-no-mes))

(defn filtra-compras-por-valor
  "Recebe um valor minimo, máximo e uma lista de compras e retorna uma lista de compras entre os dois valores."
  [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo)) compras))

(defn agrupa-compras-por-categoria
  "Recebe uma lista de compras e retorna um vetor com categoria e total gasto."
  [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))

(println (lista-clientes))
(println (lista-cartoes))
(println (lista-compras))
(println (total-gasto (lista-compras)))
(println (mes-da-data "2021-04-05"))
(println (filtra-compras-no-mes "04" (lista-compras)))
(println (filtra-compras-no-estabelecimento "Outback" (lista-compras)))
(println (total-gasto-no-mes "04" (lista-compras)))
(println (filtra-compras-por-valor 100 1000 (lista-compras)))
(println (agrupa-compras-por-categoria (lista-compras)))