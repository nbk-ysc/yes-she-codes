(ns yes-she-codes.study.week2.model.cliente)

(defrecord Cliente [^Long   id
                    ^String nome
                    ^String cpf
                    ^String email])