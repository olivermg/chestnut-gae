(ns chestnut-gae.server
  (:require [clojure.java.io :as io]
            [chestnut-gae.dev :refer [is-dev? inject-devmode-html]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [net.cgrand.enlive-html :refer [deftemplate]]
            [ring.middleware.reload :as reload]
;            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            ))

(deftemplate page (io/resource "index.html") []
  [:body] (if is-dev? inject-devmode-html identity))

(defroutes routes
  (resources "/")
  (resources "/react" {:root "react"})
  (GET "/*" req (page)))

(def http-handler
  (if is-dev?
    (reload/wrap-reload #'routes)
    routes))
