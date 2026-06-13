(ns template-humbleui.infra.app-state
  "Effectful app-state helpers."
  (:require [template-humbleui.domain.counter :as counter]))

(defn increment!
  [*state]
  (swap! *state counter/increment))

(defn reset!
  [*state]
  (swap! *state counter/reset-counter))
