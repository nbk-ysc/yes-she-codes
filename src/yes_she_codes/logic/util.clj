(ns yes-she-codes.logic.util
  (:require [java-time :as time]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))


(defprotocol ExtratorDeMes
  (mes-da-data [data]
    "Dada uma data, extrai o valor do mês como um inteiro de 1 a 12"))

(extend-type java.lang.String ExtratorDeMes
  (mes-da-data [data]
    (->> data
         (re-matches #"\d{4}-(\d{2})-\d{2}")
         second
         Long/valueOf)))

(extend-type java.time.LocalDate ExtratorDeMes
  (mes-da-data [data]
    (.getValue (time/month data))))

(defn data-valida? [data]
  (re-matches #"\d{4}-(\d{2})-\d{2}" data))


(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

;minimo 2 caracteres
(defn carateres-valido [nome]
  (re-matches #"^.{2,}" nome))

(defn cpf-valido [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cpf))

(defn email-valido [email]
  (re-matches #"^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" email))

;inteiro entre 0 e 1 0000 0000 0000 0000.
(defn numero-cartao-valido [numero]
  (<= 0 numero 10000000000000000))

;inteiro entre 0 e 999.
(defn cvv-cartao-valido [cvv]
  (<= 0 cvv 999))

;BigDecimal maior ou igual a zero.
(defn limite-cartao-valido [limite]
  (and (decimal? limite) (>= limite 0)))

;se estiver usando String, deve ter o formato "yyyy-mm".
;se estiver usando Java Time, deve ser uma instância de java.time.Month.
(defn validade-cartao-valido [validade]
  ;(= (class (java-time/month (java-time/local-date validade)) )(class (java-time/month)))
  (java-time/month (java-time/local-date validade))
  )

;String com o formato 000.000.000-00.
(defn cliente-cartao-valido [cliente]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cliente))

;deve ser um BigDecimal positivo.
(defn valor-compra-valido [valor]
  (and (decimal? valor) (pos? valor)))

;Se você estiver usando API do Java Time, então ela deve ser uma data menor ou igual à data atual.
(defn data-compra-valido [data]
  (time/before? (time/local-date) (time/local-date data)))

;Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.
(defn categoria-valido [categoria]
  (or (= "Alimentação" categoria)
      (= "Automóvel" categoria)
      (= "Casa" categoria)
      (= "Educação" categoria)
      (= "Lazer" categoria)
      (= "Saúde" categoria))
  )
