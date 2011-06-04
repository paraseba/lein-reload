# lein-reload

Leininge plugin to reload namespaces in modified files

## Usage

Add `[lein-reload "1.0.0-SNAPSHOT"]` to your `dev-dependencies` in
`project.clj`.

Start an interactive leiningen session

    lein interactive

test your code inside the leiningen shell

    lein> test

code to make it work, then reload and re-test

    lein> reload
    lein> test

reload will be fast, since it will only reload the files you modified
since last time.

## Disclaimer

The code that detects changed files is a direct rip-off of
https://github.com/weavejester/ring-reload-modified

## License

Copyright (C) 2011 Sebastián B. Galkin

Distributed under the Eclipse Public License, the same as Clojure.
