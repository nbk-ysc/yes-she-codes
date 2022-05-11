(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db]))

(println "é a sua vez de brilhar querida, nao se atrase!")

(println "Registros-de-clientes" (y.db/busca-registros-de-clientes))

(println "Registros-de-cartoes" (y.db/busca-registros-de-cartoes))

(println "Registros-de-compras" (y.db/busca-registros-de-compras))

; começa aqui

(defn novo-cliente [nome, cpf, email]
  {:nome nome,
   :cpf cpf,
   :email email})Ł

(defn novo-cartao [numero, cvv, validade,limite,cliente]
  {:numero numero,
   :cvv cvv,
   :validade validade,
   :limite limite,
   :cliente cliente }),

(defn nova-compra [data, valor, estabelecimento,categoria,cartão]
  {:data data,
   :valor valor,
   :estabelecimento estabelecimento,
   :categoria categoria,
   :cartão cartão})




;(defn lista-clientes)
;(let [primeiro (first sequencia)])
;(if (not (nil? primeiro))
;  (do
;    (funcao primeiro)
;    (meu-mapa funcao (rest sequencia))))





; 1- Crie as funções lista-clientes (se possivel tudo que seja voltado para o cliente)
; duvida: fazer um defn lista cliente que acessa o banco e retorna todos os clientes?
; mas tenho que botar manualmente aqueles clientes da massa de dados:?

; 2-lista-cartoes (se possivel tudo que seja voltado para o cartao)
; 3-lista-compras (se possivel tudo que seja voltado para o compras)

; >>>>>> devem retornar um vetor de clientes, cartões e compras respectivamente.

; *duvida*: no caso ela deve retornar as informações que estão no banco de dados? não entendi bem o que seria esse retorno..
; um grande banco, ou as informações separadas pelas funções que criamos?

; 3-Criar uma função que, dado um mês e uma lista de compras, retorne uma lista de compras feitas somente naquele mês.
; plano: >>> acessar a lista > filtrar mes/listas

; 3-Criar uma função que, dado um estabelecimento e uma lista de compras, retorne uma lista de compras feitas somente naquele estabelecimento.
; plano: >>> acessar a lista de compras > filtrar estabelecimento

; 2-Criar a função total-gasto, que recebe uma lista de compras e retorna a soma dos valores gastos.
; plano: >>> acessar a lista de compras > filtrar gastos > somar gastos

; 2-Criar a função total-gasto-no-mes, que calcule a soma dos valores gastos num determinado cartão em um mês.

; acredito que esse seja mais simples: basta fazer a função acessar o cartao de um determinado cliente
; (usando aquele recurso de in) e depois de acessado, fazer um filter do mes vigente, e somar as compras(valores em real).

; plano: >>> acessar o cartao do cliente > filtrar mes > usar função soma p/total(seria bom criar uma funçao somente pra isso)


;dados fixos