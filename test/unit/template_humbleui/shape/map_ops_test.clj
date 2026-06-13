(ns template-humbleui.shape.map-ops-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.shape.map-ops :as sut]))

(deftest deep-merge-test
  (testing "merges disjoint maps"
    (is (= {:a 1 :b 2} (sut/deep-merge {:a 1} {:b 2}))))
  (testing "right wins on conflict"
    (is (= {:a 2} (sut/deep-merge {:a 1} {:a 2}))))
  (testing "recurses into nested maps"
    (is (= {:a {:b 1 :c 3}}
           (sut/deep-merge {:a {:b 1}} {:a {:c 3}})))))

(deftest select-rename-test
  (testing "selects and renames keys"
    (is (= {:x 1} (sut/select-rename {:a 1 :b 2} {:a :x}))))
  (testing "missing source key produces empty map"
    (is (= {} (sut/select-rename {} {:a :x})))))
