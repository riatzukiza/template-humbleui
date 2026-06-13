(ns template-humbleui.shape.map-ops-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.shape.map-ops :as sut]))

(deftest deep-merge-test
  (testing "merges nested maps"
    (is (= {:a {:b 1 :c 2}}
           (sut/deep-merge {:a {:b 1}} {:a {:c 2}})))))

(deftest select-rename-test
  (testing "selects and renames keys"
    (is (= {:x 1}
           (sut/select-rename {:a 1 :b 2} {:a :x}))))
