# Dungeons
### _A GUI-based (and alternatively, a text-based) adventure game developed using Java and the Swing library following the Model-View-Controller (MVC) architecture and design pattern_.

## About

A player :raising_hand_man:/:raising_hand_woman: moves around a network of interconnected caves and tunnels, killing and evading monsters:japanese_ogre:, and collecting treasures:gem: and weapons:bow_and_arrow: along the way.

### The game environment
The game screen displays a window with two panes. The top pane is a scrollable pane that has a randomized dungeon layout which gets uncovered as the player traverses through it and the other pane shows statistics related to the player. Players can use mouse clicks as wells as arrow keys to navigate the dungeon. The red border arond a cell shows the current location of the player.
<p align = "middle">
  <img src="https://github.com/darshi24/DungeonsGame/blob/main/res/SampleRun1.PNG", height = 600, width = 475/>
  <img src="https://github.com/darshi24/DungeonsGame/blob/main/res/SampleRun2.PNG", height = 600, width = 475/>
</p>

A dungeon can be a wrapping or a non-wrapping dungeon. Each location in the dungeon is either a cave or a tunnel which can have treasures and weapons for the player to collect. There are three types of treasure - Diamond, Ruby and Sapphire. A cave might be home to a monster (called Otyugh) which can kill the player with a 50% probability if the player steps into that cave and terminate the game:trollface:. We have arrows :bow_and_arrow: as weapons which can be used to slay smelly monsters called otyughs:japanese_ogre:.  

A player is put into a dungeon with an empty treasure bag and a weapon bag with three arrows. It can move in all possible directions and collect all the treasure and arrows wherever available. Since the contents of each location remain hidden till it reaches there it cannot know if treasures, weapons or MONSTERS :cold_sweat::scream:!!! are present or not. The player can although smell the monster if it is nearby and shoot an arrow by specifying the direction and distance to kill it. After every move of the player, its current location and the contents of its treasure bag and weapons bag and the possible exits from the current location are displayed in the statistics pane. Player statistics include contents of its treasure bag, weapon bag, the treasure and weapons available in the player's current location and the smell of the otyugh in the player's current location.

### Player actions

1. Arrow keys (:arrow_up:, :arrow_right:, :arrow_down:, :arrow_left:) or mouse clicks to navigate. Can only navigate in a particular direction if it has an exit.
2. Press 'T' to pick up the treasures in its current location.
3. Press 'W' to pick up the weapons in its current location.
4. The player can change the settings by going through File->Settings and start a new game. Setting options include 
      1. Type of dungeon (wrapping or non-wrapping)
      2. Percentage of caves that have treasures
      3. Rows and columns in the dugeon
      4. Interconnectivity in the dungeon
      5. Number of otyughs in the dungeon
5. The player can create a new game with new dungeon by _File->Restart->Restart with new Dungeon_. The seetings for the new dungeon are asked from the player.
6. The player can create a new game with old dungeon by _File->Restart->Restart with old Dungeon_.
7. The player can exit a game by _File->Exit_.

## How To Run
To run the text-based game,
Download the jar file in the res folder and run with following command in terminal along with command line args:
Each field following the file namere presents a setting of the game environment:
1. Type of dungeon (wrapping or non-wrapping) - _"wrapping"_
2. Percentage of caves that have treasures - _50_
3. Rows in the dungeon - _6_
4. Columns in the dungeon - _5_
5. Interconnectivity in the dungeon - _3_
6. Number of otyughs in the dungeon - _5_
```sh
java -jar Project5_1.jar "wrapping" 50 6 5 3 5
```

To run the GUI-based game,
Download the jar file in the res folder and run with following command in terminal:
```sh
java -jar Project5_1.jar 
```

## Developer Thoughts
Hi there:smiley:! I would like to share some things from a developer perspective.

Since this sort of interconnected layout must not have a location that is unreachable, I have used Kruskal's Algorithm to create a minimum spanning tree structure so that each location can be reached and then I let the player provide the degree of added connectivity.

It was quite a challenging project to develop as I had time constraints. My aim was to follow the MVC architecture pattern, have the least amount of coupling between the three layers (Model, View and Controller layers) and the modules, and use appropriate design patterns based on the nature & characteristics of the entities (player, dungeon, treasures, weapons, monsters) and relations between them. Due to time constraints, I could not implement the shoot functionality.

Since the project was developed as a part of my 'Programming Design Paradigms' coursework, it was requirement to use JAVA and the Swing library for designing the user interface of the game. Therefore, excuse my UI design :sweat_smile: . 
