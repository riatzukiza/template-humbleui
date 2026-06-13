(ns template-humbleui.infra.app-state
  "Effectful state transitions."
  (:require
   [template-humbleui.domain.counter :as counter]))

(defn increment!
  "Increment count in the given state atom."
  [*s]
  (swap! *s counter/increment))

(defn reset!
  "Reset count in the given state atom to zero."
  [*s]
  (swap! *s counter/reset-counter))
