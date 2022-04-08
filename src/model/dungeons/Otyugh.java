package model.dungeons;

/**
 * A class that represents a smelly monster Otyugh. Otyughs are located in caves in the dungeon.
 * An Otyugh is always located at the end position of the dungeon.
 */
public class Otyugh {
  private int hitsToKill;

  /**
   * A constructor for the Otyugh class. It initializes the number of hits that it takes to kill
   * the Otyugh.
   */
  public Otyugh() {
    hitsToKill = 2;
  }

  public int getHitsToKill() {
    return hitsToKill;
  }

  public void setHitsToKill(int hitsToKill) {
    this.hitsToKill = hitsToKill;
  }
}
