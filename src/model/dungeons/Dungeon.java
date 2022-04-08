package model.dungeons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.random.RandomInterface;



/**
 * This class represents a dungeon which is a network of interconnected tunnels and caves
 * (locations in the dungeon). A dungeon can be wrapping or non-wrapping meaning that the locations
 * in the dungeon can either wrap to the one on the other side or not. A dungeon has a degree of
 * interconnectivity. Increasing the degree of interconnectivity increases the number of paths
 * between caves. Certain caves in the dungeons have treasures that the player can collect. The
 * dungeon is generated at random following some set of constraints resulting in a different
 * network each time the game begins.
 */
public class Dungeon {

  private final int rowsDungeon;
  private final int colsDungeon;
  private final int percentageDungeon;
  private final String typeDungeon;
  private final Location[][] locations;
  private final Location start;
  private final RandomInterface rand;

  /**
   * Constructor for the Dungeon class. It sets up the dungeon, the starting and the ending
   * locations before the player starts exploring it.
   *
   * @param dungeonType       type of the dungeon to be created (wrapping or non-wrapping)
   * @param percentage        percentage of the caves in the dungeon that can have treasure
   * @param rows              number of rows in the dungeon
   * @param cols              number of columns in the dungeon
   * @param interconnectivity the interconnectivity of the dungeon. an interconnectivity = 0
   *                          when there is exactly one path from every cave in the dungeon to
   *                          every other cave in the dungeon. Increasing the degree of
   *                          interconnectivity increases the number of paths between caves.
   * @param rand              an object that implements RandomInterface to create randomness in
   *                          various attributes of the dungeon
   */
  public Dungeon(String dungeonType, int percentage, int rows, int cols, int interconnectivity,
                 int otyugh,
                 RandomInterface rand) {

    if (rows < 5) {
      throw new IllegalArgumentException("Not enough rows ! Must be greater of equal to 5.");
    }
    rowsDungeon = rows;
    if (cols < 5) {
      throw new IllegalArgumentException("Not enough columns ! Must be greater of equal to 5.");
    }
    colsDungeon = cols;
    if (percentage <= 0) {
      throw new IllegalArgumentException("Percentage cannot be negative or zero");
    }
    percentageDungeon = percentage;

    if (!dungeonType.equals("wrapping") && !dungeonType.equals("non-wrapping")) {
      throw new IllegalArgumentException("Invalid type of dungeon");
    }
    typeDungeon = dungeonType;

    if (interconnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity cannot be negative");
    }

    if (otyugh > (rowsDungeon * colsDungeon) / 2) {
      throw new IllegalArgumentException("Maximum otyughs allowed are : "
              + (rowsDungeon * colsDungeon) / 2);
    }

    if (rand == null) {
      throw new IllegalArgumentException("RandomInterface object not passed");
    }
    this.rand = rand;
    locations = new Location[rows][cols];

    Location.setCount(0);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        locations[i][j] = new Location();
      }
    }
    ArrayList<Set<Location>> sets = new ArrayList<>(rows * cols);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Set<Location> s = new HashSet<>();
        s.add(locations[i][j]);
        sets.add(s);
      }
    }
    Set<Set<Location>> edges = new HashSet<>();


    for (int x = 0; x < cols; x++) {
      if (x == 0) {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[0][x]);
        tmp1.add(locations[0][x + 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[0][x]);
        tmp2.add(locations[1][x]);
        edges.add(tmp2);

        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp3 = new HashSet<>();
          tmp3.add(locations[0][x]);
          tmp3.add(locations[rows - 1][0]);
          edges.add(tmp3);


          Set<Location> tmp4 = new HashSet<>();
          tmp4.add(locations[0][x]);
          tmp4.add(locations[0][cols - 1]);
          edges.add(tmp4);

        }
      } else if (x == cols - 1) {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[0][x]);
        tmp1.add(locations[0][x - 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[0][x]);
        tmp2.add(locations[1][x]);
        edges.add(tmp2);

        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp3 = new HashSet<>();
          tmp3.add(locations[0][x]);
          tmp3.add(locations[rows - 1][cols - 1]);
          edges.add(tmp3);


          Set<Location> tmp4 = new HashSet<>();
          tmp4.add(locations[0][x]);
          tmp4.add(locations[0][0]);
          edges.add(tmp4);

        }
      } else {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[0][x]);
        tmp1.add(locations[0][x + 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[0][x]);
        tmp2.add(locations[0][x - 1]);
        edges.add(tmp2);


        Set<Location> tmp4 = new HashSet<>();
        tmp4.add(locations[0][x]);
        tmp4.add(locations[1][x]);
        edges.add(tmp4);

        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp3 = new HashSet<>();
          tmp3.add(locations[0][x]);
          tmp3.add(locations[rows - 1][x]);
          edges.add(tmp3);

        }
      }
    }

    for (int x = 0; x < cols; x++) {
      if (x == 0) {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[rows - 1][x]);
        tmp1.add(locations[rows - 1][x + 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[rows - 1][x]);
        tmp2.add(locations[rows - 2][x]);
        edges.add(tmp2);

        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp3 = new HashSet<>();
          tmp3.add(locations[rows - 1][0]);
          tmp3.add(locations[0][0]);
          edges.add(tmp3);


          Set<Location> tmp4 = new HashSet<>();
          tmp4.add(locations[rows - 1][0]);
          tmp4.add(locations[rows - 1][cols - 1]);
          edges.add(tmp4);

        }
      } else if (x == cols - 1) {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[rows - 1][x]);
        tmp1.add(locations[rows - 1][x - 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[rows - 1][x]);
        tmp2.add(locations[rows - 2][x]);
        edges.add(tmp2);


        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp3 = new HashSet<>();
          tmp3.add(locations[rows - 1][x]);
          tmp3.add(locations[0][x]);
          edges.add(tmp3);


          Set<Location> tmp4 = new HashSet<>();
          tmp4.add(locations[rows - 1][x]);
          tmp4.add(locations[rows - 1][0]);
          edges.add(tmp4);

        }
      } else {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[rows - 1][x]);
        tmp1.add(locations[rows - 1][x + 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[rows - 1][x]);
        tmp2.add(locations[rows - 1][x - 1]);
        edges.add(tmp2);


        Set<Location> tmp3 = new HashSet<>();
        tmp3.add(locations[rows - 1][x]);
        tmp3.add(locations[rows - 2][x]);
        edges.add(tmp3);

        if (dungeonType.equals("wrapping")) {

          Set<Location> tmp4 = new HashSet<>();
          tmp4.add(locations[rows - 1][x]);
          tmp4.add(locations[0][x]);
          edges.add(tmp4);

        }

      }
    }

    for (int x = 1; x < rows - 2; x++) {

      Set<Location> tmp1 = new HashSet<>();
      tmp1.add(locations[x][0]);
      tmp1.add(locations[x + 1][0]);
      edges.add(tmp1);


      Set<Location> tmp2 = new HashSet<>();
      tmp2.add(locations[x][0]);
      tmp2.add(locations[x - 1][0]);
      edges.add(tmp2);

      Set<Location> tmp3 = new HashSet<>();
      tmp3.add(locations[x][0]);
      tmp3.add(locations[x][1]);
      edges.add(tmp3);

      if (dungeonType.equals("wrapping")) {

        Set<Location> tmp4 = new HashSet<>();
        tmp4.add(locations[x][0]);
        tmp4.add(locations[x][cols - 1]);
        edges.add(tmp4);
      }

      Set<Location> temp1 = new HashSet<>();
      temp1.add(locations[x][cols - 1]);
      temp1.add(locations[x + 1][cols - 1]);
      edges.add(temp1);


      Set<Location> temp2 = new HashSet<>();
      temp2.add(locations[x][cols - 1]);
      temp2.add(locations[x - 1][cols - 1]);
      edges.add(temp2);

      Set<Location> temp3 = new HashSet<>();
      temp3.add(locations[x][cols - 1]);
      temp3.add(locations[x][cols - 2]);
      edges.add(temp3);


      if (dungeonType.equals("wrapping")) {
        Set<Location> temp4 = new HashSet<>();
        temp4.add(locations[x][cols - 1]);
        temp4.add(locations[x][0]);
        edges.add(temp4);
      }
    }

    for (int i = 1; i < rows - 1; i++) {
      for (int j = 1; j < cols - 1; j++) {

        Set<Location> tmp1 = new HashSet<>();
        tmp1.add(locations[i][j]);
        tmp1.add(locations[i][j + 1]);
        edges.add(tmp1);


        Set<Location> tmp2 = new HashSet<>();
        tmp2.add(locations[i][j]);
        tmp2.add(locations[i][j - 1]);
        edges.add(tmp2);

        Set<Location> tmp3 = new HashSet<>();
        tmp3.add(locations[i][j]);
        tmp3.add(locations[i - 1][j]);
        edges.add(tmp3);

        Set<Location> tmp4 = new HashSet<>();
        tmp4.add(locations[i][j]);
        tmp4.add(locations[i + 1][j]);
        edges.add(tmp4);
      }
    }


    List<List<Location>> setList = new ArrayList<>();
    for (Set<Location> s1 : sets) {
      List<Location> sub = new ArrayList<>();
      sub.addAll(s1);
      setList.add(sub);
    }

    List<List<Location>> locationList = new ArrayList<>();
    for (Set<Location> s1 : edges) {
      List<Location> sub = new ArrayList<>();
      sub.addAll(s1);
      locationList.add(sub);
    }


    while (sets.size() != 1) {
      int range = locationList.size();
      int number = rand.getRandomNumberForEdges(range);
      List<Location> list1 = locationList.get(number);
      locationList.remove(list1);

      Set<Location> set1 = new HashSet<>();
      Set<Location> set2 = new HashSet<>();

      for (Set<Location> s : sets) {
        if (s.contains(list1.get(0))) {
          set1 = s;
        }

        if (s.contains(list1.get(1))) {
          set2 = s;
        }

      }

      Set<Location> temp = new HashSet<>();
      temp.addAll(set1);
      Set<Location> temp1 = new HashSet<>();
      temp1.addAll(set1);

      temp.retainAll(set2);
      if (temp.isEmpty()) {

        Location l1 = list1.get(0);
        Location l2 = list1.get(1);

        l1.setNeighbor(l2);
        l2.setNeighbor(l1);

        set1.addAll(set2);
        sets.remove(temp1);
        sets.remove(set2);
      }

    }

    while (interconnectivity != 0) {
      if (locationList.size() == 0) {
        break;
      }
      int range = locationList.size();
      int number = rand.getRandomNumberForEdges(range);
      List<Location> list1 = locationList.get(number);
      locationList.remove(list1);
      Location l1 = list1.get(0);
      Location l2 = list1.get(1);
      l1.setNeighbor(l2);
      l2.setNeighbor(l1);
      interconnectivity = interconnectivity - 1;

    }

    int randx = rand.getRandomStartIndex(rowsDungeon);
    int randy = rand.getRandomStartIndex(colsDungeon);

    Location startlocation = locations[randx][randy];
    while (startlocation.getNeighbors().size() == 2) {
      randx = rand.getRandomStartIndex(rowsDungeon);
      randy = rand.getRandomStartIndex(colsDungeon);
      startlocation = locations[randx][randy];
    }


    startlocation.setStart(true);
    start = locations[randx][randy];


    List<Location> eligibleEndLocations = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (locations[i][j] != startlocation) {
          eligibleEndLocations.add(locations[i][j]);
        }
      }
    }
    for (Location n : startlocation.getNeighbors()) {
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

    if (eligibleEndLocations.size() == 0) {
      throw new IllegalArgumentException("No valid ending locations found. Create a new dungeon");
    }

    int numberForEligibleEndLocation =
            rand.getNumberForEligibleEndLocation(eligibleEndLocations.size());
    Location endlocation = eligibleEndLocations.get(numberForEligibleEndLocation);
    endlocation.setEnd(true);
    endlocation.setOtyughPresent(true);


    Location[][] locationCopy = locations;
    List<Location> treasureCaves = new ArrayList<>();
    for (int i = 0; i < getRowsDungeon(); i++) {
      for (int j = 0; j < getColsDungeon(); j++) {
        if ((locationCopy[i][j].getNeighbors().size() != 2)) {
          treasureCaves.add(locationCopy[i][j]);
        }
      }
    }
    int numberOfCavesWithTreasure = (treasureCaves.size() * getPercentage()) / 100;

    for (int i = 0; i < numberOfCavesWithTreasure; i++) {
      Location l = treasureCaves.get(rand.getNumberInRange(treasureCaves.size()));
      treasureCaves.remove(l);
      List<Treasure> treasures = new ArrayList<>();
      int numberOfTreasure = rand.getNumberInRange(3) + 1;
      for (int j = 0; j < numberOfTreasure; j++) {
        int x = rand.getNumberInRange(3);
        switch (x) {
          case 0:
            treasures.add(Treasure.RUBY);
            break;
          case 1:
            treasures.add(Treasure.SAPPHIRE);
            break;
          case 2:
            treasures.add(Treasure.DIAMOND);
            break;
          default:
            break;
        }
      }
      l.setTreasure(treasures);
    }



    List<Location> locationCopy2 = new ArrayList<>();
    for (int i = 0; i < getRowsDungeon(); i++) {
      for (int j = 0; j < getColsDungeon(); j++) {
        locationCopy2.add(locations[i][j]);
      }
    }

    int numberOfLocationsWithArrows = numberOfCavesWithTreasure;
    for (int i = 0; i < numberOfLocationsWithArrows; i++) {
      int num = rand.getNumberInRange(locationCopy2.size());
      Location loc = locationCopy2.get(num);
      int num1 = rand.getNumberInRange(4) + 1;
      List<Weapon> weaponsToAdd = new ArrayList<>();
      while (num1 > 0) {
        weaponsToAdd.add(Weapon.ARROW);
        num1--;
      }
      loc.setWeapons(weaponsToAdd);
      locationCopy2.remove(loc);
    }


    Location[][] locationCopy3 = locations;
    List<Location> treasureCaves2 = new ArrayList<>();
    for (int i = 0; i < getRowsDungeon(); i++) {
      for (int j = 0; j < getColsDungeon(); j++) {
        if ((locationCopy3[i][j].getNeighbors().size() != 2)) {
          treasureCaves2.add(locationCopy3[i][j]);
        }
      }
    }
    List<Location> otyughCaves = new ArrayList<>();
    for (Location l : treasureCaves2) {
      if (!l.isStart() && !l.isEnd()) {
        otyughCaves.add(l);
      }
    }

    int[] positions = rand.getRandomOtyugh(otyughCaves.size() - 1, otyugh - 1);

    for (int x : positions) {
      otyughCaves.get(x).setOtyughPresent(true);
      otyughCaves.get(x).setOtyugh(new Otyugh());
    }

    endlocation.setOtyughPresent(true);
    endlocation.setOtyugh(new Otyugh());

  }

  public int getRowsDungeon() {
    return rowsDungeon;
  }

  public int getColsDungeon() {
    return colsDungeon;
  }

  public Location[][] getLocations() {
    return locations;
  }

  public int getPercentage() {
    return percentageDungeon;
  }

  public String getDungeonType() {
    return typeDungeon;
  }

  public Location getStart() {
    return start;
  }

  public RandomInterface getRand() {
    return rand;
  }

  @Override
  public String toString() {
    StringBuilder dungeonRep = new StringBuilder();

    for (int i = 0; i < rowsDungeon; i++) {
      for (int j = 0; j < colsDungeon; j++) {
        dungeonRep.append("Neighbors of ").append(locations[i][j].getId()).append(": ")
                .append(locations[i][j].getNeighbors().toString()).append("\n");
      }
    }
    return dungeonRep.toString();
  }

}
