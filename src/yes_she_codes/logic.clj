(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db]))

; recebe o array menor desestruturado e devolve os dados dos clientes;
(defn novo-cliente [nome, cpf, email]
  {:nome nome
   :cpf cpf
   :email email})

; recebe o array quebrado; e passa os dados para novo-cliente.
(defn transforma-cliente [[nome, cpf, email]]
  ;(println "nome" nome)
  ;(println "cpf" cpf)
  ;(println "email" email)
  ;(println "terminei\n")
  (novo-cliente nome, cpf, email))

; recebe o array, que é o meu banco de dados com todos os clientes. Quebra o array através do map
(defn transforma-clientes [array]
  ;(println "Array, pare 01\n" array)
  (map transforma-cliente array))

(defn listar-clientes []
  (println (transforma-clientes (y.db/busca-registros-de-clientes))))

;(listar-clientes)

(defn novo-cartao [numero, cvv, validade, limite, cliente]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

(defn transforma-cartao [[numero, cvv, validade, limite, cliente]]
  (novo-cartao numero, cvv, validade, limite, cliente))

(defn transforma-cartoes [array]
  (map transforma-cartao array))


(defn listar-cartoes []
  (println (transforma-cartoes (y.db/busca-registros-de-cartoes))))

;(listar-cartoes)

(defn nova-compra [data, valor, estabelecimento, categoria, cartão]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartão          cartão})

(defn transforma-compra [[data, valor, estabelecimento, categoria, cartão]]
  (nova-compra data, valor, estabelecimento, categoria, cartão))

(defn transforma-compras [array]
  (map transforma-compra array))

(defn listar-compras []
  (println (transforma-compras (y.db/busca-registros-de-compras))))

;(listar-compras)



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