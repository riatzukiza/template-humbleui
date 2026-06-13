(ns template-humbleui.app-e2e-test
  "E2E: drive state atom through infra.app-state. No HumbleUI. Headless safe."
  (:require
   [clojure.test :refer [deftest is testing use-fixtures]]
   [template-humbleui.infra.app-state :as app-state]
   [template-humbleui.ui.root :as root]))

(defn reset-state-fixture [f]
  (reset! root/*state {:title "HumbleUI Template" :count 0})
  (f)
  (reset! root/*state {:title "HumbleUI Template" :count 0}))

(use-fixtures :each reset-state-fixture)

(deftest initial-state-test
  (testing "initial count is zero"
    (is (= 0 (:count @root/*state)))))

(deftest increment-test
  (testing "increment! adds one"
    (app-state/increment! root/*state)
    (is (= 1 (:count @root/*state)))))

(deftest multi-increment-test
  (testing "four increments accumulate"
    (dotimes [_ 4] (app-state/increment! root/*state))
    (is (= 4 (:count @root/*state)))))

(deftest reset-test
  (testing "reset! returns count to zero"
    (app-state/increment! root/*state)
    (app-state/reset! root/*state)
    (is (= 0 (:count @root/*state)))))

(deftest view-reflects-state-test
  (testing "root-view description tracks *state after increment"
    (app-state/increment! root/*state)
    (let [children (:children (root/root-view @root/*state))]
      (is (some #(and (= :label (:type %))
                      (clojure.string/includes? (:text %) "1"))
                children)))))
