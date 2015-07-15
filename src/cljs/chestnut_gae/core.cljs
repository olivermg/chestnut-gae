(ns chestnut-gae.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))

(defn main []

  (om/root
    (fn [app owner]
      (reify
        om/IRender
        (render [_]
          (dom/h1 nil (:text app)))))
    app-state
    {:target (. js/document (getElementById "app"))})

  (om/root
   (fn [data owner]
     (om/component
      (dom/h2 nil (:text data))))
   app-state
   {:target (. js/document (getElementById "app1"))}))
