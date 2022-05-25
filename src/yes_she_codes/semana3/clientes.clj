(ns yes-she-codes.semana3.clientes
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logic :as y.3.logic])
  (:use [clojure pprint])
  )



(s/set-fn-validation! true)



; ---------- CLIENT SCHEMA ----------
(def ClienteSchema                   ; o schema é um mapa com as validacoes de nome, cpf e email
  "Schema de um cliente"
  {:nome y.3.logic/MinDoisCaracteresStr,       ; pelo menos 2 caracteres OK
   :cpf y.3.logic/CpfFormatStr,                ; string com o formato 000.000.000-00 OK
   :email y.3.logic/EmailFormatStr             ; email válido OK
   })



; ---------- NEW CLIENT FUNCTION ----------
(s/defn novo-cliente :- ClienteSchema ; funcao retorna um novo cliente
  [nome :- y.3.logic/MinDoisCaracteresStr
   cpf :- y.3.logic/CpfFormatStr
   email :- y.3.logic/EmailFormatStr]
  {:nome nome, :cpf cpf, :email email}
  )



(pprint (novo-cliente "Li", "115.314.506-52", "mguimaraes@gmail.com.br"))
;(pprint (novo-cliente "L", "115.314.506-52", "mguimaraes@gmail.com")) ; nome da erro
;(pprint (novo-cliente "Li", "11531450652", "mguimaraes@gmail.com")) ; cpf da erro
;(pprint (novo-cliente "Li", "115.314.506-52", "mguimaraes@gmail")) ; email da erro

