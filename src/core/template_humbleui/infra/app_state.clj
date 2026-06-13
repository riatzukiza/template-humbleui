(ns template-humbleui.infra.app-state
  "Effectful state transitions over a state atom."
  (:require
   [template-humbleui.domain.counter :as counter]))

(defn increment!
  "Increment count in *state atom."
  [*s]
  (swap! *s counter/increment))

(defn reset!
  "Reset count in *state atom to zero."
  [*s]
  (swap! *s counter/reset-counter))
