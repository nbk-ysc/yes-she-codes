(ns yes-she-codes.semana2.model
  (:require [java-time :as jtime]))

(defprotocol Validador
  (valido? [this]))

(defrecord Cliente [id nome cpf email]
  Validador
  (valido? [this]
    (re-matches #"\d{3}\.\d{3}\.\d{3}\-\d{2}" (get this :cpf))))

(defrecord Cartao [id numero cvv validade limite cliente]
  Validador
  (valido? [this]
    (let [numero-cartao (get this :numero)
          numero-eh-valido (and (>= numero-cartao 1) (<= numero-cartao 9999999999999999))
          cvv (get this :cvv)
          cvv-eh-valido (and (>= cvv 1) (<= cvv 999))
          limite-eh-valido (> (get this :limite) 0)
          cliente-eh-valido (re-matches #"\d{3}\.\d{3}\.\d{3}\-\d{2}" (get this :cliente))]
      (and numero-eh-valido (and cvv-eh-valido (and limite-eh-valido cliente-eh-valido))))))

(defrecord Compra [id data valor estabelecimento categoria cartao]
  Validador
  (valido? [this]
    (let [data-eh-valida (jtime/not-after? (get this :data) (jtime/local-date))
          valor-eh-valido (> (get this :valor) 0)
          estabelecimento-eh-valido (>= (count (get this :estabelecimento)) 2)
          categorias-validas #{"Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"}
          categoria-eh-valida (contains? categorias-validas (get this :categoria))]
      (and data-eh-valida (and valor-eh-valido (and estabelecimento-eh-valido categoria-eh-valida))))))