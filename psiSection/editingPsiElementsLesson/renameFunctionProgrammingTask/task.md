Sometimes, switching between Python and Kotlin leeds to following code style mistake:

Python developers usually use `snake_case` for methods naming like
```python
def sort_values(values):
```
While in Kotlin or Java `camelCase` is commonly used
```Kotlin
fun sortValues(values: List<Int>) {...}
```

**Your task will be** to help such multi-language programmers and 
implement method which renames `snake_case` named method to `camelCase`.

So before your method invocation method looked like:
```Kotlin
fun sort_values(values: List<Int>) {...}
```
but after 
```Kotlin
fun sortValues(values: List<Int>) {...}
```

<div class="hint" title="How to get project for WriteActionCommand?">

  Every PSI element has link to project, just try `psiElement.project`
</div>
