(ns template-humbleui.domain.counter
  "Pure counter domain logic."
  (:require
   [malli.core :as m]
   [template-humbleui.law.domain-schema :as schema]))

(defn increment
  [{:keys [count] :as state}]
  {:pre [(m/validate schema/CounterState state)]
   :post [(m/validate schema/CounterState %)]}
  (assoc state :count (inc count)))

(defn reset-counter
  [state]
  {:pre [(m/validate schema/CounterState state)]
   :post [(m/validate schema/CounterState %)]}
  (assoc state :count 0))
