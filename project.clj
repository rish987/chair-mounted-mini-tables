(defproject kbd_mount "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"] 
                 [scad-clj "0.5.3"]]
  :plugins [[lein-auto "0.1.3"]
            [lein-exec "0.3.7"]]
  :main ^:skip-aot kbd_mount.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
