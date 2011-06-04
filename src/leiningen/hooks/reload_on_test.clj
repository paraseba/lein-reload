(ns leiningen.hooks.reload-on-test
  (:refer-clojure :exclude [test])
  (:use [robert.hooke :only (add-hook)]
        (leiningen [reload :only (reload)]
                   [test   :only (test)])))

(defn- prepend-reload [f & args]
  (apply reload args)
  (apply f args))

(add-hook #'test prepend-reload)

