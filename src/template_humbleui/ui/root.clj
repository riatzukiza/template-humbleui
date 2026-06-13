(ns template-humbleui.ui.root
  "Top-level app state atom and pure view description.
   HumbleUI rendering is invoked only via infra.window — never loaded here."
  (:require
   [malli.core :as m]
   [template-humbleui.law.ui-schema :as schema]))

(defonce *state
  (atom {:title "HumbleUI Template"
         :count 0}))

(defn root-view
  "Pure description of app state as a UI data tree.
   Does NOT require any HumbleUI namespace — safe for headless test loads."
  [{:keys [title count] :as state}]
  {:pre [(m/validate schema/AppState state)]}
  {:type    :column
   :padding 24
   :spacing 12
   :children [{:type :label :text title}
              {:type :label :text (str "Count: " count)}
              {:type :button :text "Increment"}]})
