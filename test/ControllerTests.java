import org.junit.Test;

import java.io.StringReader;

import controller.Controller;
import controller.GameController;
import model.dungeons.Dungeon;
import model.dungeons.Player;
import model.dungeons.PlayerInterface;
import model.random.DefinedRandomClass;

import static org.junit.Assert.assertEquals;

/**
 * A class that tests various functionalities of the controller.
 */
public class ControllerTests {

  @Test
  public void invalidOperationInput() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("hjgjh 1 north 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("You need to type a number.",
            lines[9]);

  }

  @Test
  public void invalidDirectionStringInputDuringMove() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("1 hhhjjhj 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to move : You can only enter one of the following : "
                    + "north/south/east/west",
            lines[9]);

  }

  @Test
  public void invalidDirectionStringInputDuringShoot() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("2 hhhjjhj 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to shoot : You can only enter one of the following : "
                    + "north/south/east/west",
            lines[9]);

  }

  @Test
  public void invalidDistanceInputDuringShoot() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("2 north o 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to shoot : Enter distance to shoot : "
                    + "You need to type a number.",
            lines[9]);

  }

  @Test
  public void quitMessage() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("2 north 1 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to shoot : Enter distance to shoot : "
                    + "You need to type a number.",
            lines[lines.length - 1]);
  }

  @Test
  public void winMessage() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("1 north 1 west 1 north 1 west 1 west 1 "
            + "north 2 west 1 2 west 1 1 west");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Congrats ! You win.",
            lines[lines.length - 1]);

  }

  @Test
  public void loseMessage() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("1 north 1 west 1 north 1 west 1 west 1 "
            + "north 1 west");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Sorry, you're Dead.",
            lines[lines.length - 1]);

  }


  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    Dungeon d6 = new Dungeon("wrapping", 50, 7, 5, 3,
            5, new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);
    StringReader input = new StringReader("hjgjh 1 north 3");
    Appendable gameLog = new FailingAppendable();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);
  }

  @Test
  public void hitOtyugh() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("1 north 1 west 1 north 1 west 1 west 1 "
            + "north 2 west 1 2 west 1 1 west");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to shoot : Enter distance to shoot : "
            + "You hit the monster !",lines[lines.length - 9]);
  }

  @Test
  public void missOtyugh() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("2 north 2 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Enter direction to shoot : Enter distance to shoot : "
            + "Oops! You missed the monster.",lines[lines.length - 6]);
  }

  @Test
  public void reallyBadSmell() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Something smells really bad...",lines[lines.length - 6]);
  }

  @Test
  public void lessSmell() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("1 east 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("Something smells a little...",lines[lines.length - 7]);
  }

  @Test
  public void outOfArrowsMessage() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    StringReader input = new StringReader("2 east 1 2 east 1 2 east 1 2 east 1 2 3");
    StringBuilder gameLog = new StringBuilder();
    Controller c = new GameController(input, gameLog);
    c.playGame(p6);

    String[] lines = gameLog.toString().split("\n");
    assertEquals("You are out of weapons !",lines[lines.length - 6]);
  }
}
