# Beeping

We love things that beeps and stresses us.

App should beep when a timer has gone to zero, meaning a bread should be taken out of the oven, or potatoes 
cooked, water is boiling etc.


## Implementation
- Generate the sound by just using `sin()` and some saturation for wider frequency range?
- Should support input of time and frequency, e.g: `listOf(Beep(ms=200, hz=440), Beep(ms=200, hz=880))`
- Probably run in a separate thread?