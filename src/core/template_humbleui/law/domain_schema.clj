(ns template-humbleui.law.domain-schema
  "Malli schemas for domain entities. No I/O."
  (:require [malli.core :as m]))

(def CounterState
  [:map
   [:count [:int {:min 0}]]])

(def AppState
  [:map
   [:title :string]
   [:count [:int {:min 0}]]])

(defn validate!
  "Validate value against schema; throw ex-info on failure."
  [schema value]
  (when-not (m/validate schema value)
    (throw (ex-info "Contract violation"
                    {:schema (m/form schema)
                     :value  value
                     :errors (m/explain schema value)})))
  value)
