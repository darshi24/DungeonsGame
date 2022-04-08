package model.dungeons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that implements the PlayerInterface. A player visits various locations in the dungeon
 * starting from the starting location. Each player in the dungeon has a treasure bag that
 * gets filled as the player visits various caves in the dungeon and collects treasure. Finally, a
 * player reaches at the end location with a bag that has some/no treasure.
 */
public class Player implements PlayerInterface {

  private List<Treasure> treasureBag;
  private Location currentLocation;
  private List<Weapon> weaponsBag;
  private final Dungeon dungeon;

  /**
   * Constructor for the class Player. It creates a player with an empty treasure bag and places it
   * at the starting location of the dungeon.
   *
   * @param dungeon the dungeon that the player will be navigating
   */
  public Player(Dungeon dungeon) {
    treasureBag = new ArrayList<>();
    currentLocation = dungeon.getStart();
    List<Weapon> initial = new ArrayList<>();
    initial.add(Weapon.ARROW);
    initial.add(Weapon.ARROW);
    initial.add(Weapon.ARROW);
    this.weaponsBag = initial;
    this.dungeon = dungeon;
  }

  @Override
  public void move(String direction) {
    if (dungeon.getDungeonType().equals("non-wrapping")) {
      switch (direction) {
        case "east":
          List<Location> neighbors1 = currentLocation.getNeighbors();
          List<Integer> neighborIds1 = new ArrayList<>();
          for (Location l : neighbors1) {
            neighborIds1.add(l.getId());
            if (neighborIds1.contains(currentLocation.getId() + 1)) {
              currentLocation = l;
              break;
            }
          }
          break;
        case "west":
          List<Location> neighbors2 = currentLocation.getNeighbors();
          List<Integer> neighborIds2 = new ArrayList<>();
          for (Location l : neighbors2) {
            neighborIds2.add(l.getId());
            if (neighborIds2.contains(currentLocation.getId() - 1)) {
              currentLocation = l;
              break;
            }
          }
          break;
        case "north":
          List<Location> neighbors3 = currentLocation.getNeighbors();
          List<Integer> neighborIds3 = new ArrayList<>();
          for (Location l : neighbors3) {
            neighborIds3.add(l.getId());
            if (neighborIds3.contains(currentLocation.getId() - dungeon.getColsDungeon())) {
              currentLocation = l;
              break;
            }
          }
          break;
        case "south":
          List<Location> neighbors4 = currentLocation.getNeighbors();
          List<Integer> neighborIds4 = new ArrayList<>();
          for (Location l : neighbors4) {
            neighborIds4.add(l.getId());
            if (neighborIds4.contains(currentLocation.getId() + dungeon.getColsDungeon())) {
              currentLocation = l;
              break;
            }
          }
          break;
        default:
          break;
      }
    } else {

      switch (direction) {
        case "east":
          List<Location> neighbors1 = currentLocation.getNeighbors();
          List<Integer> neighborIds1 = new ArrayList<>();
          for (Location l : neighbors1) {
            neighborIds1.add(l.getId());
            if (currentLocation.getId() % dungeon.getColsDungeon() == 0) {
              if (neighborIds1.contains(currentLocation.getId() - (dungeon.getColsDungeon() - 1))) {
                currentLocation = l;
                break;
              }
            } else {
              if (neighborIds1.contains(currentLocation.getId() + 1)) {
                currentLocation = l;
                break;
              }
            }

          }
          break;
        case "west":
          List<Location> neighbors2 = currentLocation.getNeighbors();
          List<Integer> neighborIds2 = new ArrayList<>();
          for (Location l : neighbors2) {
            neighborIds2.add(l.getId());
            if ((currentLocation.getId() - 1) % dungeon.getColsDungeon() == 0) {
              if (neighborIds2.contains(currentLocation.getId() + (dungeon.getColsDungeon() - 1))) {
                currentLocation = l;
                break;
              }
            } else {
              if (neighborIds2.contains(currentLocation.getId() - 1)) {
                currentLocation = l;
                break;
              }
            }
          }
          break;

        case "north":
          List<Location> neighbors3 = currentLocation.getNeighbors();
          List<Integer> neighborIds3 = new ArrayList<>();
          for (Location l : neighbors3) {
            neighborIds3.add(l.getId());

            if (currentLocation.getId() >= 1
                    && currentLocation.getId() <= dungeon.getColsDungeon()) {
              if (neighborIds3.contains(currentLocation.getId()
                      + (dungeon.getColsDungeon() * (dungeon.getRowsDungeon() - 1)))) {
                currentLocation = l;
                break;
              }
            } else {
              if (neighborIds3.contains(currentLocation.getId() - dungeon.getColsDungeon())) {
                currentLocation = l;
                break;
              }
            }

          }
          break;
        case "south":
          List<Location> neighbors4 = currentLocation.getNeighbors();
          List<Integer> neighborIds4 = new ArrayList<>();
          for (Location l : neighbors4) {
            neighborIds4.add(l.getId());

            if (currentLocation.getId() <= dungeon.getRowsDungeon() * dungeon.getColsDungeon()
                    && currentLocation.getId()
                    >= (dungeon.getRowsDungeon() * dungeon.getColsDungeon())
                    - (dungeon.getColsDungeon() - 1)) {
              if (neighborIds4.contains(currentLocation.getId()
                      - (dungeon.getColsDungeon() * (dungeon.getRowsDungeon() - 1)))) {
                currentLocation = l;
                break;
              }
            } else {
              if (neighborIds4.contains(currentLocation.getId() + dungeon.getColsDungeon())) {
                currentLocation = l;
                break;
              }
            }

          }
          break;
        default:
          break;
      }
    }

  }

