# COMP2000 Worksheet 1 — Mid-Semester Submission

**Student name: Aaron Pang**

**Student ID: 49071378**

**GitHub repo URL: https://github.com/Aaron-Pang/COMP2000-A1/tree/main**

---

## 1. Version Control

**1.1.** Paste the first 10 lines of the output of `git log --graph --oneline --all` from your repository:

```




```

**1.2.** Describe your workflow. Did you use branches? Pull requests?

When working on the project, code would be pushed after a specific feature or certain progress on a feature was made.
These changes were then able to be pulled by other members so their local repositories could remain updated.
A branch would be used when a change that was expected to take a longer time to implement was used, to avoid conflicts
between this change and routine changes in the main branch.



**1.3.** Estimate the percentage of commits you contributed relative to the total in your repository.

I estimate I contributed about 90% of the commits in my repository.

---

## 2. Program Design

**2.1.** List every class in your project and write 1–2 sentences describing its responsibility.

*Interfaces*

Growable.java: Contains function protoypes for functions that all Growable objects should have, like spread(), tick(), getState(), and others.

PlantState.java: Contains function prototypes for the states a Plant can have, including the ability to check and change the plant's state.

SkyState.java: Similar to PlantState, but functions are specialised for the states the Sky can be.

SkyObserver.java: Contains an update function called on objects observing the Sky.

SkySubject.java: Contains function prototypes used by the Sky as a subject that is observed.

The following is organised by folder.

*placed_objects/sky*
Sky.java: This is the topmost blue bar in the window containing the sun. It keeps track of time and the state of time in the project, and updates objects observing it when the time state changes.

SunnyState.java: The sky's state when it is sunny. It keeps the sky blue and sun yellow during the day, and switches to night at the right time, updating observers when it does so.

NightState.java: The sky's state at night. Turns the sky black and makes the moon white, and updates observers when it becomes day again.

*placed_objects*
Ground.java: This is the green panel at the bottom of the window that other objects like plants and patches are added to. Each time the simulation updates, it calls the relevant functions of all objects placed in it, and features some supplementary methods to assist with this.

Patch.java: This is an object that can be placed on the ground, and acts like a collection of Growable objects. Objects in the collection are more fertile, and can produce more seeds that they would otherwise be able to.


*supplementary*
Direction.java: A basic class that holds an x and y value used by some plants when they grow.

Radius.java: Acts like a square area that some other objects use, like the Patch or Sunflower. Features some useful methods such as one to get a random point within the area covered by the radius.

Window.java: The main window that all other objects are placed in, either directly or indirectly. Contains a timer that acts as the main loop for the entire program, triggering methods within the Sky and Ground each tick. Will only directly contain the Sky and Ground, and updates all other objects in the simulation through them.

*growables*
Fungus.java: An incomplete class that will in future represent a mushroom that grows slowly and consistently, even during the night.


*growables/plant*
AdultState.java: A plant state used if a specialisation of Plant does not have distinct behaviour as an Adult. Will update the Plant to look different than other states, and transitions to being dead when a Plant's lifespan is exceeded.

DeadState.java: A plant state used if a specialisation of Plant does not have distinct behaviour when Dead. After a set amount of time, will remove the Plant from the Ground.

JuvenileState.java: A plant state used if a specialisation of Plant does not have distinct behaviour when Juvenile. Looks different to other stages for visual purposes, and moves to Adult when enough time has passed.

SeedlingState.java: A plant state used if a specialisation of Plant does not have distinct behaviour when a Seedling. Is also visually different to other phases and transitions to Juvenile after a certain amount of time.

SeedState.java: A plant state used if a specialisation of Plant does not have distinct behaviour when a Seed. Looks different to other states and progresses to Seedling after a certain amount of time.

Plant.java: The main superclass for all Plants. Provides code that keeps the Plant's state, observes the Sky, calls methods needed to update itself, and some other useful supplementary functions.

*growables/weed*

**TODO**

*growables/plant/flower*
BloomState.java: Unique state used only by flowers when the specialised flower does not have a unique BloomState. Flowers only produce seeds if they are blooming.

Flower.java: A specialisation of Plant, but still abstract. Adds Flower-exclusive functionality like the BloomState.

*growables/plant/flower/sunflower*
Sunflower.java: A specialisation of flower. Has it's own unique appearance, method of producing and distributing seeds, and states.

SunflowerSeedState: Provides the unique appearance of a Sunflower seed.

SunflowerJuvenileState: Provides the unique appearance for a juvenile Sunflower.

SunflowerAdultState: Provides the unique look for an adult Sunflower.

SunflowerBloomState: Provides the look of a blooming Sunflower.


**2.2.** Identify any inheritance relationships. For each parent–child pair, list what the child inherits and what it overrides.
JFrame -> Window

JPanel -> Sky

JPanel -> Ground

JPanel -> Plant

Plant -> Weed

Plant -> Flower

Flower -> Sunflower

*Interface Implementations*

Plant -> Growable, SkyObserver

**2.3.** Pick the class that you think has the best design. Explain why.

I think Plant.java has some of the best design in the project. This is because it features some complex functionality, needing to update every tick between many different states, each with very different behaviour. It implements the state pattern studied in the Week 6 lecture, and also acts as an observer to the Sky, since a plant will only grow during the day.


