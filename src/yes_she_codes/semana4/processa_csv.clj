(ns yes-she-codes.semana4.processa-csv
  (:require [yes_she_codes.dados.compras :as dados]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " ""))) ; Função pra converter o número do cartão: remove espaços do meio e converte para Long

(def csv->compra [java-time/local-date ; usa essa função no primeiro elemento pra transformar em LocalDate
                  bigdec               ; usa essa função no segundo elemento pra transformar em BigDecimal
                  identity             ; o terceiro elemento é uma String mesmo, então devolve o valor original
                  identity             ; o quarto elemento também é uma String, então devolve o valor original
                  str->long])          ; usa essa função no quinto elemento pra transformar o número do cartão para um Long

(defn converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))


(defn processa-arquivo-de-compras! [dados/compras.csv]
  (->> (slurp dados/compras.csv)               ; pega a string do arquivo
       clojure.string/split-lines            ; cria uma lista com cada linha em uma posição
       rest                                  ; retorna uma lista sem o primeiro elemento (linha de cabeçalho)
       (map #(clojure.string/split % #","))  ; cria um vetor com cada valor separado por vírgula em um índice
       (map #(converte-valores-na-linha csv->compra %) ; converte cada uma das linhas
            (map #(apply nova-compra %)))))
