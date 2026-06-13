(ns build
  "tools.build uberjar.
   infra.window is included in the jar via copy-dir but NOT AOT-compiled.
   HumbleUI loads its natives at runtime; compiling them requires a GPU/EGL
   context that does not exist on headless CI runners."
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
  ;; Copy all sources — core AND humbleui — into the jar unchanged.
  (b/copy-dir {:src-dirs   ["src/core" "src/humbleui" "resources"]
               :target-dir class-dir})
  ;; AOT-compile only the headless-safe core namespaces.
  ;; infra.window is deliberately excluded: it requires HumbleUI native libs
  ;; (Skija/libEGL) which are unavailable on headless CI runners.
  ;; It is included as source and compiled on first use at runtime.
  (b/compile-clj {:basis     @basis
                  :src-dirs  ["src/core"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis     @basis
           :main      'template-humbleui.main}))
