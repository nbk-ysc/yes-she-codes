(ns yes-she-codes.util)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn deve-remover? [id item]
  (->> item
       :id
       (= id)))


;FUNCOES DE VALIDACAO
(defn maior-2-caracteres? [palavra]
  (< 2 (count palavra)))

(defn decimal-maior-igual-0? [numero]
  (and (decimal? numero) (<= 0 numero)))

(defn numero-entre-0-999? [numero]
  (and (>= 999 numero) (<= 0 numero)))

(defn expressaoregular? [expressao valor]
  (re-matches expressao valor))

(defn valida-cpf? [cpf]
  (def pat (re-pattern "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}"))
  (and (= 14 (count cpf)) (not (nil? (re-find pat cpf)))))

(defn valida-email? [email]
  (def pat (re-pattern "\\S+@\\S+\\.\\S+"))
  (not (nil? (re-find pat email))))

(defn valida-data-validade? [data]
  (def pat (re-pattern "[0-9]{4}-[0-9]{2}"))
  (and (= 7 (count data)) (not (nil? (re-find pat data)))))

(defn valida-data-compra? [data]
  (def pat (re-pattern "[0-9]{4}-[0-9]{2}-[0-9]{2}"))
  (and (= 10 (count data)) (not (nil? (re-find pat data)))))

(defn valida-categorias? [categoria]
  (contains? #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"} categoria))
