package model.random;

import java.util.Random;

/**
 * A class that implements the RandomInterface. It implements all the functions that help
 * to generate randomness in various attributes of the dungeon.
 */
public class RandomClass implements RandomInterface {
  Random r = new Random();

  @Override
  public int getRandomNumberForEdges(int range) {
    return r.nextInt(range);
  }

  @Override
  public int getRandomStartIndex(int range) {
    return r.nextInt(range);
  }

  @Override
  public int getNumberForEligibleEndLocation(int range) {
    return r.nextInt(range);
  }

  @Override
  public int getNumberInRange(int range) {
    return r.nextInt(range);
  }

  @Override
  public int[] getRandomOtyugh(int caves, int number) {
    if (number > caves) {
      int num = caves;
      int[] positions = new int[num];
      for (int i = 0; i < num; i++) {
        positions[i] = i;
      }
      return positions;
    }
    int[] caveNumbers = new int[number];
    for (int i = 0; i < number; i++) {
      caveNumbers[i] = r.nextInt(caves);
    }
    return caveNumbers;
  }

  @Override
  public boolean getProbabilityOfPlayerDead() {
    return Math.random() > 0.5;
  }
}