**2.4.** Paste one code snippet that demonstrates your use of polymorphism or encapsulation.  Include an explanation of _how_ this demonstrates polymorphim or encapsulation.  Give a reference to a provided reading that talks about this type of polymorphism or encapsulation.





---

## 3. Generics and Exceptions

**3.1.** List every place your code uses generics (e.g. `ArrayList<Actor>`, `Optional<Cell>`, `HashMap<String, Team>`). If you deliberately used none, explain why.

Patch.java is a generic class, and has a field "collection" which is an ArrayList of it's type. It also has a generic method addToPatch(T item) which takes an object of the Patch's type and tries to add it to the collection ArrayList. It has another method getObjectsInRadius() which returns an ArrayList<Growable>.

Ground.java has a method getGrowables, which returns an ArrayList<Growable> of the Growable objects currently added to the Ground.


**3.2.** List every place your code handles exceptions (try/catch, throws, custom exception classes). What error is each protecting against?

*Custom Exceptions*

InvalidPositionException: Thrown when objects are unable to be placed due to an error with their positioning in the window.

OutOfPatchBoundsException: Thrown by Patch.java when an attempt is made to add a Growable to the Patch that is outside of its radius.

InvalidParentException: Thrown by objects when attempting to add them to a Container object that they cannot be added to.

*Exception Handling*

Plant.java throws an InvalidPositionException when an attempt is made to place a Plant outside of the window or is created without a position.

Sunflower.java handles exceptions when creating new Sunflowers. It attempts to catch InvalidPositionException first, and other exceptions second.

Patch.java throws an OutOfPatchBoundsException if an attempt is made to add plant to the patch that is not within its radius.

Pathc.java throws InvalidParentException if an attempt is made to add the Patch to a Container other than a Ground.


**3.3.** Paste a code snippet showing either a generic class/method or a try/catch block.

public class Patch<T extends Growable> extends JPanel{
    
    Radius patchArea;
    ArrayList<T> collection;
    
    public Patch(int radius, Point position) {
        patchArea = new Radius(position, radius);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBounds(position.x-radius, position.y-radius, 2*radius, 2*radius);
        
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 255, 125));
        this.collection = new ArrayList<T>();
    }

    public void addToPatch(T item) throws OutOfPatchBoundsException {
        if(patchArea.isPointInRadius(item.getPosition())) {
            collection.add(item);
        } else {
            throw new OutOfPatchBoundsException();
        }
        item.increaseSpreadNum(2);
    }
}




---

## 4. Log Book

**4.1.** Attach or link your log book entries for Weeks 1–6.





**4.2.** Which week's activity taught you the most? What did you learn?

---

## 5. Uniqueness and Creativity

**5.1.** List everything you added to the project that was not part of the in-class activities.

The Sky's day/night cycle, and it's implementation of the observer pattern with Plants.



**5.2.** Which feature required the most independent research or problem-solving? What did you learn from it?

The implementation of the Patch class required a lot of problem solving around how generics and type-erasure actually work in Java, as when I started implementing it, I misunderstood much of the purpose of generics, why one would use them, and how to implement them in a way that functioned in the context I was looking for. I learnt about the difference between a type and a raw type, type safety and checking, and the importance of examining what is known, and what I was assuming about the code I was writing.

**5.3.** Paste one code snippet that you are especially proud of. Explain why it goes beyond what was done in class.

The following is one of the more structurally involved pieces of code in the project. It features much work covered in class, but also implements two design patterns from the lectures: the state pattern and the observer pattern. It also throws some custom exceptions, and is an abstract class that is never explicitly instantiated.

public abstract class Plant extends JPanel implements Growable, SkyObserver{
    public PlantState seedState;
    public PlantState seedlingState;
    public PlantState juvenileState;
    public PlantState adultState;
    public PlantState deadState;

    public PlantState state;
    
    Instant startTime;
    public int size = 60;
    public Point position;

    public int growthDelay;      //How long between growth states in milliseconds
    public int spreadRadius;     //How far a plant can spread its seeds

    public Plant(Point p, int growthDelay, int size, Sky sky) {
        seedState = new SeedState(this);
        seedlingState = new SeedlingState(this);
        juvenileState = new JuvenileState(this);
        adultState = new AdultState(this);
        deadState = new DeadState(this);

        state = seedState;

        startTime = Instant.now();
        position = p;
        spreadRadius = 100;
        if(position == null) {
            throw new InvalidPositionException("Position is null");
        } else if (position.x > Window.WIN_WIDTH || position.x < 0 || position.y < 0 || position.y > Window.WIN_HEIGHT/4*3) {
            throw new InvalidPositionException("Position: " + position.x + ", " + position.y);
        }
        this.growthDelay = growthDelay;

        this.position = p;
        this.setBounds(position.x, position.y, size, size);
        this.setBackground(Color.darkGray);
        sky.registerObserver(this);
    }

    @Override
    public void tick() {
        Instant now = Instant.now();
        long lifespan = (Duration.between(startTime, now)).toMillis();

        state.checkChange(lifespan);
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.paintComponent(g);
    }

    public void delete() {
        Container parent = this.getParent();
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }

    @Override
    public String getState() {
        return state.getName();
    }

    @Override
    public Point getPosition() {
        return position;
}
