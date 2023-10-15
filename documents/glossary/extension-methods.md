# Glossary: Extension Methods

## Summary

Several terms have entered the lexicon to describe this pattern: "implicit method injection," "implicit syntax," or denoting a class with operators "rich" (_e.g._, `RichUser`), to name a few, but they're now formally known (in Scala 3) as [extension methods][scala-extension-methods].

The premise is simple: make applying functions to values more fluent without introducing methods or employing inheritance.

Let's illustrate this.

## Scala 2

While the ergonomics _feel_ natural, we generally avoid adding methods to data classes as we're essentially marrying business logic with our models (never mind how we can't easily add methods to classes we don't own except by using inheritance and [delegation][wikipedia-delegation-pattern]):

```scala
case class User(givenName: String, familyName: String) {
  def fullName: String = givenName + familyName
}

def user: User = ???
println(user.fullName)
```

We might convert that method to a function:

```scala
case class User(givenName: String, familyName: String)

def fullNameFor(user: User): String = {
  import user._
  givenName + familyName
}

def user: User = ???
println(fullNameFor(user))
```

Better; functions are great, of course, but it seems awkward. Extension methods to the rescue:

```scala
case class User(givenName: String, familyName: String)

implicit class UserOps(private val self: User) extends AnyVal {
  import self._
  def fullName: String = givenName + familyName
}

println(user.fullName)
```

We can use this pattern on _any_ class with minimal overhead and retain the ergonomics of methods while employing pure functions that respect encapsulation.

While a trivial example, you'll find this pattern helpful in all kinds of situations.

[scala-extension-methods]: https://docs.scala-lang.org/scala3/reference/contextual/extension-methods.html
[wikipedia-delegation-pattern]: https://en.wikipedia.org/wiki/Delegation_pattern
