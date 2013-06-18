(ns clollector.parser)

(defn parse-stacktrace [stacktrace]
  (map #(% :attrs) stacktrace))

