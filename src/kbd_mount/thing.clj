(ns kbd_mount.thing
  (:require [scad-clj.model :refer :all]
            [kbd_mount.utils :refer [render!]]))

(def top 35.5)
(def right 60)

(def top-overhang 47.61)
(def right-overhang 26)

(def bottom-thickness 10)
(def thickness 4.5)
(def height 58)

(def platform-overhang-top 50)
(def platform-overhang-right 50)

(def hole-rad (/ 3.5 2))
(def hole-clearance 5)

(def hole-offset (+ hole-rad hole-clearance))

;(def support-width 1.2)
;(def num-supports 6)

;(def sup-space (/ (- width support-width) (- num-supports 1)))
;(def support-length-top (- (/ (+ length (- lip-length-buffer lip-buffer)) 2) raft-depth (/ bar-length 2)))
;
;(def supports 
;    (translate [(- (/ width 2)) 0 0] (apply union (for [sup-mul (range 0 num-supports)]
;        (hull
;          (translate [(+ (/ support-width 2) (* sup-mul sup-space)) (+ (/ bar-length 2) raft-depth) (- (/ depth 2))] (cube support-width 0.1 depth))
;          (translate [(+ (/ support-width 2) (* sup-mul sup-space)) (+ (/ bar-length 2) raft-depth (/ support-length-top 2)) 0] (cube support-width support-length-top 0.1 ))
;        )
;    ))))
;
;(def planter_spacers
;  (union
;    (translate [0 0 (- (/ top-raft-depth 2))] (cube width bar-length top-raft-depth))
;    (translate [0 0 (/ lip-depth 2)] (cube (+ width lip-buffer) (+ length lip-length-buffer) lip-depth))
;    (translate [0 (- (- (/ bar-length 2)) (/ raft-depth 2)) (- (/ depth 2))] (cube width raft-depth depth))
;    (translate [0 (+ (/ bar-length 2) (/ raft-depth 2)) (- (/ depth 2))] (cube width raft-depth depth))
;    supports
;    (mirror [0 1 0] supports)
;  )
;)

(def hole (cylinder hole-rad (+ bottom-thickness 2) :center true))

(def mount-plate (difference
  (color [1 0 0] (cube (+ top thickness) (+ right top-overhang thickness) bottom-thickness))
  (translate [0 (- (/ thickness 2)) 0] (union
    (translate [(+ (- (/ (+ top thickness) 2)) hole-offset) 0 0] hole)
    (translate [(+ (- (/ (+ top thickness) 2)) hole-offset) (/ right 2) 0] hole)
    ;(translate [(+ (- (/ (+ top thickness) 2)) hole-offset) (- (/ right 2)) 0] hole)
  ))

  (translate [(- (/ thickness 2)) 0 0] (union
    (translate [(/ top 4) (+ (- (/ (+ right top-overhang thickness) 2)) hole-offset) 0] hole)
    (translate [(- (/ top 4)) (+ (- (/ (+ right top-overhang thickness) 2)) hole-offset) 0] hole)))

))

(def kbd_mount
  (union
    mount-plate
    (translate [(/ top 2) 0 (/ (+ height bottom-thickness) 2)] (cube thickness (+ right top-overhang thickness) height))
    (translate [0 (/ (+ right top-overhang) 2) (/ (+ height bottom-thickness) 2)] (cube (+ top thickness) thickness height))
    (translate [0 0 (+ height (/ bottom-thickness 2) (/ thickness 2))]
    (union
      (color [0 1 0] (cube (+ top thickness) (+ right top-overhang thickness) thickness))
      (translate [0 (/ (- (+ right top-overhang platform-overhang-top) thickness) 2) 0] (color [0 0 1] (cube (+ top thickness) platform-overhang-top thickness)))
      (translate [(/ (- (+ top platform-overhang-right) thickness) 2) 0 0] (color [0 0 1] (cube platform-overhang-right (+ right top-overhang thickness) thickness)))
      (translate [(/ (- (+ top platform-overhang-right) thickness) 2) (/ (- (+ right top-overhang platform-overhang-top) thickness) 2) 0] (color [0 0 1] (cube platform-overhang-right platform-overhang-top thickness)))
    ))
  ))

(defn render-things!
  []
  (render! "kbd_mount" kbd_mount))

;(render-things!)
