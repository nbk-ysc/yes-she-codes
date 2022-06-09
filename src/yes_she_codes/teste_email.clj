(ns yes-she-codes.teste-email)

(import (org.apache.commons.validator.routines UrlValidator))

(defn -testa-email [& email]
  (let [validador (UrlValidator.)]
    (if (.isValid validador "oi")
      (println "valid"))
    (if (not (.isValid validador "oi"))
      (println "INvalid"))))

;(import (org.apache.commons.validator.routines EmailValidator))

;(defn -testa-email [& email]
;(let [validador (EmailValidator.)]
;(if (.isValid validador "oi")
; (println "valid"))
;(if (not (.isValid validador "oi"))
; (println "INvalid")) ) )
