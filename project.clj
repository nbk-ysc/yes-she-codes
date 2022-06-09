(defproject yes-she-codes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
            :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                             :username "renata.coraiola@nubank.com.br"
                                             :password "91ae7f48-5a6f-4b9e-8a4b-c7f103069b1f"}}
            :dependencies [[org.clojure/clojure "1.10.1"]
                           [prismatic/schema "1.2.1"]
                           [com.datomic/datomic-pro "1.0.6397"]
                           [org.slf4j/slf4j-nop "1.7.32"]]
  :repl-options {:init-ns yes-she-codes.core})
