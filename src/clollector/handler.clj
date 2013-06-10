(ns clollector.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as response]
            [clojure.pprint :as pprint]
            [clojure.xml :as xml]))

(def ^:private counter (atom 0))
(def ^:private mappings (ref {}))

(defn url-for
  [id]
  (@mappings id))

(defn shorten!
  "Stores the given URL under a new unique identifier, or the given identifier
  if provided. Returns the identifier as a string.
  Modifies the global mapping accordingly."
  ([url]
   (let [id (swap! counter inc)
         id (Long/toString id 36)]
     (or (shorten! url id)
         (recur url))))
  ([url id]
   (dosync
           (when-not (@mappings id)
             (alter mappings assoc id url)
             id))))

(defn retain
  [& [url id :as args]]
  (if-let [id (apply shorten! args)]
    {:status 201
     :headers {"Location" id}
     :body (list "URL " url " assigned the short identifier " id)}
    {:status 409 :body (format "Short URL %s is already taken" id)}))

(defn redirect
  [id]
  (if-let [url (url-for id)]
    (response/redirect url)
    {:status 404 :body (str "No such short URL: " id)}))

(defn log-exception
  "Logs a hoptoad-style exception"
  [body]
  (pprint/pprint (xml/parse body)))

(defn simple-logging-middleware
  "logs the current request"
  [app]
  (fn [req]
    (println req)
    (app req)))

(defroutes app-routes
  (GET "/" request "Welcome!")
  (PUT "/:id" [id url] (retain url id))
  (POST "/" [url] (if (empty? url)
                    {:status 400 :body "No `url` parameter provided"}
                    (retain url)))
  (GET "/:id" [id] (redirect id))
  (GET "/list/" [] (interpose "\n" (keys @mappings)))
  (POST "/notifier_api/v2/notices/" {body :body} (log-exception body))

  (compojure.route/not-found "Sorry, there's nothing here."))

(def app
  (handler/site (-> app-routes simple-logging-middleware)))
