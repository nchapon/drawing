(ns drawing.lines
  (:require [quil.core :as q]))

(defn setup
  "SetUp QUIL"
  []
  (q/frame-rate 90)
  (q/color-mode :rgb)
  (q/stroke 64 64 64)
  (q/stroke-weight 20)
  )

(defn draw
  "Draw something"
  []
  (q/point (q/mouse-x) (q/mouse-y))
  )


(q/defsketch hello-lines
                 :title "You can see lines"
                 :size [500 500]
                 :setup setup
                 :draw draw)
