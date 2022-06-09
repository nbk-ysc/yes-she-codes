(defproject yes-she-codes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :username "camila.costa@nubank.com.br"
                                   :password "d2879d10-8c20-4acb-9a60-4222725722e9"}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clojure.java-time "0.3.3"]
                 [prismatic/schema "1.2.0"]
                 [com.datomic/datomic-pro "1.0.6344"]]
  :repl-options {:init-ns yes-she-codes.core})
