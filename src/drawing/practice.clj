(ns drawing.practice
    (:require [quil.core :as q]
              [quil.middleware :as m]))

(def flake (ref nil))

(def background (ref nil))

(defn setup
  "Value returned is used as initial state"
  []
  (dosync
   (ref-set flake (q/load-image "images/white_flake.png"))
   (ref-set background (q/load-image "images/blue_background.png")))
  (q/smooth)
  (q/frame-rate 60)
  [{:x 100 :swing 10 :y 10 :speed 9}
   {:x 400 :swing 5 :y 300 :speed 11}
   {:x 700 :swing 8 :y 100 :speed 8}])


(defn update-x
  "Update X"
  [x swing]
  (let [start (- x swing)
        end (+ x swing)
        new-x (+ start (rand-int (- end start)))]
    (cond
     (> 0 new-x) (q/width) ;; new-x negative => right
     (< (q/width) new-x) 0 ;; new-x greater than width => left
     :else new-x)))

(defn update-y
  [y speed]
  (if (>= y (q/height))
    0
    (+ y speed)))

(defn update
  "It will be called before draw on each frame except the first one."
  [state]
  (for [p state]
    (merge p {:x (update-x (:x p) (:swing p)) :y (update-y (:y p) (:speed p))})))

(defn draw
  "Draw function"
  [state]
  (q/background-image @background)
  (dotimes [n 3]
    (let [snowflake (nth state n)]
      (q/image @flake (:x snowflake) (:y snowflake)))))


(q/defsketch practice
  :title "Clara's Quil practice"
  :size [1000 1000]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
