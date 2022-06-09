(ns yes-she-codes.semana3.core
    (:use clojure.pprint)
    (:require [schema.core :as s]))

;CLIENTE
(defn string-valido? [nome]     (>= (count nome) 2))
(def cpf-valido? (partial re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}"))
(def email-valido? (partial re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))

(def ClienteSchema  {(s/optional-key :id) s/Int
                     :nome  (s/constrained s/Str string-valido?)
                     :cpf   (s/constrained s/Str cpf-valido?)
                     :email (s/constrained s/Str email-valido?)})

(s/defn ->Cliente :- ClienteSchema
        [id :- s/Int
         nome :- (s/constrained s/Str string-valido?)
         cpf :- (s/constrained s/Str cpf-valido?)
         email :- (s/constrained s/Str email-valido?)]
        {:id    id :nome  nome :cpf   cpf :email email})




;CARTAO
(def Numero-de-cartao-valido? (partial y.util/entre-valores 1 9999999999999999))
(def cvv-valido? (partial y.util/entre-valores 0 999))
(def validade-valido? (partial re-matches #"[0-9]{4}-[0-9]{2}"))


(def CartaoSchema {(s/optional-key :id) s/Int
                   :numero      (s/constrained s/Int Numero-de-cartao-valido?),
                   :cvc         (s/constrained s/Int cvv-valido?),
                   :validade    (s/constrained s/Str validade-valido?),
                   :limite      (s/constrained BigDecimal (comp not neg?)),
                   :cliente-cpf (s/constrained s/Str cpf-valido?)})
;[4321432143214321 222 "2024-02" 2.000M "333.444.555-66"]

(s/defn ->Cartao :- CartaoSchema
        [ id  :- s/Int
         numero  :- (s/constrained s/Int Numero-de-cartao-valido?),
         cvv  :- (s/constrained s/Int cvv-valido?),
         validade  :- (s/constrained s/Str validade-valido?),
         limite  :- (s/constrained BigDecimal (comp not neg?)),
         cliente-cpf :-  (s/constrained s/Str cpf-valido?)]
        {:id id :numero numero :cvv  cvv :validade validade :limite limite  :cliente cliente-cpf})



(def data-valida? (partial re-matches #"[0-9]{4}-[0-9]{2}-[0-9]{2}"))
(def categoria-valida? (s/enum "Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"))

(def CompraSchema
      {(s/optional-key :id) s/Int
       :data            (s/constrained s/Str data-valida?),
       :valor           (s/constrained BigDecimal (comp not neg?)),
       :estabelecimento (s/constrained s/Str string-valido?)
       :categoria       (s/constrained s/Str categoria-valida?)
       :cartao          (s/constrained s/Int Numero-de-cartao-valido?),})
;["2022-01-01" 129.90M "Outback" "Alimentação" 1234123412341234]

(s/defn ->Compra :- CompraSchema
        [id :- s/Int
         data :- (s/constrained s/Str data-valida?),
         valor :- (s/constrained BigDecimal (comp not neg?)),
         estabelecimento :- (s/constrained s/Str string-valido?)
         categoria :- (s/constrained s/Str categoria-valida?)
         cartao :- (s/constrained s/Int Numero-de-cartao-valido?)]
        {:id  id :data data :valor valor :estabelecimento estabelecimento :categoria  categoria :cartao  cartao})