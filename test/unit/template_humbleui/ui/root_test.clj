(ns template-humbleui.ui.root-test
  "Unit tests for root-view pure description. No HumbleUI required."
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.ui.root :as sut]))

(def base-state {:title "Test" :count 0})

(deftest root-view-type-test
  (testing "returns a :column node"
    (is (= :column (:type (sut/root-view base-state))))))

(deftest root-view-children-test
  (let [children (:children (sut/root-view base-state))]
    (testing "has at least one :label child"
      (is (some #(= :label (:type %)) children)))
    (testing "has a :button child"
      (is (some #(= :button (:type %)) children)))))

(deftest root-view-reflects-count-test
  (testing "count is visible in a label's text"
    (let [children (:children (sut/root-view {:title "T" :count 42}))]
      (is (some #(and (= :label (:type %))
                      (clojure.string/includes? (:text %) "42"))
                children)))))

(deftest root-view-contract-test
  (testing "negative count violates :pre assertion"
    (is (thrown? AssertionError
                 (sut/root-view {:title "T" :count -1})))))
