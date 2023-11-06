package tutorial.monads.flightItinerary.common

/**
 * A simple [[https://en.wikipedia.org/wiki/Record_locator|record locator]] for an airline reservation. Not strictly conformant to the standard, but good enough for our purposes.
 *
 * @see [[https://en.wikipedia.org/wiki/Record_locator]]
 */
case class Locator(toText: String) extends AnyVal
