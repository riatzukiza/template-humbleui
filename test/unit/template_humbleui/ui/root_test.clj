(ns template-humbleui.ui.root-test
  "Unit tests for root-view pure description function.
   Does NOT load HumbleUI — safe headless."
  (:require
   [clojure.test :refer [deftest is testing]]
   [template-humbleui.ui.root :as sut]))

(def base-state {:title "Test App" :count 0})

(deftest root-view-shape-test
  (testing "returns a map with :type :column"
    (let [desc (sut/root-view base-state)]
      (is (= :column (:type desc)))))
  (testing "children contains label and button entries"
    (let [children (:children (sut/root-view base-state))]
      (is (seq children))
      (is (some #(= :label (:type %)) children))
      (is (some #(= :button (:type %)) children))))
  (testing "count is reflected in a label text"
    (let [children (:children (sut/root-view {:title "T" :count 42}))]
      (is (some #(and (= :label (:type %))
                      (clojure.string/includes? (:text %) "42"))
                children)))))

(deftest root-view-contract-test
  (testing "throws on invalid state"
    (is (thrown? AssertionError
                 (sut/root-view {:title "T" :count -1})))))
