Sometimes, switching between Python and Kotlin leads to the following code style mistake:

Python developers usually use `snake_case` for method naming, like:
```python
def sort_values(values):
```
In contrast, Kotlin and Java commonly use `camelCase`:
```Kotlin
fun sortValues(values: List<Int>) {...}
```

**Your task will be** to assist multi-language programmers by
implementing a method that renames `snake_case` methods to `camelCase`.

Before your method invocation, the method might look like:
```Kotlin
fun sort_values(values: List<Int>) {...}
```
After:
```Kotlin
fun sortValues(values: List<Int>) {...}
```

<div class="hint" title="How to get the project for WriteActionCommand?">

Every PSI element has a link to the project; just try `psiElement.project`.
</div>
