(ns template-humbleui.law.ui-schema
  (:require [malli.core :as m]))

(def AppState
  [:map
   [:title :string]
   [:count [:int {:min 0}]]])
