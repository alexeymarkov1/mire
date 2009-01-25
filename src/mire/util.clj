(ns mire.util
  (:use [clojure.contrib seq-utils]))

(defn remove-first
  "Returns a lazy seq of the items in coll excluding the first for
  which (pred item) returns false. pred must be free of side-effects."
  [pred coll]
  (when (seq coll)
      (if (pred (first coll))
        (rest coll)
        (lazy-cons (first coll) (remove pred (rest coll))))))

(defn move-between-refs
  "Move one instance of obj between ref1 and ref2. Must be called in a transaction."
  [obj from to]
  (commute from (partial remove-first #(= % obj)))
  (commute to conj obj))

(defn pick-rand
  "Return a random element of vect."
  [vect]
  (vect (rand-int (count vect))))
