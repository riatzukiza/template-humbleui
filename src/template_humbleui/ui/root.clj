(ns template-humbleui.ui.root
  "Top-level HumbleUI root view and app loop."
  (:require
   [io.github.humbleui.window :as window]
   [io.github.humbleui.ui :as ui]
   [malli.core :as m]
   [template-humbleui.law.ui-schema :as schema]))

(defonce *state
  (atom {:title "HumbleUI Template"
         :count 0}))

(defn root-view
  [{:keys [title count]}]
  {:pre [(m/validate schema/AppState {:title title :count count})]}
  (ui/padding 24
    (ui/column
      (ui/label (str title))
      (ui/gap 12 12)
      (ui/label (str "Count: " count))
      (ui/button #(swap! *state update :count inc)
        (ui/label "Increment")))))

(defonce *window (atom nil))

(defn start!
  []
  (reset! *window
          (window/create
            {:title (:title @*state)
             :width 800
             :height 600}
            (fn [] (root-view @*state))))
  (window/show @*window))
