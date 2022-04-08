import org.junit.Test;

import model.dungeons.Dungeon;
import model.dungeons.Location;
import model.dungeons.Player;
import model.dungeons.PlayerInterface;
import model.random.DefinedRandomClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A class that tests various functionalities of a non-wrapping dungeon.
 */
public class NonWrappingTests {

  @Test
  public void testStartLocation() {
    Dungeon d1 = new Dungeon("non-wrapping", 10, 6, 6, 3,
            15,
            new DefinedRandomClass());
    PlayerInterface p1 = new Player(d1);

    Location l = null;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (d1.getLocations()[i][j].isStart()) {
          l = d1.getLocations()[i][j];
        }
      }
    }
    assertEquals(1, l.getId());
  }

  @Test
  public void testEndLocation() {

    Dungeon d2 = new Dungeon("non-wrapping", 10, 6, 6, 3,
            15,
            new DefinedRandomClass());
    PlayerInterface p2 = new Player(d2);
    Location l = null;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (d2.getLocations()[i][j].isEnd()) {
          l = d2.getLocations()[i][j];
        }
      }
    }
    assertEquals(32, l.getId());
  }

  @Test
  public void dungeonCreation() {


    Dungeon d3 = new Dungeon("non-wrapping", 20, 6, 6, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);
    assertEquals("Neighbors of 1: [2]\n" +
            "Neighbors of 2: [1, 8]\n" +
            "Neighbors of 3: [4, 9]\n" +
            "Neighbors of 4: [5, 3]\n" +
            "Neighbors of 5: [11, 4, 6]\n" +
            "Neighbors of 6: [5]\n" +
            "Neighbors of 7: [8, 13]\n" +
            "Neighbors of 8: [7, 14, 9, 2]\n" +
            "Neighbors of 9: [10, 8, 3]\n" +
            "Neighbors of 10: [9, 16, 11]\n" +
            "Neighbors of 11: [5, 12, 10]\n" +
            "Neighbors of 12: [18, 11]\n" +
            "Neighbors of 13: [7, 19]\n" +
            "Neighbors of 14: [20, 8]\n" +
            "Neighbors of 15: [16]\n" +
            "Neighbors of 16: [10, 15]\n" +
            "Neighbors of 17: [18]\n" +
            "Neighbors of 18: [12, 24, 17]\n" +
            "Neighbors of 19: [20, 13]\n" +
            "Neighbors of 20: [21, 14, 19, 26]\n" +
            "Neighbors of 21: [20]\n" +
            "Neighbors of 22: [28]\n" +
            "Neighbors of 23: [24, 29]\n" +
            "Neighbors of 24: [23, 18, 30]\n" +
            "Neighbors of 25: [26, 31]\n" +
            "Neighbors of 26: [25, 27, 20]\n" +
            "Neighbors of 27: [33, 26]\n" +
            "Neighbors of 28: [34, 22]\n" +
            "Neighbors of 29: [35, 23]\n" +
            "Neighbors of 30: [36, 24]\n" +
            "Neighbors of 31: [25, 32]\n" +
            "Neighbors of 32: [31]\n" +
            "Neighbors of 33: [27, 34]\n" +
            "Neighbors of 34: [33, 28]\n" +
            "Neighbors of 35: [36, 29]\n" +
            "Neighbors of 36: [35, 30]\n", d3.toString());
  }

  @Test
  public void locationsAssignedAsCavesCorrectly() {

    Dungeon d3 = new Dungeon("non-wrapping", 20, 6, 6, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);
    Location[][] locs = d3.getLocations();
    List<Location> caves = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (locs[i][j].getNeighbors().size() != 2) {
          caves.add(locs[i][j]);
        }
      }
    }
    assertEquals("[1, 5, 6, 8, 9, 10, 11, 15, 17, 18, 20, 21, 22, 24, 26, 32]", caves.toString());
  }

  @Test
  public void treasuresAssignedToCorrectCaves() {

    Dungeon d3 = new Dungeon("non-wrapping", 20, 6, 6, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);
    Location[][] locs = d3.getLocations();
    List<Location> caves = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (locs[i][j].getNeighbors().size() != 2) {
          caves.add(locs[i][j]);
        }
      }
    }
    assertEquals("[1, 5, 6, 8, 9, 10, 11, 15, 17, 18, 20, 21, 22, 24, 26, 32]", caves.toString());
    assertEquals("[RUBY]", caves.get(0).getTreasure().toString());
    assertEquals("[RUBY]", caves.get(1).getTreasure().toString());
    assertEquals("[RUBY]", caves.get(2).getTreasure().toString());
    assertEquals("[]", caves.get(3).getTreasure().toString());
    assertEquals("[]", caves.get(4).getTreasure().toString());
    assertEquals("[]", caves.get(5).getTreasure().toString());
    assertEquals("[]", caves.get(6).getTreasure().toString());
    assertEquals("[]", caves.get(7).getTreasure().toString());
    assertEquals("[]", caves.get(8).getTreasure().toString());
    assertEquals("[]", caves.get(9).getTreasure().toString());

  }
}