  @Override
  public int pickTreasure() {
    this.treasureBag.addAll(currentLocation.getTreasure());
    int found = currentLocation.getTreasure().size();
    currentLocation.setTreasure(new ArrayList<>());
    return found;
  }

  @Override
  public int pickWeapons() {
    this.weaponsBag.addAll(currentLocation.getWeapons());
    int found = currentLocation.getWeapons().size();
    currentLocation.setWeapons(new ArrayList<>());
    return found;
  }

  @Override
  public void setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
  }

  @Override
  public List<Treasure> getTreasureBag() {
    return treasureBag;
  }

  @Override
  public Location getCurrentLocation() {
    return currentLocation;
  }

  @Override
  public Smell smell() {
    List<Location> level1 = currentLocation.getNeighbors();
    List<Location> level2 = new ArrayList<>();
    for (Location l : level1) {
      level2.addAll(l.getNeighbors());
    }

    for (Location l : level1) {
      if (l.isOtyughPresent()) {
        return Smell.STRONG;
      }
    }

    int count = 0;
    for (Location l : level2) {
      if (l.isOtyughPresent()) {
        count++;
      }
    }

    if (count == 1) {
      return Smell.WEAK;
    } else if (count >= 2) {
      return Smell.STRONG;
    }

    return Smell.NONE;
  }

  @Override
  public boolean shootWeapon(String direction, int distance) {

    if (weaponsBag.isEmpty()) {
      return false; // bag empty
    }
    Location loc = getCurrentLocation();
    Location prev = getCurrentLocation();
    int count = 0;
    while (distance > 0) {
      if (loc.getNeighbors().size() != 2) {
        if (isMovePossible(direction) != null) {
          prev = loc;
          loc = isMovePossible(direction);
          distance--;
        } else {
          return false; //move not possible
        }

      } else {
        count++;
        if (count > 50) {
          return false; //tunnel loop
        }
        for (Location l : loc.getNeighbors()) {
          if (l.getId() != prev.getId()) {
            prev = loc;
            loc = l;
            break;
          }
        }
      }
    }

    if (loc.isOtyughPresent() && loc.getOtyugh() != null) {
      if (loc.getOtyugh().getHitsToKill() == 2) {
        loc.getOtyugh().setHitsToKill(1);
      } else if (loc.getOtyugh().getHitsToKill() == 1) {
        loc.setOtyughPresent(false);
        loc.setOtyugh(null);
      }

      weaponsBag.remove(0);
      return true;
    }

    weaponsBag.remove(0);
    return false; // no otyugh found to kill
  }

  @Override
  public String toString() {

    return String.format("You are in a %s , Treasure Bag : %s, Weapon Bag : %s "
                    + "Possible moves : %s ",
            this.currentLocation.getNeighbors().size() == 2 ? "tunnel" : "cave", this.treasureBag,
            this.weaponsBag, possibleMoves().toString());
  }

  @Override
  public List<Weapon> getWeaponsBag() {
    return weaponsBag;
  }

  @Override
  public void setWeaponsBag(List<Weapon> weaponsBag) {
    this.weaponsBag = weaponsBag;
  }


  @Override
  public Location isMovePossible(String direction) {
    if (dungeon.getDungeonType().equals("non-wrapping")) {
      switch (direction) {
        case "east":
          List<Location> neighbors1 = currentLocation.getNeighbors();
          List<Integer> neighborIds1 = new ArrayList<>();
          for (Location l : neighbors1) {
            neighborIds1.add(l.getId());
            if (neighborIds1.contains(currentLocation.getId() + 1)) {
              return l;
            }
          }
          break;
        case "west":
          List<Location> neighbors2 = currentLocation.getNeighbors();
          List<Integer> neighborIds2 = new ArrayList<>();
          for (Location l : neighbors2) {
            neighborIds2.add(l.getId());
            if (neighborIds2.contains(currentLocation.getId() - 1)) {
              return l;
            }
          }
          break;
        case "north":
          List<Location> neighbors3 = currentLocation.getNeighbors();
          List<Integer> neighborIds3 = new ArrayList<>();
          for (Location l : neighbors3) {
            neighborIds3.add(l.getId());
            if (neighborIds3.contains(currentLocation.getId() - dungeon.getColsDungeon())) {
              return l;
            }
          }
          break;
        case "south":
          List<Location> neighbors4 = currentLocation.getNeighbors();
          List<Integer> neighborIds4 = new ArrayList<>();
          for (Location l : neighbors4) {
            neighborIds4.add(l.getId());
            if (neighborIds4.contains(currentLocation.getId() + dungeon.getColsDungeon())) {
              return l;
            }
          }
          break;
        default:
          break;
      }
    } else {

      switch (direction) {
        case "east":
          List<Location> neighbors1 = currentLocation.getNeighbors();
          List<Integer> neighborIds1 = new ArrayList<>();
          for (Location l : neighbors1) {
            neighborIds1.add(l.getId());
            if (currentLocation.getId() % dungeon.getColsDungeon() == 0) {
              if (neighborIds1.contains(currentLocation.getId() - (dungeon.getColsDungeon() - 1))) {
                return l;
              }
            } else {
              if (neighborIds1.contains(currentLocation.getId() + 1)) {
                return l;
              }
            }

          }
          break;
        case "west":
          List<Location> neighbors2 = currentLocation.getNeighbors();
          List<Integer> neighborIds2 = new ArrayList<>();
          for (Location l : neighbors2) {
            neighborIds2.add(l.getId());
            if ((currentLocation.getId() - 1) % dungeon.getColsDungeon() == 0) {
              if (neighborIds2.contains(currentLocation.getId() + (dungeon.getColsDungeon() - 1))) {
                return l;
              }
            } else {
              if (neighborIds2.contains(currentLocation.getId() - 1)) {
                return l;
              }
            }
          }
          break;

        case "north":
          List<Location> neighbors3 = currentLocation.getNeighbors();
          List<Integer> neighborIds3 = new ArrayList<>();
          for (Location l : neighbors3) {
            neighborIds3.add(l.getId());

            if (currentLocation.getId() >= 1
                    && currentLocation.getId() <= dungeon.getColsDungeon()) {
              if (neighborIds3.contains(currentLocation.getId()
                      + (dungeon.getColsDungeon() * (dungeon.getRowsDungeon() - 1)))) {
                return l;
              }
            } else {
              if (neighborIds3.contains(currentLocation.getId() - dungeon.getColsDungeon())) {
                return l;
              }
            }

          }
          break;
        case "south":
          List<Location> neighbors4 = currentLocation.getNeighbors();
          List<Integer> neighborIds4 = new ArrayList<>();
          for (Location l : neighbors4) {
            neighborIds4.add(l.getId());

            if (currentLocation.getId() <= dungeon.getRowsDungeon() * dungeon.getColsDungeon()
                    && currentLocation.getId()
                    >= (dungeon.getRowsDungeon() * dungeon.getColsDungeon())
                    - (dungeon.getColsDungeon() - 1)) {
              if (neighborIds4.contains(currentLocation.getId()
                      - (dungeon.getColsDungeon() * (dungeon.getRowsDungeon() - 1)))) {
                return l;
              }
            } else {
              if (neighborIds4.contains(currentLocation.getId() + dungeon.getColsDungeon())) {
                return l;
              }
            }

          }
          break;
        default:
          break;
      }
    }
    return null;
  }

  private List<String> possibleMoves() {
    List<String> directions = new ArrayList<>(Arrays.asList("north", "south", "west", "east"));
    List<String> possibleMoves = new ArrayList<>();
    for (String dir : directions) {
      if (isMovePossible(dir) != null) {
        possibleMoves.add(dir);
      }
    }
    return possibleMoves;
  }

  @Override
  public boolean isPlayerDead() {
    if (currentLocation.isOtyughPresent()) {
      if (currentLocation.getOtyugh().getHitsToKill() == 2) {
        return true;
      } else if (currentLocation.getOtyugh().getHitsToKill() == 1) {
        return dungeon.getRand().getProbabilityOfPlayerDead();
      }

    }
    return false;
  }

  @Override
  public boolean didPlayerWin() {
    return currentLocation.isEnd();
  }

  public Dungeon getDungeon() {
    return dungeon;
  }

  @Override
  public boolean canPlayerMoveHere(Location locationToMoveTo) {
    return currentLocation.getNeighbors().contains(locationToMoveTo);
  }
}
