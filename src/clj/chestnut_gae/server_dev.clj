(ns chestnut-gae.server-dev
  (:require [chestnut-gae.server :as server]
            [chestnut-gae.dev :refer [is-dev? browser-repl start-figwheel]]
            [net.cgrand.reload :refer [auto-reload]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn run-web-server [& [port]]
  (let [port (Integer. (or port (env :port) 10555))]
    (println (format "Starting web server on port %d." port))
    (run-jetty server/http-handler {:port port :join? false})))

(defn run-auto-reload [& [port]]
  (auto-reload 'chestnut-gae.server)
  (start-figwheel))

(defn run [& [port]]
  (when is-dev?
    (run-auto-reload))
  (run-web-server port))

(defn -main [& [port]]
  (run port))
