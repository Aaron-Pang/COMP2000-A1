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

*Observer pattern*
@Override
public void checkChange() {
    if(sky.hour >= 12000) {
        if((int) (Math.random() * 3) == 0) {
            //Change to storm
            sky.state = sky.stormyState;
        } else {
            //Change to night
            sky.state = sky.nightState;
        }
        sky.notifyObservers();
    }
}

@Override
public void update(String timeState, int hour){
    environmentState = timeState;
}

The function checkChange() is part of one of the Sky's states. When the sky switches state, it will call Sky's notifyObservers(), which in turn will call update() for every observer in it's registered list. The snippet of update() is in Sunflower.java, which will update the Sunflower's data so it can behave differently depending on what the time/weather is in the sky. Technically, the observer pattern in the textbook also states that the observer should keep a reference to the object it is observing. While my objects do have this, it is separate from the design pattern, since this functionality was not needed for my implementation of the design pattern.

*State pattern*

These snippets of code demonstrate how the Sky changes time/weather states (this design pattern was also implemented for Growables, which have different stages of growth).



**1.3.** For each pattern, explain why you chose it over an alternative. What trade-off did you make?

*Observer pattern:*
I picked the observer pattern since it seemed like it struck the best balance between efficiency and simplicity. Before learning about this design pattern, I was considering emulating it's behaviour by having each plant check the state of the sky with every tick. While this would have technically worked, I suspect it would have performed poorly, since instead of waiting for updates passively, the Growable items would all be actively checking for changes every tick, multiplied for each plant in the window.

*State pattern:*
Before learning about the state pattern, I had implemented Plant states by simply creating abstract methods within the Plant superclass, overriding them to create specific behaviour. This was an inelegant solution, since the Sunflower has a blooming state which the Weed does not, so I would need to create an abstract bloom method in Plant, and override it to do nothing in Weed. Using the state pattern however, I was able to customise the states of one specialised object without modifying the superclass or effecting other subclasses in any way.



---

## 2. Streams / Lambdas / Parallelism

**2.1.** List every file in your project that uses lambda expressions or the Streams API.
- Ground.java
- LightningRadiusCollector.java






**2.2.** For the most sophisticated use, paste the code and explain: what does it do, and why was a stream/lambda better than a loop?

public void tick() {
        ArrayList<Growable> dead = getDead();
        getGrowables().stream().forEach(i -> i.tick());
        impacts.stream().forEach(i -> i.tick());
        dead.stream().forEach(i -> delete(i));
        getEmptyPatches().stream().forEach(i -> delete(i));
        repaint();
        revalidate();
}

In this example, streams and lambdas significantly reduce the number of lines of code required to call all the functions for each object in the window every tick. Without them, I would need to write a loop for the Growables, ImpactPoints, and Patches, but here each of these loops becomes a single line, substantially increasing readability.

**2.3.** Did you use parallel streams, custom collectors, or the Fork/Join framework? If yes, how did you ensure thread safety? If no, where could parallelism be introduced?

My program implements parallel streams and a custom collector.

Concerning the parallel streams, I picked out a number of areas in which I read from a list to convert the collection of which to a stream, such as calling the tick() function in plants to let them perform their logic every tick. Some areas were not suitable for parallel streams, such as having objects remove themselves from the window, since this would run into concurrent modification errors/race conditions.

I created a custom collector to filter for items that were within a certain distance from a point, used when lightning strikes to determine which Growable objects should be destroyed. It creates a Map<Boolean, List<Growable>>, where true items will be removed. This collector is essentially a filter, but implements the supplier, accumulator, combiner, finisher, and characteristics in such a way that it would be easy to modify the behavour of the collector.

---

## 3. Uniqueness and Creativity

**3.1.** What did you add to your simulation that went beyond the in-class activities? Describe the feature and what inspired it.

Despite beginning implementation of the Lightning class in class, I modified it outside of class to make use of a custom collector after reading the textbook, and gaining an understanding of the 5 main features it needed. I decided that how my Lightning class filtered objects was simple enough for me to 



**3.2.** Paste a code snippet or describe a design decision that demonstrates emergent behaviour, non-trivial complexity, or a genuinely surprising outcome.





**3.3.** If you had another 6 weeks, what would you add or change?





---

## 4. Log Book and Final Reflection

**4.1.** Attach or link your log book entries for Weeks 7–13.





**4.2.** Reflect on the semester:

- What was the most important concept you learned?
I feel that design patterns were the most important concept I learnt. I feel more able to find and interpret design patterns outside of the ones taught in this unit, and the structure they provide programs seems to very often be valuable in terms of code maintainability. They were the most impactful on how I designed this program, since I how I would think to do so without them turned out to be much more complicated than it needed to be with design patterns.

- What design trade-off did you face, and how did you resolve it?
I hesitated while implementing the state pattern, since although it made the code substantially cleaner, it also introduced a lot more individual java files into the project. While this was difficult to manage at first, I figured out how to split a java project into folders/packages to mitigate this issue.

- If you started over, what would you do differently?
Were I to start over, I would make the simulation grid-based, since I feel this would have made development easier, allow me to more easily integrate certain rubric points, and made it more clear to viewers of the simulation how plants grow and spread. I would also look into design patterns outside of the ones taught by this unit, since there are areas in the program that I feel like have higher coupling than necessary, but wasn' able to figure out how to solve myself.

- What feedback from the Week 7 submission did you act on?
I increased the detail my logbook entries went into, including reflections on how I could implement class content into my program and how this would be useful to me or in general.
