(ns yes-she-codes.core
  (:require [yes-she-codes.cliente :as cliente]
            [yes-she-codes.cartao :as cartao]
            [yes-she-codes.compra :as compra]))

(comment
  
  (compra/total-gasto-por-mes "02" [{:data "2022-01-01", :valor 150, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234}
                                    {:data "2022-02-01", :valor 150, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341235}
                                    {:data "2022-02-01", :valor 150, :estabelecimento "n", :categoria "n", :cartao 1234123412341236}])

  (defn teste
    ([arg1] arg1)
    ([arg1 arg2] "socorro deus")
    ([] "aaaaaaaaaa")
    )

  (defn mes-igual
    [compra]
    (if (= (subs (:data compra) 5 7) "02")
      compra))

  (defn lista-compras
    ([mes] (filter mes-igual compras))
    ([] compras))

  (defn total-gasto-errado [compras]
    (let [soma 0]
      (+ soma (:valor compras))))

  (defn total-gasto-anonima [compras]
    (reduce #(+ %1 (:valor %2)) 0 compras))

  ;(println (map total-gasto (lista-compras)))
  ;(println (total-gasto (lista-compras)))

  import exclusivo
  (ns yes-she-codes.core
    (:require [yes-she-codes.cliente :refer [a]]))

  importa tudo
  (ns yes-she-codes.core
    (:require [yes-she-codes.cliente :as X]))

  (require '[yes-she-codes.cartao])
    yes-she-codes.cartao/cartoes
  (require '[yes-she-codes.cartao :as cartao])
    cartao/cartoes
  (require '[yes-she-codes.cartao :refer [cartoes]])
    cartoes
  (require '[yes-she-codes.cartao :as cartao :refer [cartoes]])
    cartao/cartoes OU cartoes


  usar let ao chamar função dentro de função para facilitar o entendimento dos retornos
  (defn total-gasto-por-mes
    [mes compras]
    (let [lista-compras-mes (lista-compras-por-mes mes compras)]
      (total-gasto lista-compras-mes)))

  (defn total-gasto-por-mes
    [mes compras]
    (total-gasto (lista-compras-por-mes mes compras)))
  )