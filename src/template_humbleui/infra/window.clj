(ns template-humbleui.infra.window
  "Effectful HumbleUI window bootstrap.
   This namespace is the ONLY place that requires HumbleUI rendering libs.
   Never loaded during tests."
  (:require
   [io.github.humbleui.window :as window]
   [io.github.humbleui.ui :as ui]
   [template-humbleui.ui.root :as root]))

(defn- hui-view
  "Translate pure view description into a HumbleUI component tree."
  [desc]
  (case (:type desc)
    :column  (apply ui/column
                    (map hui-view (:children desc)))
    :label   (ui/label (:text desc))
    :button  (ui/button #(swap! root/*state update :count inc)
                        (ui/label (:text desc)))
    (ui/label (str "unknown: " (:type desc)))))

(defonce *window (atom nil))

(defn start!
  "Create and show the HumbleUI window. Call from -main only."
  []
  (reset! *window
          (window/create
           {:title (:title @root/*state)
            :width 800
            :height 600}
           (fn [] (-> @root/*state root/root-view hui-view))))
  (window/show @*window))
