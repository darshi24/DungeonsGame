package model.random;

/**
 * An interface that provides functionalities to create randomness in various attributes of the
 * dungeon. This helps in creating a different setup of the dungeon every time a player wants to
 * play the game.
 */
public interface RandomInterface {

  /**
   * A method that generates a random number for the purpose of creating walls and entrances of
   * various locations in the dungeon.
   *
   * @param range range from which a number needs to be generated
   * @return a number from the specified range
   */
  public int getRandomNumberForEdges(int range);

  /**
   * A method that generates a random index to define the starting location of the player in the
   * dungeon.
   *
   * @param range the range from which the number needs to be generated.
   * @return a number from the specified range
   */
  public int getRandomStartIndex(int range);

  /**
   * A method that generates a random index to define the ending location (from a candidate list of
   * locations) of the player in the dungeon.
   *
   * @param range the range from which the number needs to be generated.
   * @return a number from the specified range
   */
  public int getNumberForEligibleEndLocation(int range);

  /**
   * A method that generates a random number within the given range.
   *
   * @param range the given range
   * @return a number within the given range
   */
  public int getNumberInRange(int range);

  /**
   * A method that returns a array of integers representing the location numbers where an otyugh
   * will be present.
   *
   * @param caves  the number of caves in which an otyugh can be present
   * @param number the number of otyughs that will be in the dungeon.
   * @return an array of integers representing the location numbers where an otyugh
   *         will be present.
   */
  public int[] getRandomOtyugh(int caves, int number);

  /**
   * A method that returns a random decision whether a player will be dead or not if it enters a
   * cave with an otyugh that is partially hit.
   *
   * @return true or false depending on the randomness
   */
  public boolean getProbabilityOfPlayerDead();

}
