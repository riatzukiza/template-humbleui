(ns template-humbleui.domain.counter-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.domain.counter :as sut]))

(def valid-state {:count 0})

(deftest increment-test
  (testing "increments count"
    (is (= {:count 1} (sut/increment valid-state))))
  (testing "multiple increments compose"
    (is (= {:count 3}
           (-> valid-state
               sut/increment
               sut/increment
               sut/increment)))))

(deftest reset-counter-test
  (testing "reset returns zero"
    (is (= {:count 0} (sut/reset-counter {:count 99})))))
