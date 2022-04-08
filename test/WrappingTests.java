import org.junit.Test;



import java.util.ArrayList;
import java.util.List;

import model.dungeons.Dungeon;
import model.dungeons.Location;
import model.dungeons.Player;
import model.dungeons.PlayerInterface;
import model.random.DefinedRandomClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class that tests various functionalities of a wrapping dungeon.
 */
public class WrappingTests {

  @Test
  public void testStartLocation() {

    Dungeon d1 = new Dungeon("wrapping", 10, 6, 5, 3,
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

    Dungeon d2 = new Dungeon("wrapping", 10, 6, 5, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p2 = new Player(d2);
    Location l = null;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (d2.getLocations()[i][j].isEnd()) {
          l = d2.getLocations()[i][j];
        }
      }
    }
    assertEquals(17, l.getId());
  }

  @Test
  public void dungeonCreation() {


    Dungeon d3 = new Dungeon("wrapping", 20, 6, 5, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);
    assertEquals("Neighbors of 1: [6, 2, 5, 26]\n" + "Neighbors of 2: [1, 7]\n"
            + "Neighbors of 3: [8, 28]\n" + "Neighbors of 4: [5]\n" + "Neighbors of 5: [4, 1]\n"
            + "Neighbors of 6: [1, 11]\n" + "Neighbors of 7: [8, 2]\n"
            + "Neighbors of 8: [7, 3, 9]\n" + "Neighbors of 9: [10, 8]\n"
            + "Neighbors of 10: [9, 15]\n" + "Neighbors of 11: [12, 6, 16, 15]\n"
            + "Neighbors of 12: [13, 11]\n" + "Neighbors of 13: [12]\n"
            + "Neighbors of 14: [19, 15]\n" + "Neighbors of 15: [10, 11, 14]\n"
            + "Neighbors of 16: [11]\n" + "Neighbors of 17: [18]\n"
            + "Neighbors of 18: [23, 17]\n" + "Neighbors of 19: [14, 20, 24]\n"
            + "Neighbors of 20: [19, 25]\n" + "Neighbors of 21: [26]\n"
            + "Neighbors of 22: [27]\n" + "Neighbors of 23: [24, 18]\n"
            + "Neighbors of 24: [25, 23, 29, 19]\n" + "Neighbors of 25: [30, 24, 20]\n"
            + "Neighbors of 26: [30, 27, 1, 21]\n" + "Neighbors of 27: [26, 22]\n"
            + "Neighbors of 28: [3]\n" + "Neighbors of 29: [24]\n"
            + "Neighbors of 30: [25, 26]\n", d3.toString());
  }

