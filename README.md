# template-humbleui

Production-grade [HumbleUI](https://github.com/HumbleUI/HumbleUI) desktop application template.

## Source layout

```
src/
  core/       — domain.*, law.*, shape.*, ui.*, infra.app-state
               No HumbleUI deps. Safe for tests and kondo.
  humbleui/   — infra.window only.
               Requires HumbleUI rendering libs.
               Only on classpath when :humbleui alias is active.
```

## Namespace Architecture

| Category | Purpose |
|---|---|
| `domain.*` | Pure business logic, no I/O |
| `law.*` | Malli schemas + `validate!`, no I/O |
| `shape.*` | Pure structure morphisms |
| `ui.*` | Pure view description maps (headless-safe) |
| `infra.app-state` | Effectful state atom transitions |
| `infra.window` | HumbleUI window bootstrap (`:humbleui` alias only) |

## Commands

```bash
# Tests (no HumbleUI dep resolved)
clojure -M:test
clojure -M:test unit
clojure -M:test integration
clojure -M:test e2e

# Lint (no HumbleUI dep resolved)
clojure -M:kondo

# Run app (resolves HumbleUI)
clojure -M:humbleui -m template-humbleui.main

# Build uberjar (resolves HumbleUI)
clojure -T:build uberjar
```

## HumbleUI SHA

Pinned to commit `ca23454728b347152f019f7bacbb2538cecbfa93`
(Skija 0.143.11 / JWM 0.4.24, 2026-03-24).
See [HumbleUI commits](https://github.com/HumbleUI/HumbleUI/commits/main)
to update.

## License

Copyright © 2026 — EPL-2.0
