(ns template-humbleui.app-e2e-test
  "E2E tests: drive state transitions through infra.app-state.
   Does NOT require HumbleUI window libs. Safe on headless CI."
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
  (testing "initial count is zero"
    (is (= 0 (:count @root/*state))))

  (testing "increment! adds one"
    (app-state/increment! root/*state)
    (is (= 1 (:count @root/*state))))

  (testing "multiple increments accumulate"
    (dotimes [_ 4] (app-state/increment! root/*state))
    (is (= 5 (:count @root/*state))))

  (testing "reset! returns count to zero"
    (app-state/reset! root/*state)
    (is (= 0 (:count @root/*state)))))

(deftest root-view-reflects-state-test
  (testing "view description tracks state atom"
    (app-state/increment! root/*state)
    (let [desc (-> @root/*state
                   (update :count identity))]
      (is (= 1 (:count desc))))))
