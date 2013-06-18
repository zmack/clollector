(ns clollector.test.parser
  (:use clojure.test
        ring.mock.request  
        clollector.parser))

(deftest test-stacktrace-parsing
  (let [things '[{:tag :line,
               :attrs {:number "6", :file "(irb)", :method "irb_binding"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "80",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                :method "eval"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "80",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                :method "evaluate"},
               :content nil}]
        things-expected '[{:number "6", :file "(irb)", :method "irb_binding"}
                          {:number "80",
                           :file "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                           :method "eval"}
                          {:number "80",
                           :file "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                           :method "evaluate"}]
        
        ]
    (is (= things-expected (parse-stacktrace things) ))))

(def things '[{:tag :line,
               :attrs {:number "6", :file "(irb)", :method "irb_binding"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "80",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                :method "eval"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "80",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                :method "evaluate"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "254",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/context.rb",
                :method "evaluate"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "159",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "block (2 levels) in eval_input"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "273",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "signal_status"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "156",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "block in eval_input"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "243",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                :method "block (2 levels) in each_top_level_statement"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "229",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                :method "loop"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "229",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                :method "block in each_top_level_statement"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "228",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                :method "catch"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "228",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                :method "each_top_level_statement"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "155",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "eval_input"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "70",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "block in start"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "69",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "catch"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "69",
                :file
                "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                :method "start"},
               :content nil}
              {:tag :line,
               :attrs
               {:number "16",
                :file "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/bin/irb",
                :method "<main>"},
               :content nil}])

(def hoptoad-exception-parsed-xml '{:tag :notice,
                                    :attrs {:version "2.0"},
                                    :content
                                    [{:tag :api-key,
                                      :attrs nil,
                                      :content ["FF00FF"]}
                                     {:tag :notifier,
                                      :attrs nil,
                                      :content
                                      [{:tag :name, :attrs nil, :content ["Hoptoad Notifier"]}
                                       {:tag :version, :attrs nil, :content ["2.4.9"]}
                                       {:tag :url, :attrs nil, :content ["http://hoptoadapp.com"]}]}
                                     {:tag :error,
                                      :attrs nil,
                                      :content
                                      [{:tag :class, :attrs nil, :content ["Exception"]}
                                       {:tag :message, :attrs nil, :content ["Exception: Exception"]}
                                       {:tag :backtrace,
                                        :attrs nil,
                                        :content
                                        [{:tag :line,
                                          :attrs {:number "6", :file "(irb)", :method "irb_binding"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "80",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                                           :method "eval"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "80",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/workspace.rb",
                                           :method "evaluate"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "254",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/context.rb",
                                           :method "evaluate"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "159",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "block (2 levels) in eval_input"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "273",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "signal_status"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "156",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "block in eval_input"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "243",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                                           :method "block (2 levels) in each_top_level_statement"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "229",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                                           :method "loop"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "229",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                                           :method "block in each_top_level_statement"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "228",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                                           :method "catch"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "228",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb/ruby-lex.rb",
                                           :method "each_top_level_statement"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "155",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "eval_input"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "70",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "block in start"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "69",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "catch"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "69",
                                           :file
                                           "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/lib/ruby/1.9.1/irb.rb",
                                           :method "start"},
                                          :content nil}
                                         {:tag :line,
                                          :attrs
                                          {:number "16",
                                           :file "/home/zmack/.rvm/rubies/ruby-1.9.3-p385/bin/irb",
                                           :method "<main>"},
                                          :content nil}]}]}
                                         {:tag :server-environment,
                                          :attrs nil,
                                          :content
                                          [{:tag :project-root,
                                            :attrs nil,
                                            :content ["/home/zmack/work/foobar"]}
                                           {:tag :environment-name, :attrs nil, :content ["development"]}]}]})
