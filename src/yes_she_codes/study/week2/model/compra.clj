(ns yes-she-codes.study.week2.model.compra
  (:import (java.time LocalDate)))

(defrecord Compra [^Long       id
                   ^LocalDate  data
                   ^BigDecimal valor
                   ^String     estabelecimento
                   ^String     categoria
                   ^Long       cartao])
