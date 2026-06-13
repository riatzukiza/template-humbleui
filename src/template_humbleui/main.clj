(ns template-humbleui.main
  (:gen-class)
  (:require
   [template-humbleui.infra.window :as win]))

(defn -main
  [& _args]
  (win/start!))
