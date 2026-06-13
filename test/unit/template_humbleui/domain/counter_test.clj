(ns template-humbleui.domain.counter-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.domain.counter :as sut]))

(def ^{:doc "Minimal valid CounterState for use across tests."}
  valid-state {:count 0})

(deftest increment-test
  (testing "increments count by 1"
    (is (= {:count 1} (sut/increment valid-state))))
  (testing "composes across multiple calls"
    (is (= {:count 3}
           (-> valid-state
               sut/increment
               sut/increment
               sut/increment)))))

(deftest reset-counter-test
  (testing "resets to zero"
    (is (= {:count 0} (sut/reset-counter {:count 42})))))
