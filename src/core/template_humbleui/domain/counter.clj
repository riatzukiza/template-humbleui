(ns template-humbleui.domain.counter
  "Pure counter domain logic. No I/O."
  (:require
   [malli.core :as m]
   [template-humbleui.law.domain-schema :as schema]))

(defn increment
  "Increment the counter state by 1."
  [state]
  {:pre  [(m/validate schema/CounterState state)]
   :post [(m/validate schema/CounterState %)]}
  (update state :count inc))

(defn reset-counter
  "Reset counter to zero."
  [state]
  {:pre  [(m/validate schema/CounterState state)]
   :post [(m/validate schema/CounterState %)]}
  (assoc state :count 0))
