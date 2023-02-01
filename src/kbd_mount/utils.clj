(ns kbd_mount.utils
  (:require [clojure.java.io :refer [make-parents]]
            [scad-clj.scad :refer [write-scad]]))

(defn render!
  [file-name & part]
  (let [file-path (str "out/" file-name ".scad")]
    (make-parents file-path)
    (spit file-path
      (write-scad part))))
