(ns chestnut_gae.test-runner
  (:require
   [cljs.test :refer-macros [run-tests]]
   [chestnut_gae.core-test]))

(enable-console-print!)

(defn runner []
  (if (cljs.test/successful?
       (run-tests
        'chestnut_gae.core-test))
    0
    1))
