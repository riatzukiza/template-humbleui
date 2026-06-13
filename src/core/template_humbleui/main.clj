(ns template-humbleui.main
  "Entry point. Requires infra.window which lives in src/humbleui
   and is only on the classpath when started with the :humbleui alias."
  (:gen-class)
  (:require
   [template-humbleui.infra.window :as win]))

(defn -main
  [& _args]
  (win/start!))
