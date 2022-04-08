package model.dungeons;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the Location interface. A location can either be of type tunnel or cave.
 * A location can be classified as tunnel (which has exactly 2 entrances) or a cave
 * (which has 1, 3 or 4 entrances). Some caves have one or more treasure that the player can
 * collect and put in its treasure bag. A cave can have one or more (maximum 3) treasure(s).
 */
public class Location implements LocationInterface {
  private static int count = 0;
  private final int id;
  private boolean isStart;
  private boolean isEnd;
  private List<Treasure> treasure;
  private final List<Location> neighbors;
  private List<Weapon> weapons;
  private boolean isOtyughPresent;
  private Otyugh otyugh;

  /**
   * Constructor for the class Location. It creates a default location with a unique id whose
   * attributes get updated as the player moves through the dungeon.
   */
  public Location() {
    id = ++count;
    isStart = false;
    isEnd = false;
    treasure = new ArrayList<>();
    neighbors = new ArrayList<>();
    weapons = new ArrayList<>();
    isOtyughPresent = false;
    otyugh = null;
  }

  @Override
  public void setNeighbor(Location location) {
    this.neighbors.add(location);
  }

  @Override
  public String toString() {
    return Integer.toString(id);
  }

  public List<Location> getNeighbors() {
    return neighbors;
  }

  public int getId() {
    return id;
  }

  public void setTreasure(List<Treasure> treasure) {
    this.treasure = treasure;
  }

  public List<Treasure> getTreasure() {
    return treasure;
  }

  public void setStart(boolean start) {
    isStart = start;
  }

  public void setEnd(boolean end) {
    isEnd = end;
  }

  public boolean isStart() {
    return isStart;
  }

  public boolean isEnd() {
    return isEnd;
  }

  public List<Weapon> getWeapons() {
    return weapons;
  }

  public void setWeapons(List<Weapon> weapons) {
    this.weapons = weapons;
  }

  public boolean isOtyughPresent() {
    return isOtyughPresent;
  }

  public void setOtyughPresent(boolean otyughPresent) {
    isOtyughPresent = otyughPresent;
  }

  public Otyugh getOtyugh() {
    return otyugh;
  }

  public void setOtyugh(Otyugh otyugh) {
    this.otyugh = otyugh;
  }

  public static void setCount(int count) {
    Location.count = count;
  }
}
