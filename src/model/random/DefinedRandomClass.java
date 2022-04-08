package model.random;

/**
 * A class that implements the RandomInterface. The purpose of this class is to help to test the
 * various functionalities that are actually random in the dungeon. It implements all the functions
 * that help to generate randomness in such a way that can be used for testing.
 */
public class DefinedRandomClass implements RandomInterface {


  @Override
  public int getRandomNumberForEdges(int range) {
    return 0;
  }

  @Override
  public int getRandomStartIndex(int range) {
    return 0;
  }

  @Override
  public int getNumberForEligibleEndLocation(int range) {
    return range - 1;
  }

  @Override
  public int getNumberInRange(int range) {
    return 0;
  }

  @Override
  public int[] getRandomOtyugh(int caves, int number) {
    int num = Math.min(caves, number);
    int[] positions = new int[num];
    for (int i = 0; i < num; i++) {
      positions[i] = i;
    }
    return positions;
  }

  @Override
  public boolean getProbabilityOfPlayerDead() {
    return false;
  }
}
