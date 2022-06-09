(ns yes-she-codes.util
  (:require [java-time :as time]
            [schema.core :as s])
  (:import java.util.Date
           (java.text SimpleDateFormat)))

(defn transforma-datas
  [data]
  (time/local-date data))

(defn transforma-datas-inst
  [data]
  (let [x1 (SimpleDateFormat. "yyyy-MM-dd")]
    (.parse x1 data)))

(defn mes-da-data [data]
  (.getValue (time/month data)))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn tamanho-valido?
  [valor]
  (>= (count valor) 2))

(defn cpf-valido? [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cpf))

(defn email-valido?  [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (and (string? email) (re-matches pattern email))))

(defn entre-valores [min max valor]
  (and (>= valor min) (<= valor max)))

(defn min-caracteres [n]
  (s/constrained s/Str #(>= (count %) n)))


(defn opcional [schema]
  (s/maybe schema))

(def InteiroPositivo (s/pred pos-int?))
(def IdOpcional (opcional InteiroPositivo))
(def ValorPositivo (s/constrained BigDecimal (comp not neg?)))


(def NomeValido (s/constrained s/Str tamanho-valido?))
(def CpfValido (s/constrained s/Str cpf-valido?))
(def EmailValido (s/constrained s/Str email-valido?))
(def NumeroDeCartaoValido (s/constrained s/Int (partial entre-valores 1 9999999999999999)))
(def CvvValido (s/constrained s/Int (partial entre-valores 0 999)))

(def DataDeCompraValida (s/constrained java.time.LocalDate
                                       (fn [data]
                                         (let [data-atual (time/local-date)]
                                           (or (= data data-atual) (time/before? data data-atual))))))

(def EstabelecimentoValido (min-caracteres 2))
(def CategoriaValida (s/enum "Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"))