package model.dungeons;

/**
 * This interface represents a location in the dungeon. Each location in the grid represents a
 * location in the dungeon where a player can explore and can be connected to at most four (4)
 * other locations: one to the north, one to the east, one to the south, and one to the west.
 */
public interface LocationInterface {

  /**
   * A method that sets neighbors of a location in the dungeon during the setup of the dungeon.
   * Neighbors represent the possible moves of a player from a certain location.
   *
   * @param location location whose neighbors have to be set.
   */
  public void setNeighbor(Location location);
}
