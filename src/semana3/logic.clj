(ns semana3.logic
  (:require [schema.core :as s
             :include-macros true]))

(def tipos-categoria ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"])

(defn dois-caracteres?
  [nome]
  (>= (count nome) 2))

(def Possui-2-caracteres (s/constrained s/Str dois-caracteres? 'Possui-2-caracteres))


(defn cpf-format?
  [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cpf))

(def CpfFormat (s/constrained s/Str cpf-format? 'CpfFormat))


(defn email-format?
  [email]
  (re-matches #"^(?![_.-])((?![_.-][_.-])[a-zA-Z\d_.-]){0,63}[a-zA-Z\d]@((?!-)((?!--)[a-zA-Z\d-]){0,63}[a-zA-Z\d]\.){1,2}([a-zA-Z]{2,14}\.)?[a-zA-Z]{2,14}$" email))

(def EmailFormat (s/constrained s/Str email-format? 'EmailFormat))

;(pprint (s/validate EmailFormat "sa@gmail.com.br"))

;(pprint (s/validate CpfFormat "155.565.565-78"))
;(println (CpfFormatStr "35299928874"))
;
;(def CpfFormatStr (s/constrained s/Str cpf-format? 'CpfFormatStr))

(defn valida-numero-cartao
  [cartao]
  (and (>= cartao 0)
       (<= cartao 10000000000000000)))

(def Cartao-valido? (s/constrained s/Int valida-numero-cartao 'Cartao-valido))

;(pprint (s/validate Cartao-valido? 10000000000000000))

(defn valida-numero-cvv [cvv]
  (and (>= cvv 0)
       (<= cvv 999)))

(def Cvv-valido? (s/constrained s/Int valida-numero-cvv 'Cvv-valido?))

;(pprint (s/validate Cvv-valido? 655))

;não consegui fazer de uma maneira de aceitar 0 a esquerda
;(pprint (format "Pad with leading zeros %03d" 97))
;(pprint (Integer/parseInt "097"))

(defn ano-mes-format
  [ano-mes]
  (re-matches #"[1-2]{1}[0-9]{3}\-[0-1]{1}[0-9]{1}" ano-mes))

(def VerificaData? (s/constrained s/Str ano-mes-format 'VerificaData))

(defn valida-valor-positivo-incluindo-zero [limite]
  (>= limite 0))

(def Valor-BigDecimal-positivo-ou-zero? (s/constrained BigDecimal valida-valor-positivo-incluindo-zero 'Valor-BigDecimal-positivo-ou-zero?))

(defn ano-mes-dia-format
  [ano-mes]
  (re-matches #"[1-2]{1}[0-9]{3}\-[0-1]{1}[0-9]{1}\-[0-3]{1}[0-9]{1}" ano-mes))

(def Verifica-data-completa? (s/constrained s/Str ano-mes-dia-format 'VerificaData))
;(println (s/validate Valor-BigDecimal-positivo-ou-zero? 90M))


(defn possui-categoria? [categoria]
  some (partial = categoria) tipos-categoria)

(def Categoria-valida? (s/constrained s/Str possui-categoria? 'Categoria-valida?))


