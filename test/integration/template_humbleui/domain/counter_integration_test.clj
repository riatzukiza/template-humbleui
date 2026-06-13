(ns template-humbleui.domain.counter-integration-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [malli.core :as m]
   [template-humbleui.domain.counter :as counter]
   [template-humbleui.law.domain-schema :as schema]))

(deftest increment-satisfies-schema-test
  (testing "increment preserves contract"
    (is (m/validate schema/CounterState
                    (counter/increment {:count 0})))))
