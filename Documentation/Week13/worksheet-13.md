# COMP2000 Worksheet 2 — Final Submission

**Student name:**
Aaron Pang

**GitHub repo URL:**
https://github.com/Aaron-Pang/COMP2000-A1.git

---

## 1. Design Patterns

Don't forget to submit a pdf file which shows a diagram of your pattern implemenation to supplement this text description.

**1.1.** Which design pattern(s) did you implement? Name them and state where each appears in your codebase.
*Observer pattern*
This was used in the Sky and Plant classes.

*State pattern*
This was used in Sky, Plant, Flower, Sunflower, and Weed.





**1.2.** For each pattern, paste a code snippet showing the key interaction between participants.





**1.3.** For each pattern, explain why you chose it over an alternative. What trade-off did you make?

*Observer pattern:*
I picked the observer pattern since it seemed like it struck the best balance between efficiency and simplicity. Before learning about this design pattern, I was considering emulating it's behaviour by having each plant check the state of the sky with every tick. While this would have technically worked, I suspect it would have performed poorly, since instead of waiting for updates passively, the Growable items would all be actively checking for changes every tick, multiplied for each plant in the window.

*State pattern:*
Before learning about the state pattern, I'd implemented Plant states by simply creating abstract methods within the Plant superclass, overriding them to create specific behaviour. This was an inelegant solution, since the Sunflower has a blooming state which the Weed does not, so I would need to create an abstract bloom method in Plant, and override it to do nothing in Weed. Using the state pattern however, I was able to customise the states of one specialised object without modifying the superclass or effecting other subclasses in any way.



---

## 2. Streams / Lambdas / Parallelism

**2.1.** List every file in your project that uses lambda expressions or the Streams API.





**2.2.** For the most sophisticated use, paste the code and explain: what does it do, and why was a stream/lambda better than a loop?





**2.3.** Did you use parallel streams, custom collectors, or the Fork/Join framework? If yes, how did you ensure thread safety? If no, where could parallelism be introduced?





---

## 3. Uniqueness and Creativity

**3.1.** What did you add to your simulation that went beyond the in-class activities? Describe the feature and what inspired it.





**3.2.** Paste a code snippet or describe a design decision that demonstrates emergent behaviour, non-trivial complexity, or a genuinely surprising outcome.





**3.3.** If you had another 6 weeks, what would you add or change?





---

## 4. Log Book and Final Reflection

**4.1.** Attach or link your log book entries for Weeks 7–13.





**4.2.** Reflect on the semester:

- What was the most important concept you learned?

- What design trade-off did you face, and how did you resolve it?

- If you started over, what would you do differently?

- What feedback from the Week 7 submission did you act on?
