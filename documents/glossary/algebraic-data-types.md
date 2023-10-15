# Glossary: Algebraic Data Types

## Summary

Algebraic Data Types (ADT) is a topic, when using Scala, **you must know.** Luckily, we can put this plain and simple:

They're a way of structuring data that reduces all possible values to a small number we can reason about. More importantly, **they're another use of the type system to make invalid states impossible to represent.**

While hardly a concept specific to Scala, the language supports them naturally, and the compiler uses them to prevent bugs chiefly through exhaustive pattern matching (_i.e._, any match statement is guaranteed to consider all possible cases).

## Further Reading

There are volumes of quality material on Algebraic Data Types, so rather than repeat better authors here, the reader can refer to these articles:

- [_Algebraic Data Types (ADT) in Scala_ by Daniel Ciocîrlan for "Rock the JVM"](https://blog.rockthejvm.com/algebraic-data-types/)
- [_Algebraic Data Types in Scala_ for "Baeldung"](https://www.baeldung.com/scala/algebraic-data-types)
- [_Algebraic Data Types_ by Nicolas Rinaudo](https://nrinaudo.github.io/scala-best-practices/definitions/adt.html)
- [_Algebraic Data Types in Scala_ by Shannon Barnes](https://medium.com/@shannonbarnes_85491/algebraic-data-types-in-scala-701f3227fe91)

Any of these will provide the reader with a thorough understanding of this essential topic. 
