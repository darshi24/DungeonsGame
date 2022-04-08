package main;

import controller.GameController;
import controller.GuiController;
import model.dungeons.Dungeon;
import model.dungeons.Player;
import model.dungeons.PlayerInterface;
import model.random.RandomClass;

import java.io.InputStreamReader;

/**
 * Driver class.
 */
public class Driver {

  /**
   * The main method.
   *
   * @param args type of dungeon, percentage of caves to be populated with treasure, number of rows
   *             of dungeon, number of columns of dungeon, interconnectivity of the dungeon.
   *             number of otyughs in the dungeon. If no arguments are given then a game with
   *             graphical user interface is created. If the arguments are given, then a
   *             text-based game is created.
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;

    if (args.length > 0) {
      new GameController(input, output).playGame(new Player(new Dungeon(args[0],
              Integer.parseInt(args[1]), Integer.parseInt(args[2]),
              Integer.parseInt(args[3]), Integer.parseInt(args[4]),
              Integer.parseInt(args[5]), new RandomClass())));
    } else {

      Dungeon dungeon = new Dungeon("wrapping",50, 6,6,
              4,3, new RandomClass());
      PlayerInterface player = new Player(dungeon);
      GuiController gui = new GuiController(player);
      gui.playGame(player);
    }

  }
}
