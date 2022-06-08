(ns yes_she_codes.semana4.models.model
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]
            [schema.core :as s]
            [java-time :as time]
            [yes_she_codes.semana4.utils :as y.utils]))