  @Test
  public void locationsAssignedAsCavesCorrectly() {

    Dungeon d3 = new Dungeon("wrapping", 20, 6, 5, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);
    Location[][] locs = d3.getLocations();
    List<Location> caves = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].getNeighbors().size() != 2) {
          caves.add(locs[i][j]);
        }
      }
    }
    assertEquals("[1, 4, 8, 11, 13, 15, 16, 17, 19, 21, 22, 24, 25, 26, 28, 29]",
            caves.toString());
  }

  @Test
  public void treasuresAssignedToCorrectCaves() {

    Dungeon d3 = new Dungeon("wrapping", 20, 6, 5, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p3 = new Player(d3);

    Location[][] locs = d3.getLocations();
    List<Location> caves = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].getNeighbors().size() != 2) {
          caves.add(locs[i][j]);
        }
      }
    }
    assertEquals("[1, 4, 8, 11, 13, 15, 16, 17, 19, 21, 22, 24, 25, 26, 28, 29]",
            caves.toString());
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
    assertEquals("[]", caves.get(10).getTreasure().toString());
    assertEquals("[]", caves.get(11).getTreasure().toString());
    assertEquals("[]", caves.get(12).getTreasure().toString());
    assertEquals("[]", caves.get(13).getTreasure().toString());


  }


  @Test
  public void minimumLengthFive() {

    Dungeon d5 = new Dungeon("wrapping", 50, 6, 5, 3,
            10,
            new DefinedRandomClass());
    PlayerInterface p5 = new Player(d5);
    Location startLocation = p5.getCurrentLocation();
    Location end = null;

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (d5.getLocations()[i][j].isEnd()) {
          end = d5.getLocations()[i][j];
        }
      }
    }

    List<Location> eligibleEndLocations = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        eligibleEndLocations.add(d5.getLocations()[i][j]);
      }
    }

    for (Location n : startLocation.getNeighbors()) {
      eligibleEndLocations.remove(n);
      for (Location n2 : n.getNeighbors()) {
        eligibleEndLocations.remove(n2);
        for (Location n3 : n2.getNeighbors()) {
          eligibleEndLocations.remove(n3);
          for (Location n4 : n3.getNeighbors()) {
            eligibleEndLocations.remove(n4);
            for (Location n5 : n4.getNeighbors()) {
              eligibleEndLocations.remove(n5);

            }
          }
        }
      }
    }

    eligibleEndLocations.removeIf(l -> l.getNeighbors().size() == 2);

    assertTrue(eligibleEndLocations.contains(end));

  }

  @Test
  public void ifOtyughsInCavesOnly() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    Location[][] locs = d6.getLocations();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].isOtyughPresent()) {
          assertNotEquals(2,locs[i][j].getNeighbors().size());

        }
      }
    }

  }

  @Test
  public void ifEndLocationHasOtyugh() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);
    Location[][] locs = d6.getLocations();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].isEnd()) {
          assertTrue(locs[i][j].isOtyughPresent());
          break;
        }
      }
    }
  }


  @Test
  public void ifStartLocationDoesNotHaveOtyugh() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);
    assertFalse(d6.getStart().isOtyughPresent());
  }

  @Test
  public void playerHasThreeArrowsInitially() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);
    assertEquals(3,p6.getWeaponsBag().size());
  }

  @Test
  public void OtyughHealthChanges() {
    Dungeon d8 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p8 = new Player(d8);
    assertEquals(false,p8.getCurrentLocation().isOtyughPresent());
    p8.move("west");
    assertEquals(2,p8.isMovePossible("west").getOtyugh().getHitsToKill());
    p8.shootWeapon("west",1);
    assertEquals(1,p8.isMovePossible("west").getOtyugh().getHitsToKill());
    p8.shootWeapon("west",1);
    assertEquals(false,p8.isMovePossible("east").isOtyughPresent());
  }

  @Test
  public void ifArrowsPossibleInAllLocations() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    Location[][] locs = d6.getLocations();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].getWeapons().size() > 0) {
          assertTrue(locs[i][j].getNeighbors().size() == 1
                  || locs[i][j].getNeighbors().size() == 2
                  || locs[i][j].getNeighbors().size() == 3
                  || locs[i][j].getNeighbors().size() == 4);
        }
      }
    }
  }

  @Test
  public void cavesWithLocationsSameAsLocationsWithArrows() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    int count1 = 0;
    int count2 = 0;
    Location[][] locs = d6.getLocations();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].getTreasure().size() > 0) {
          count2++;
        }
        if (locs[i][j].getWeapons().size() > 0) {
          count1++;
        }
      }
    }
    assertEquals(count1, count2);

  }

  @Test
  public void numberOfMonstersAllocatedCorrectly() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    Location[][] locs = d6.getLocations();
    int count = 0;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (locs[i][j].isOtyughPresent()) {
          count++;
        }
      }
    }

    assertEquals(5,count);
  }

  @Test
  public void arrowMovesThroughIrrespectiveOfDirection() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    System.out.println(d6);

    p6.move("west");
    p6.move("west");
    assertEquals(2,p6.isMovePossible("south").getOtyugh().getHitsToKill());
    p6.move("east");
    p6.shootWeapon("west",1);
    p6.move("west");
    assertEquals(1,p6.isMovePossible("south").getOtyugh().getHitsToKill());

  }

  @Test
  public void game() {
    Dungeon d6 = new Dungeon("wrapping", 50, 6, 5, 3,
            5,
            new DefinedRandomClass());
    PlayerInterface p6 = new Player(d6);

    assertEquals(d6.getStart().getId(), p6.getCurrentLocation().getId());
    Location end = null;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 5; j++) {
        if (d6.getLocations()[i][j].isEnd()) {
          end = d6.getLocations()[i][j];
        }
      }
    }

    assertEquals("You are in a cave , Treasure Bag : [], "
            + "Weapon Bag : [ARROW, ARROW, ARROW] Possible moves : [north, south, west] "
            + " STRONG",p6.toString() + " " + p6.smell());


    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, south, west]  "
            + "STRONG",p6.toString() + " " + p6.smell());

    p6.move("west");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a tunnel , Treasure Bag : [RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [west, east]  STRONG",p6.toString() + " " + p6.smell());

    p6.move("west");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a tunnel , Treasure Bag : [RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [south, east]  STRONG",p6.toString() + " " + p6.smell());

    p6.shootWeapon("south",1);
    p6.smell();
    assertEquals("You are in a tunnel , Treasure Bag : [RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [south, east]  STRONG",p6.toString() + " " + p6.smell());

    p6.move("south");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, west, east]  STRONG",p6.toString() + " "
            + p6.smell());

    p6.shootWeapon("west",1);
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, west, east]  STRONG",p6.toString() + " "
            + p6.smell());

    p6.move("west");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, south, west, east]  STRONG",p6.toString() + " "
            + p6.smell());

    p6.move("south");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY],"
            + " Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, west, east]  STRONG",p6.toString() + " "
            + p6.smell());

    p6.move("west");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a tunnel , Treasure Bag : [RUBY, RUBY, RUBY, RUBY],"
            + " Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW]"
            + " Possible moves : [south, east]  WEAK",p6.toString() + " " + p6.smell());

    p6.move("south");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW]"
            + " Possible moves : [north, south, east] "
            + " NONE",p6.toString() + " " + p6.smell());

    p6.move("south");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY],"
            + " Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW]"
            + " Possible moves : [north, south, west, east] "
            + " WEAK",p6.toString() + " " + p6.smell());

    p6.move("east");
    p6.pickTreasure();
    p6.pickWeapons();
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY],"
            + " Weapon Bag : [ARROW, ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, south, west, east]  "
            + "STRONG",p6.toString() + " " + p6.smell());

    p6.shootWeapon("south",1);
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW, ARROW] "
            + "Possible moves : [north, south, west, east] "
            + " STRONG",p6.toString() + " " + p6.smell());

    p6.shootWeapon("south",1);
    p6.smell();
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY], "
            + "Weapon Bag : [ARROW, ARROW, ARROW] "
            + "Possible moves : [north, south, west, east]  "
            + "NONE",p6.toString() + " " + p6.smell());

    p6.move("south");
    assertEquals("You are in a cave , Treasure Bag : [RUBY, RUBY, RUBY, RUBY],"
            + " Weapon Bag : [ARROW, ARROW, ARROW] Possible moves : [north] "
            + " NONE",p6.toString() + " " + p6.smell());

    assertTrue(p6.didPlayerWin());
    assertEquals(p6.getCurrentLocation().getId(),end.getId());

  }
}
