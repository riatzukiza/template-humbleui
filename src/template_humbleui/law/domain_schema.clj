(ns template-humbleui.law.domain-schema
  "Domain contracts."
  (:require [malli.core :as m]))

(def CounterState
  [:map
   [:count [:int {:min 0}]]])

(def AppState
  [:map
   [:title :string]
   [:count [:int {:min 0}]]])

(defn validate!
  [schema value]
  (when-not (m/validate schema value)
    (throw (ex-info "Contract violation"
                    {:schema (m/form schema)
                     :value value
                     :errors (m/explain schema value)})))
  value)
