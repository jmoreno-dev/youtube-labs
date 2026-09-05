# Conventional Commits

```text
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Common Types

* `feat` — New feature
* `fix` — Bug fix
* `refactor` — Code restructuring
* `docs` — Documentation
* `test` — Tests
* `style` — Formatting
* `perf` — Performance
* `build` — Build/dependencies
* `ci` — CI/CD
* `chore` — Maintenance

### Scope


feat(user): added new variable.

### Breaking Changes

Use `!` or `BREAKING CHANGE:`.

```text
feat(api)!: change response format.
BREAKING CHANGE: the response format has changed.
```

### Examples

```text
feat(auth): add JWT authentication
fix(user): prevent duplicate registration
refactor(payment): simplify payment service
docs: update API documentation
test(user): add registration tests
ci: add GitHub Actions workflow
```
