# Algebraic Data Type Essentials

I promised I wouldn't needlessly bury my reader with theory, but here's one you must know. However, rather than condescend and say (as someone once told me), "You ideally model your domain as an algebra," I'm going to put this plain and simple:

It's a way of trimming down too many cases you can't reason about into a small number of cases you can reason about.

While hardly a concept specific to Scala, the language makes it easy to define an ADT, and the compiler uses them to prevent bugs. At the same time, they clarify exactly the intend of your interface and the behavior of your logic.
