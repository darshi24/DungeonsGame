package model.dungeons;

import java.util.List;

/**
 * An interface that represents a player in the dungeon. A player starts from the starting location,
 * moves through the dungeon, collects treasures from the caves and ends at the ending location.
 */
public interface PlayerInterface {

  /**
   * A method that allows a player to move through either a wrapping or a non-wrapping dungeon.
   * If the player tries to move in a direction that has a wall, it stays at the same place.
   *
   * @param direction the direction (east, west, north south) in the the player wants to move.
   */
  public void move(String direction);

  /**
   * A method that allows the player to collect treasure when it visits a cave with treasure.
   * When a player reaches such a cave, it collects all the treasure that is present at that
   * location which then gets added to the player's treasure bag.
   */
  public int pickTreasure();

  /**
   * A method that allows the player to collect weapons (arrows) when it visits a location.
   * When a player reaches such a location, it collects all the weapons that are present at that
   * location which then gets added to the player's weapon bag.
   */
  public int pickWeapons();

  /**
   * A method that sets/updates the current location of a player. The current location will be
   * updated after every move.
   *
   * @param currentLocation the new location to be set as the current location
   */
  public void setCurrentLocation(Location currentLocation);

  /**
   * A method that retrieves the treasures present in the player's treasure bag.
   *
   * @return list of treasures present in the player's treasure bag
   */
  public List<Treasure> getTreasureBag();

  /**
   * A method that retrieves the current location of the player in the dungeon.
   *
   * @return a Location object that represents the current location of the object
   */
  public Location getCurrentLocation();

  /**
   * A method that returns the type of smell depending on how far an otyugh is from the current
   * position of the player.
   *
   * @return An enum representing the smell.
   */
  public Smell smell();

  /**
   * A method that takes an arrow from the weapon bag of the player nad shoots it to kill the
   * otyugh.
   *
   * @param direction the direction in which the player wants to shoot the arrow.
   * @param distance  the distance to which the player wants to shoot the arrow.
   * @return returns true is an otyugh was hit, false if it wasn;t hit.
   */
  public boolean shootWeapon(String direction, int distance);

  /**
   * A method that determines whether the player is dead. a player is dead if it ends up in a cave
   * with an otyugh having full health. The player has a 50% chance of dying if the otyugh's health
   * is half.
   *
   * @return true if the player is dead, false if the player is alive.
   */
  public boolean isPlayerDead();

  /**
   * A method that determines whether the player wins. A player wins if it reaches the end.
   *
   * @return true if the player wins, false otherwise
   */
  public boolean didPlayerWin();

  /**
   * A method that determines whether a move from the current location to the user's input location
   * is possible and then makes the player move to that position if possible. If the player enters
   * a direction in which it cannot move, it stays at the same place.
   *
   * @param direction the direction in which the player wants to move
   * @return the new location that the player moved to and null if the player cannot move in the
   *          specified direction
   */
  public Location isMovePossible(String direction);

  /**
   * A method that retrieves the contents of the weapon bag of the player.
   *
   * @return list of weapons (arrows) that the player has in its bag.
   */
  public List<Weapon> getWeaponsBag();

  /**
   * A method to put in the weapons (arrows) that were found by the player at any location.
   *
   * @param weaponsBag the weapons (arrows) that were found by the player at the location it
   *                   stepped onto
   */
  void setWeaponsBag(List<Weapon> weaponsBag);

  /**
   * A method to get the dungeon object that the player is currently in.
   *
   * @return object of type Dungeon
   */
  public Dungeon getDungeon();

  /**
   * A method that determines whether a player can move to teh specified location.
   *
   * @param locationToMoveTo the location that the player want to move to
   * @return true if the player can move to the specifies direction, otherwise false.
   */
  boolean canPlayerMoveHere(Location locationToMoveTo);

}
