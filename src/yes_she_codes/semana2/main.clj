(ns yes-she-codes.semana2.main
  (:require [yes-she-codes.semana2.logic :as ysc.logic]
            [yes-she-codes.semana2.db :as ysc.db]))

(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))
(def repositorio-de-compras (atom []))

(ysc.db/carrega-dados {:tipo-da-entidade :clientes,
                       :caminho          "/Users/bruna.soares/Documents/yes-she-codes-arquivos/clientes.csv"
                       :atomo            repositorio-de-clientes})

(ysc.db/carrega-dados {:tipo-da-entidade :cartoes,
                       :caminho          "/Users/bruna.soares/Documents/yes-she-codes-arquivos/cartoes.csv"
                       :atomo            repositorio-de-cartoes})

(ysc.db/carrega-dados {:tipo-da-entidade :compras,
                       :caminho          "/Users/bruna.soares/Documents/yes-she-codes-arquivos/compras.csv"
                       :atomo            repositorio-de-compras})

(ysc.logic/lista-itens! repositorio-de-clientes)

(println (ysc.logic/pesquisa-item-por-id! repositorio-de-clientes 2))

(ysc.logic/lista-itens! repositorio-de-cartoes)

(ysc.logic/lista-itens! repositorio-de-compras)

(println (ysc.logic/pesquisa-item-por-id! repositorio-de-compras 6))

(println (ysc.logic/exclui-item! repositorio-de-compras 1))