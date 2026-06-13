(ns template-humbleui.law.ui-schema
  "Malli schemas for UI-layer data shapes."
  )

(def AppState
  [:map
   [:title :string]
   [:count [:int {:min 0}]]])
