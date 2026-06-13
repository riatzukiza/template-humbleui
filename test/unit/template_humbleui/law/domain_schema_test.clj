(ns template-humbleui.law.domain-schema-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [malli.core :as m]
   [template-humbleui.law.domain-schema :as sut]))

(deftest counter-state-schema-test
  (testing "accepts valid state"
    (is (m/validate sut/CounterState {:count 0})))
  (testing "rejects invalid state"
    (is (not (m/validate sut/CounterState {:count -1})))))

(deftest validate!-test
  (testing "returns valid values"
    (is (= {:count 1} (sut/validate! sut/CounterState {:count 1}))))
  (testing "throws for invalid values"
    (is (thrown? clojure.lang.ExceptionInfo
                 (sut/validate! sut/CounterState {:count -1})))))
