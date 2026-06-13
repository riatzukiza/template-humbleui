(ns template-humbleui.domain.counter-integration-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [malli.core :as m]
   [template-humbleui.domain.counter :as counter]
   [template-humbleui.law.domain-schema :as schema]))

(deftest increment-satisfies-schema-test
  (testing "increment output satisfies CounterState contract"
    (is (m/validate schema/CounterState
                    (counter/increment {:count 0})))))

(deftest stateful-sequence-test
  (testing "100 increments all satisfy CounterState"
    (let [states (reductions (fn [s _] (counter/increment s))
                              {:count 0}
                              (range 100))]
      (is (every? #(m/validate schema/CounterState %) states)))))
