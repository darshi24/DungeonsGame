# Dungeons

## About/Overview

This project aims to design a game where a player moves around a network of interconnected caves and tunnels and collects treasures and weapons along the way. This is a game that can be played through typing commands and also by using a graphical user interface. 

## List of Features
- The following are the feature for the text based game:
- A dungeon is created with caves and tunnels where one cave is randomly selected as start and another cave is randomly selected as end. The path length between the start and end locations is atleast 5 and every location can be reached from every other location.
- A dungeon can be a wrapping or a non-wrapping dungeon.
- Caves have 1,3 or 4 entrances. Tunnels have two entrances.
- There are three types of treasure - Diamond, Ruby and Sapphire. A cave can have a monster otyugh which can kill the player. 
- There is a type of weapon called arrow which can be used by the player to slay the monster otyugh. 
- A certain number of the caves have one or more treasure(s).
- The same number of locations(cave or tunnel) have one or more arrows.
- Certain caves have otyughs in them.
- Otyughs have smell. Therefore a player navigating through the dungeon can smell and determine how close otyughs can be from him.
- A player is put into a dungeon with an empty treasure bag and a weapon bag with three arrows where it can moves in all possible directions and collect all the treasure and arrows wherever available.
- A player can shoot an arrow by specifying the direction and distance and try to kill an otyugh.
- After every move of the player, its current location and the contents of its treasure bag and weapons bag and the possible exits from the current location are displayed.
- The following are the feature for the GUI based game:
- In the GUI-based scenario, the player's screen displays a window with two panes. One Pane has the dungeon layout which get uncovered as the player traverses through it. The other pane shows statistics of the player such as contents of its treasure bag, weapon bag, the treasure and weapons available in the player's current location and the smell of the otyugh in the player's current location.
- The user can use mouse clicks to navigate through the dungeon and use the arrows keys as well.
- The user needs to press T to pick up the treasures in its current location.
- The user needs to press W to pick up the weapons in its current location.
- The player can change the settings by going through File->Settings and start a new game.
- The player can create a new game with new dungeon by File->Restart->Restart with new Dungeon. The seetings for the new dungeon are asked from the player.
- The player can create a new game with old dungeon by File->Restart->Restart with old Dungeon
- The player can exit a game by File->Exit


## How To Run
To run the text-based game,
Run the jar file with following command along with command line args:
```sh
java -jar Project5_1.jar "wrapping" 50 6 5 3 5
```

To run the GUI-based game,
Run the jar file with following command:
```sh
java -jar Project5_1.jar 
```


## How to Use the Program
To run the text-based game, run the jar file using above-mentioned command along with the command line parameters. 
Parameter 1 - type of dungeon (wrapping or non-wrapping)
Parameter 2 - percentage of caves to be populated with treasures
Parameter 3 - rows of the dungeon
Parameter 4 - columns of the dungeon
Parameter 5 - interconnectivity in the dungeon
Parameter 6 - number of otyughs in the dungeon

Play the game interactively following the instructions displayed.

To use the GUI-based game, run the jar file as mentioned above. Navigate through mouse clicks or arrow key presses in the dungeon. Press T to pick up treasure and press W to pick up weapons. 

## Description of Examples

Description of SampleRun1.png
The screenshot shows the state of an ongoing game. The dungeon is displayed as the player navigates through it. The current location of the player is indicated by the red border. As the dungeon is too big to fit in the screen, the scroll bars appear. The panel below the dungeon shows various contents:
1. The treasure bag contents - How many of each treasure type does the player have.
2. The weapon bag contents - the number of weapons (arrows) that the player has.
3. Location details - number of each treasure type in the location that the player is currently in. When the player picks up the treasure, the values turn to 0. It also displays the smell (NONE, WEAK, STRONG) that the player can sense st its currrent location.

Description of SampleRun2.png
The screenshot additionally shows how the pop up menu looks like when the player selects File-> Settings from the menu. There are text boxes provided for the user to input each field.

Description of SampleRun3.png 
The screenshot additionally shows how the pop up menu looks like when the player is dead.


Description of SampleRun4.png 
The screenshot additionally shows the details of each location.

Description of SampleRun5.png 
I had forgotten to add the feature that displays the weapon number in the player's current location. For that reason it is not in the above screenshots but I have included it in this screenshot.


## Design/Model Changes
- The design I made before starting Project 5 and after completing it does not have many changes in design. I have just corrected some mistakes I made in the arrow heads and added multiplicities.
- I have added a class for the Settings Panel that I missed out on adding before.
- Apart from this there were only minor changes in names of methods and one or two addition/deletion of methods.

## Assumptions
- A dungeon should have minimum 5 columns and rows.
- If a player tries to move in the direction that is blocked, then it will stay at the same place. 
- There can be zero or one otyugh in a cave.
- The number of otyughs in a cave cannot be more than half of the locations in the dungeon.
- If the number of otyughs specified through the command line is more than the caves in the dungeon, then every cave would have an otyugh.

## Limitations
- When a player tries to move in a direction that is blocked, the player stays in the same place. No error message is being thrown. The player has to figure out on its own that it has stayed in the same place. This could or could not be a limitation of the program depending on the requirements that the user want.   
- When an otyugh is completely dead, it does get eliminated from that location. But message is not displayed saying that the otyugh is dead. The message will only say that the otyugh was hit.
- For the GUI-based game, the shoot operation has not been implemented.
- For the GUI-based game, the starting image is not displayed perfectly due to resizing problems.

## Citations
- https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/


