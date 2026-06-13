(ns template-humbleui.law.domain-schema-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [malli.core :as m]
   [template-humbleui.law.domain-schema :as sut]))

(deftest counter-state-schema-test
  (testing "accepts valid state"
    (is (m/validate sut/CounterState {:count 0})))
  (testing "rejects negative count"
    (is (not (m/validate sut/CounterState {:count -1}))))
  (testing "rejects missing count key"
    (is (not (m/validate sut/CounterState {})))))

(deftest validate!-test
  (testing "returns value on success"
    (is (= {:count 5} (sut/validate! sut/CounterState {:count 5}))))
  (testing "throws ex-info on violation"
    (is (thrown? clojure.lang.ExceptionInfo
                 (sut/validate! sut/CounterState {:count -1})))))
