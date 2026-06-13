(ns build
  "tools.build uberjar — includes :humbleui alias so infra.window compiles."
  (:require
   [clojure.tools.build.api :as b]))

(def lib       'com.example/template-humbleui)
(def version   (format "0.1.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")
(def basis     (delay (b/create-basis {:project "deps.edn"
                                       :aliases  [:humbleui]})))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uberjar [_]
  (clean nil)
  (b/copy-dir {:src-dirs   ["src/core" "src/humbleui" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis     @basis
                  :src-dirs  ["src/core" "src/humbleui"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis     @basis
           :main      'template-humbleui.main}))
