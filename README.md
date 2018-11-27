# Meteor Blaster - COMP167 - Spring 2018
## Due: April 24, 2018 @ 6:00pm

### Introduction

This programming assignment requires you to implement the GUI and animation portion of the Meteor Blaster game. You will be given the backend portion of the game developed in MP1 and you may be able to recycle some of the code you personally developed in MP2. Some of the classes are the same; however, some have been modified so go through each of the classes carefully – even if they share a name with a class used in MP2.

![Figure01](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure1.png)

### UML Diagrams

Here are the UML Diagrams that describe the classes you must implement in Java.

![Figure02](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure2.png)

### Level 1: ProfilePane Design & Implementation (25 pts)

#### ProfilePane Class

![Figure03](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure3.png)

- Design a GUI for the player profile function of the Meteor Blaster game. Your design should include the following:
  - A ComboBox or ListBox to select an existing player profile.
  - Controls to enter a Gamer ID (nickname), First name and Last name.
  - A control (e.g. Button) to initiate a search for an existing gamer (OK button in Figure 1).
  - A control (e.g. Button) to initiate the creation of a new player profile (Register in Figure 2).
  - A control (e.g. Label) to display status messages to the user.
  
![Figure04](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure4.png)
![Figure05](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure5.png)

- Implement your GUI using JavaFX. Start by creating a JavaFX application in Netbeans. Name the project MeteorBlasterGUI and add the MeteorBlasterBackend jar file as a library for this project . Add a new class to your project and name it ProfilePane. ProfilePane should extend one of the subclasses of Pane (StackPane, HBox, VBox, GridPane or BorderPane). The particular Pane you use depends on your GUI design. My design used a HBox with a VBox on the inside for the TextBox and Button at the top of the GUI.
  - Create properties in ProfilePane for each needed control and add them to Pane or other inner Panes that you create. The constructor for ProfilePane should read in the profile data from the profile text file and save it in the GamesProfiles object (profiles).
  - Instantiate an instance of ProfilePane in your start method and add it to a Scene so that it can be displayed in the Stage.
  
### Level 2: (35 pts)

Add event handling to your ProfilePane GUI. The particular events you have to handle will depend on your GUI design. Use anonymous listeners for your controls for this GUI. In general, you will have to respond to controls that initiate:
- Searching to see if a new player name is already used for another player profile. If the gamer tries to add a GamerID that is already in use, display a message in the status label.
- Adding a new player profile to the player profile text file. If the player was added successfully, display a message in the status label.
- Launching a new Stage. For this version, you should display a new Stage containing the selected gamer profile in a TextArea control followed by a ListBox with the properties of each Gamer as a separate entry in the ListBox. The properties of each gamer can be concatenated together with a comma as a separator.

### Level 3: (50 pts)

This level will require you to create an application that contains a Craft that can be moved around the PlayArea.  The PlayArea background will display an outer space background image.  The LevelData object will contain the name of the image file to be used for your PlayArea background. The Craft will be controlled by rotation (a=left, d=right) and moving forward (w key).  When the Craft is directed off the screen, it should appear on the opposite side moving in the same direction.  Add event handling for keyboard and mouse events in your GameUniverse class.  

GameUniverse extends the BorderPane class.  The zones in GameUniverse will be used to display the different Panes of your game (PlayArea, StatusArea, ControlArea).  For this level, you will create the PlayArea class and place and instance in the Center zone of the GameUniverse.  You are free to include additional properties in your GameUniverse to support some of your game features.  

Create your PlayArea object and then include it as a parameter to the Craft constructor.  You will add the Craft to the PlayArea in the Craft constructor.  Your Craft does not have to fire torpedoes, produce the thrust animation or produce sound at this level.

![Figure06](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure6.png)

![Figure07](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure7.png)

```
public void setPlayAreaBackground( String bgImage ) {
   try {
       Image image1 = new Image( new FileInputStream(bgImage));
       BackgroundImage myBI = new BackgroundImage( image1, BackgroundRepeat.NO_REPEAT, 
          BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
       this.setBackground( new Background(myBI));
   }
   catch ( java.io.FileNotFoundException ex) {
       System.err.println("Could not find image for loading.");
   }
}
```

![Figure08](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure8.png)

![Figure09](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure9.png)

```
@Override
public double getBoundingRadius() {
    return Math.max( this.getBoundsInParent().getHeight(), this.getBoundsInParent().getWidth())/2;
}
```

```
@Override
public final double getCenterX() {
    return this.getBoundsInParent().getMinX() + this.getBoundsInParent().getWidth()/2;
}
```

![Figure10](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure10.png)

### Level 4: (70 pts)

Add the MeteorField and PhotonTorpedoes to the project on this level.  When the spacebar is pressed, a PhotonTorpedo is fired from the current Craft position and moves at the established PhotonTopedo speed in the Craft’s current direction.  Once a PhotonTorpedo is created, it is added to the torpedoes ArrayList in the Craft.  The MeteorData and CraftData objects will contain most of the needed properties for the Craft, Meteors and PhotonTorpedoes.

