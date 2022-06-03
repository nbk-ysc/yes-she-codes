(defproject yes-she-codes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :username "caroline.dian@nubank.com.br"
                                   :password "54e16b56-3536-46a8-a33c-54eddb43e254"}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [prismatic/schema "1.1.12"]
                 [com.datomic/datomic-pro "1.0.6397"]
                 [org.slf4j/slf4j-nop "1.7.32"]]

  :repl-options {:init-ns yes_she_codes.semana4.core})
