(ns template-humbleui.shape.map-ops
  "Pure structure morphisms. No domain policy. No I/O."
  )

(defn deep-merge
  "Recursively merge maps. Right-hand values win on conflict."
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
  "Select keys from m and rename them per rename-map {from to}."
  [m rename-map]
  (reduce-kv (fn [acc from to]
               (if-let [v (get m from)]
                 (assoc acc to v)
                 acc))
             {}
             rename-map))
