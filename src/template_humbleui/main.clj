(ns template-humbleui.main
  (:gen-class)
  (:require
   [template-humbleui.ui.root :as root]))

(defn -main
  [& _args]
  (root/start!))
