(ns yes-she-codes.logic.logic
  (:require [schema.core :as s]
            [yes-she-codes.logic.util :as y.util]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))


(def NomeValido (s/constrained s/Str y.util/carateres-valido 'NomeValido))
(def CpfValido (s/constrained s/Str y.util/cpf-valido 'CpfValido))
(def EmailValido (s/constrained s/Str y.util/email-valido 'EmailValido))
(def NumeroCartaoValido (s/constrained s/Num y.util/numero-cartao-valido 'NumeroValido))
(def CvvValido (s/constrained s/Num  y.util/cvv-cartao-valido 'CvvValido))
(def LimiteValido (s/constrained s/Num y.util/limite-cartao-valido 'LimiteValido))
(def ValidadeValido (s/pred y.util/validade-cartao-valido 'ValidadeValido))
(def ClienteValido (s/constrained s/Str y.util/cliente-cartao-valido 'ClienteValido))
(def ValorCompraValido (s/constrained s/Num y.util/valor-compra-valido 'ValorCompraValido))
(def DataCompraValido (s/pred y.util/data-compra-valido 'DataCompraValido))
(def EstabelecimentoValido (s/constrained s/Str y.util/carateres-valido 'EstabelecimentoValido))
(def CategoriaValido (s/constrained s/Str y.util/categoria-valido 'CategoriaValido))