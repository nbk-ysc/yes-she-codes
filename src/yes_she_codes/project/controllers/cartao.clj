;(ns yes-she-codes.project.controllers.cartao
;  (:require [schema.core :as s]
;            [yes-she-codes.project.model.cartao :as model.cartao]
;            [yes-she-codes.project.logic.common.common :as logic.common]))
;
;(s/defn insere-cartao! :- model.cartao/Cartoes
;  [cartoes :- model.cartao/Cartoes
;   registro :- model.cartao/Cartao]
;  (swap! cartoes logic.common/insere-registro registro))
;
;(s/defn lista-cartoes!
;  [cartoes :- model.cartao/Cartoes]
;  (logic.common/lista-entidade @cartoes))
;
;(s/defn pesquisa-cartao-por-id! :- model.cartao/Cartao
;  [cartoes :- model.cartao/Cartoes
;   id :- s/Num]
;  (logic.common/pesquisa-registro-por-id @cartoes id))
;
;(s/defn exclui-cartao! :- model.cartao/Cartoes
;  [cartoes :- model.cartao/Cartoes
;   id :- s/NUm]
;  (swap! cartoes logic.common/exclui-registro id))
