(ns chestnut-gae.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"
                          :toggle-state {:checked false}}))

(defn checkbox-view
  [state owner {:keys [text key]}]
  (reify
    om/IRender
    (render [_]
      (dom/input #js {:type "checkbox" :checked (key state)
                      :onClick (fn [] (om/transact! state key not))}
                 text))))

(defn toggle-view
  [state owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
               (om/build checkbox-view state {:opts {:text "toggle 1" :key :checked}})
               (dom/br nil)
               (om/build checkbox-view state {:opts {:text "toggle 2" :key :checked}})))))

(defn app-view
  [app owner]
  (reify
    om/IRender
    (render [_]
      (om/build toggle-view (:toggle-state app)))))

(defn main []
  (om/root
   app-view
   app-state
   {:target (.getElementById js/document "toggle")}))

(main)
