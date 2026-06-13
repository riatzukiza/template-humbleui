(ns template-humbleui.app-e2e-test
  (:require
   [clojure.test :refer [deftest is testing use-fixtures]]
   [template-humbleui.infra.app-state :as app-state]
   [template-humbleui.ui.root :as root]))

(defn reset-state-fixture
  [f]
  (reset! root/*state {:title "HumbleUI Template" :count 0})
  (f)
  (reset! root/*state {:title "HumbleUI Template" :count 0}))

(use-fixtures :each reset-state-fixture)

(deftest state-transitions-test
  (testing "initial state is zero"
    (is (= 0 (:count @root/*state))))
  (testing "increment updates state"
    (app-state/increment! root/*state)
    (is (= 1 (:count @root/*state))))
  (testing "reset returns to zero"
    (app-state/reset! root/*state)
    (is (= 0 (:count @root/*state)))))
