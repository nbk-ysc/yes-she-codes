(ns yes-she-codes.semana3.logic
  (:require [schema.core :as s])
  (:use [clojure pprint])
  )



; ---------- MIN 2 CARACTERES ----------
(defn min-2-caracteres?
  [x]
  (>= (count x) 2))

(def MinDoisCaracteresStr (s/constrained s/Str min-2-caracteres? 'MinDoisCaracteres?))



; ---------- CPF STRING FORMAT ----------
(defn cpf-format?
  [x]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" x))

(def CpfFormatStr (s/constrained s/Str cpf-format? 'CpfFormatStr))
;(pprint (cpf-format? "115.314.506-52"))
;(pprint (cpf-format? "11531450652"))



; ---------- EMAIL STRING FORMAT ----------
(defn email-format?
  [x]
  (re-matches #"^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+).*" x))

(def EmailFormatStr (s/constrained s/Str email-format? 'EmailFormatStr))



; ---------- INT BETWEEN 0 AND 1 0000 0000 0000 0000 ----------
(defn int-between-0-and-?
  [x]
  (and (> x 0) (< x 10000000000000000)))

(def IntBetween0And (s/constrained s/Int int-between-0-and-? 'IntBetween0And))
;(pprint (int-between-0-and-? 1000000000000000))



; ---------- INT BETWEEN 0 AND 999 ----------
(defn int-between-0-and-999?
  [x]
  (and (> x 0) (<= x 999)))

(def IntBetween0And999 (s/constrained s/Int int-between-0-and-999? 'IntBetween0And999))
;(pprint (int-between-0-and-999? 998))



; ---------- DATE FORMAT STRING ----------
(defn date-format-string?
  [x]
  (re-matches #"\d{4}-\d{2}" x))

(def DateFormatString (s/constrained s/Str date-format-string? 'DateFormatString))
;(pprint (date-format-string? "2012-02"))



; ---------- DATE FORMAT STRING ----------
(defn date-day-format-string?
  [x]
  (re-matches #"\d{4}-\d{2}-\d{2}" x))

(def DateDayFormatString (s/constrained s/Str date-day-format-string? 'DateDayFormatString?))
;(pprint (date-day-format-string? "2012-02-01"))



; ---------- BIGDECIMAL >= 0 ----------
(defn big-decimal-bigger-or-equal-0?
  [x]
  (>= x 0))

(def BigDecimalBiggerEqual0 (s/pred big-decimal-bigger-or-equal-0? 'DateFormatString))
;(pprint (big-decimal-bigger-or-equal-0? 1.0))



; ---------- CATEGORIA ---------
;Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde
(defn match-category?
  [x]
  (clojure.string/includes? "Alimentação, Automóvel, Casa, Educação, Lazer, Saúde" x)
  )

(def MatchCategory (s/pred match-category? 'MatchCategory))
;(pprint (match-category? "lala"))
