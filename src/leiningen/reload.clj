(ns leiningen.reload
 (:use [clojure.java.io :only (file)]
       [leiningen.compile :only (eval-in-project)]
       [lein-reload.util.tracker :only (tracker)]))


(defn reload
  "Reload namespaces in modified files."
  [project]
  (let [dirs (map project [:source-path :test-path])]
    (eval-in-project project
      `(do
         (defonce
           get-namespaces#
           (tracker/tracker (map io/file '~dirs) (System/currentTimeMillis)))
         (doseq [ns-sym# (get-namespaces#)]
           (println "Reloading:" ns-sym#)
           (require ns-sym# :reload)))
       nil false '(do
                    (require '[lein-reload.util.tracker :as tracker]
                             '[clojure.java.io :as io])))))

