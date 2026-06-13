(ns template-humbleui.shape.map-ops
  "Pure structure transforms. No domain policy."
  )

(defn deep-merge
  [& maps]
  (reduce (fn [acc m]
            (if (map? m)
              (reduce-kv (fn [a k v]
                           (assoc a k (if (and (map? (get a k)) (map? v))
                                        (deep-merge (get a k) v)
                                        v)))
                         acc m)
              acc))
          {}
          maps))

(defn select-rename
  [m rename-map]
  (reduce-kv (fn [acc from to]
               (if-let [v (get m from)]
                 (assoc acc to v)
                 acc))
             {}
             rename-map))
