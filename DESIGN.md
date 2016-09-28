Project Design
==============

## High Level Design Goals

The high level design goals of this project are as follows:  
  
    - Put each distinct feature or item in its own class so scalability is more manageable  
    - Use interfaces/abstract classes where possible to improve organization and documentation  
    - Use inheritance if appropriate to avoid excessive amounts of duplicate code in diferent classes  
    - Use private methods and variables where possible to reduce clutter in the global namespace 
    - Avoid static methods and variables **where possible** to maintain object-oriented design.  
    - Extract methods within classes to improve readability and remove duplicate code smells.  
  
## Adding new features  
  
### Adding items in gameplay
In this particular game, the entire gameplay revolves around the snake- the snake directly interacts with  
all elements of the game, including other objects and game information. Thus to add a new item directly to the game,
follow the simple steps highlighted below:  
  
    1. Create a class that extends the Item.java class, or if item is sufficiently different, create an independent class.  
    2. Add the item to the game screen by putting it in the Group instance variable of the House.java class.  
    3. Call the new item's appropriate methods in the Snake.java class within the handle method of the AnimationTimer  
       instance variable's anonymous class so that the Snake object can interact with the item.  
  
### Adding more levels to the game  
Adding levels is simple. We use the House.java class. The House.java class places all the elements in its  
Group pane object on the screen.The House.java class has a constructor that takes in 2 integer parameters: current level, and  
game difficulty. To add more levels of increasing difficulty before the boss level, simply change the bossLevel constant.  
Then there will be more levels before the player reaches the boss.  
  
If you want to do something special on a particular level, then follow the highlighted steps below:  

    1. Create a constant integer for that level.  
    2. Create a new method to execute when currLevel matches desired level.  
    3. Add the appropriate checks to the setItems method in House.java so that the method will be executed upon reaching level.  
    4. Add appropiate checks to displayInfo, displayLives, displayMiceLeft methods if necessary.  
    
### Adding Labels, Buttons, Text, etc.  
Use the MenuButton class to call the appropriate static method to create the element desired. If no method matches the desired  
element, create a new method in the MenuButton class and call that method in other appropriate classes.  
  
### Adding a new mode  
This is surprisingly easy given the design of this project. Simply create a new class that extends the SceneMaker interface,  
implements the appropriate methods, and then create a method in the SceneControl class that instantiates the object, calls  
the displayScene function, and uses the Stage instance variable in the SceneControl class to switch scenes.  
  
## Justifications for Major Design Choices
  
### Including a SceneMaker Interface
This interface has only 2 methods, but both methods are crucial for all "screen" classes such as GameOver.java, Map.java,  
Menu.java, Win.java. The displayScene method populates the Scene instance variable that all these classes have. The getScene  
method returns the scene so that the SceneControl class can use its Stage instance variable to switch to that particular scene.  
These 2 methods are crucial and so every "screen" class must implement this interface.

    - Pros: The good side of this is that it is clear that every class that implements 
            this interface must be a "screen" class in that it represents a different screen of the game. 
    - Cons: This interface is not flexible because the displayScene has no parameters, so any class wanting to take in  
            parameters will not be able to.

### Extending Snake.java in Boss.java
This is a major design decision in that it removes a large amount of duplicate code. This inheritance is appropriate:  
The Boss class is a Snake, and therefore should extend the Snake class. The Boss class overrides certain methods, but a large  
number methods remain the same. 

    - Pros: Removes a large amount of duplicate code and improves readability. Overriden methods are clear so that
            differences between the classes can be clearly seen.
    - Cons: The Boss class has its own game loop (AnimationTimer), so the Boss's AnimationTimer must be started separately
            from the Snake's. This is an easy source of bugs, as one may often forget to separately start the Boss's game loop.

### Including a SceneControl.java class with static methods and a private static Stage object
As there can only be a single Stage object, to switch scenes with, there are 2 feasible options.  
Firstly, you could pass the a reference of the Stage (or an object that encapsulates the Stage)  
to all the classes that need it. Secondly, you could make the Stage object static and use static methods  
to manipulate the Stage object within a single class.  
  
I chose to do the latter opton, so that the state of the Stage object would persevere throughout the program's life.  
  
    - Pros: Simpler code design- Every class does not need to pass in a reference of an encapsulated Stage object. 
            The Stage's state needs to be preserved, and making it static does just that. Also, switching between scenes 
            is much easier because there exists a single global Stage object encapsulated by the SceneControl class.  
    - Cons: One drawback to this strategy is that multiple games would be difficult because there is only a single instance  
            of the Stage object inside the SceneControl.java class, and therefore multiple games would require multiple stages,  
            which a single static Stage cannot provide.  



## Assumptions  
I assumed that the user knows how to use arrow keys to switch between buttons, and to hit the ENTER key to select  
a button. I also assume that going in opposite direction of the current direction kills you instantly.  






