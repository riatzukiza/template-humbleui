(ns template-humbleui.infra.window
  "HumbleUI window bootstrap.
   ONLY place that requires HumbleUI rendering libs.
   Lives in src/humbleui — only on classpath with :humbleui alias."
  (:require
   [io.github.humbleui.window :as window]
   [io.github.humbleui.ui :as ui]
   [template-humbleui.infra.app-state :as app-state]
   [template-humbleui.ui.root :as root]))

(defn- ->hui
  "Walk a pure view description map into a HumbleUI component."
  [{:keys [type text children] :as desc}]
  (case type
    :column  (apply ui/column (map ->hui children))
    :label   (ui/label text)
    :button  (ui/button #(app-state/increment! root/*state)
                        (ui/label text))
    (ui/label (str "unknown node: " type))))

(defonce *window (atom nil))

(defn start!
  "Create and show the HumbleUI application window."
  []
  (reset! *window
          (window/create
           {:title  (:title @root/*state)
            :width  800
            :height 600}
           (fn [] (->hui (root/root-view @root/*state)))))
  (window/show @*window))
