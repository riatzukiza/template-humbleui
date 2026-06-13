# template-humbleui

Production-grade [HumbleUI](https://github.com/HumbleUI/HumbleUI) desktop application template for Clojure.

## Features

- **HumbleUI** desktop rendering stack
- **Malli** boundary contracts
- **clj-kondo** zero-warning linting
- **kaocha** unit, integration, and e2e suites
- **tools.build** uberjar build
- **GitHub Actions** lint, test, and build workflows

## Namespace Architecture

| Category | Purpose |
|---|---|
| `domain.*` | Pure business logic, no I/O |
| `infra.*` | Effectful boundaries and adapters |
| `shape.*` | Pure structure transforms |
| `law.*` | Contracts, Malli schemas, validators |
| `ui.*` | HumbleUI component descriptions and rendering glue |

## Commands

```bash
clojure -M:test
clojure -M:test unit
clojure -M:test integration
clojure -M:test e2e
clojure -M:kondo
clojure -T:build uberjar
```
