(ns leiningen.reload
 (:use [leiningen.compile :only (eval-in-project)]))

(defn reload
  "Reload namespaces in modified files.
  It will watch :source-path and :test-path for changes.
  You can pass extra directories as command line arguments."
  [project & extra-dirs]
  (let [dirs (concat (map project [:source-path :test-path]) extra-dirs)]
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

