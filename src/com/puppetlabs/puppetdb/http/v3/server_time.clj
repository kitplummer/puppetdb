(ns com.puppetlabs.puppetdb.http.v3.server-time
  (:require [com.puppetlabs.http :as pl-http])
  (:use [clj-time.core :only [now]]
        [clj-time.coerce :only [to-string]]
        [net.cgrand.moustache :only [app]]
        [com.puppetlabs.middleware :only [verify-accepts-json validate-no-query-params]]))

(defn server-time-response
  [req]
  (pl-http/json-response {:server-time (to-string (now))}))

(def routes
  (app
    [""]
    {:get server-time-response}))

(def server-time-app
  (-> routes
    verify-accepts-json
    validate-no-query-params))