Meteors are created with:
-	A random size that is a multiple of the minimum meteor size.  
-	A random speed between 1 and the maximum meteor speed.  
-	A random direction between 0 and 359 degrees.

At each animation update, the AnimationTimer handle() method should update the position of all moving objects on the screen (Craft, Meteors and Torpedoes)  then check to see if there has been a collision between a PhotonTorpedo and a Meteor or between a Meteor and the Craft.

#### Algorithm and hints for Meteor constructor:
The Meteor sprite sheet contains 4 rows and 4 columns of 125x125 meteor images.  You are passed the entire sprite sheet to the meteor constructor so you need to randomly display one of the 16 different meteor images.  In addition, you will need to scale that image to be smaller or larger if you implement the optional level where there is meteor splitting.  Also, remember that Meteors are a subclass of ImageView which includes many convenient properties and methods to help us achieve the effects we want to display.

-	Set properties of the Meteor from the parameters and the  MeteorData object (location, image, minimum size, maximum speed, etc.)
-	Set the meteorImageWidth and meteorImageHeight properties by dividing the sprite sheet height and width by the number of meteors on each row and the number of meteors on each column.  For the given sprite sheet, these values should be both 125 pixels since the sprite sheet is 500x500 pixels – right?
-	Give the Meteor a random direction a random speed
-	Set the parentWidth and parentHeight properties inherited from MovingSprite:

`setParentWidth(playArea.getPrefWidth());`

`setParentHeight(playArea.getPrefHeight());`

-	Use the setFitHeight and setFitWidth to scale the image to the preferred viewable size (should be some multiple of the meteor minimum size).
-	Randomly decide which meteor to display.

`int row = rand.nextInt(meteorData.getMeteorRows());`

`int col = rand.nextInt(meteorData.getMeteorCols());`

`Rectangle2D viewportRect = new Rectangle2D(col*meteorImageWidth, row*meteorImageHeight, meteorImageWidth, meteorImageHeight);`

`this.setViewport(viewportRect);`

-	add this meteor object to the playArea

A torpedo-to-meteor collision should cause the meteor to disappear and the torpedo to disappear.  Within you code that means that these objects will have a visible property of false and are also cleared from their containing ArrayList.

A meteor-to-craft collision should cause the meteor to disappear and the shield damage of the Craft to decrease by 10%.  When the Craft shield percentage reaches 0%, the next collision will end the game.

![Figure11](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure11.png)

![Figure12](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure12.png)

![Figure13](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure13.png)

![Figure14](https://github.com/NCATCS/images/blob/master/Spring2018MP3_Figure14.png)

### Level 5: (75 pts)

Add the thrust animation to your craft. While the w-key is pressed, your code should cycle through the first three Craft images to simulate rocket thrust.   Here is the general strategy:

Declare a boolean variable as a property of the GameUniverse.  When the Craft is idle and the w-key is pressed, set the variable to true in the keypressed event handler for the w-key.  When the Timer handle() method is called, check the value of the boolean variable.  If it is true, toggle the Craft image in modulo fashion.  When the w-key is released, set the boolean variable to false and display the first craft image (no fire thrust).  

### Optional Feature 1: (10 pts)

Implement game levels.  When all the meteors are destroyed, create a game level based off the information in the LevelData object. The LevelData object will contain information such as the background image  for the PlayArea, the number of starting Meteors, etc.  The first two properties of each Level object will give the starting and ending levels that the Level object describes.

### Optional Feature 2: (10 pts)

Add sound to your game. Sounds should be played when:
-	A torpedo is fired
-	The craft moves in a forward direction
-	A torpedo hits a meteor
-	A meteor hits the craft
-	Shields fall below 20%

### Optional Feature 3: (10 pts)

Display a shield meter.  The meter should behave like a progress bar and should display a green bar while the shields are above 50%.  When shields are between 21% and 49%, the bar should be yellow.  Below 21%, the bar should be red.  If you implement sound, play a warning message when the shield meter falls below 21%.  My version of the game used a rectangle as the progress bar.  The length and color of the rectangle was changed as the shield strength of the craft decreased.

### Optional Feature 4: (10 pts)

Maintain and display player statistics in one of the GameUniverse zones (StatusArea).  Include:
-	GamerID
-	Gamer Rank
-	Gamer’s highest score
-	High Score of all Gamers (System High Score)
-	Gamer current score

You will need to use the RankData object to figure out the gamer’s current rank.  At the end of the game, you will need to update the player profile with the data from the game just completed.

### Optional Feature 5: (10 pts)

Implement meteor splits when hit by a torpedo.  You will need to add another Meteor constructor that also includes the speed and direction of the Meteor as parameters.  This second constructor will be called to create new smaller Meteors after a larger meteor is involved in a collision.  You can decide how you want to split a Meteor.  My strategy was to take a Meteor that was N times the minimum Meteor size and make it into N-1 Meteors that were all the minimum size.  So, if the minimum size was 30 and I had a Meteor that was 90x90 (N=3), I would split that larger Meteor into 2 Meteors of size 30.  The split Meteors would be assigned a random direction that was +/- 30 degrees from the original Meteor’s direction.  I would also assign random speeds to each new Meteor.   The original Meteor is removed from the MeteorField.
