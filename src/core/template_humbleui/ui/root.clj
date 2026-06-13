(ns template-humbleui.ui.root
  "App state atom and pure view description.
   No HumbleUI requires — safe to load in headless tests."
  (:require
   [malli.core :as m]
   [template-humbleui.law.ui-schema :as schema]))

(defonce *state
  (atom {:title "HumbleUI Template"
         :count 0}))

(defn root-view
  "Return a plain Clojure map describing the UI. Pure function."
  [{:keys [title count] :as state}]
  {:pre [(m/validate schema/AppState state)]}
  {:type     :column
   :padding  24
   :spacing  12
   :children [{:type :label  :text title}
              {:type :label  :text (str "Count: " count)}
              {:type :button :text "Increment"}]})
