(ns yes-she-codes.semana3.clientes
  (:require [schema.core :as s])
  (:use [clojure pprint])
  )



(s/set-fn-validation! true)



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



; ---------- CLIENT SCHEMA ----------
(def ClienteSchema                   ; o schema é um mapa com as validacoes de nome, cpf e email
  "Schema de um cliente"
  {:nome MinDoisCaracteresStr,       ; pelo menos 2 caracteres OK
   :cpf CpfFormatStr,                ; string com o formato 000.000.000-00 OK
   :email EmailFormatStr             ; email válido OK
   })



; ---------- NEW CLIENT FUNCTION ----------
(s/defn novo-cliente :- ClienteSchema ; funcao retorna um novo cliente
  [nome :- MinDoisCaracteresStr
   cpf :- CpfFormatStr
   email :- s/Str]
  {:nome nome, :cpf cpf, :email email}
  )

(pprint (novo-cliente "Li", "115.314.506-52", "mguimaraes@gmail.com.br"))
;(pprint (novo-cliente "L", "115.314.506-52", "mguimaraes@gmail.com")) ; nome da erro
;(pprint (novo-cliente "Li", "11531450652", "mguimaraes@gmail.com")) ; cpf da erro
;(pprint (novo-cliente "Li", "115.314.506-52", "mguimaraes@gmail")) ; email da erro

