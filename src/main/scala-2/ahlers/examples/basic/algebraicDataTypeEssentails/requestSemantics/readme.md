# Request Semantics

## Problem Statement

Suppose you have an existent service fetching records, and you need to expand how consumers can query those records. You already have a request envelope that specifies an `Option` parameter that permits unqualified returns.

You might think, "I'll add more optional parameters," and immediately, we notice a problem.

Are those parameters mutually exclusive? If so, where are the boundaries of that exclusivity? Consumers may need further clarification. `Option` has, sadly, become a widely overloaded concept with implications for everything from business logic to compatibility concerns.

Worse, how do we implement those queries? Pattern matching on a few `Option` parameters starts simple enough but doesn't scale. Worse, now we've made it easy to violate our invariants. If the consumer supplies a pair of exclusive parameters (or none at all), they've forced us to report errors that have nothing to do with our business domain but instead deal with how to use our service. What's considered a "bad request" should be narrowly defined, and we should make it difficult (or impossible) for our consumers to craft one.

## Evolving Solution

### Step 1
