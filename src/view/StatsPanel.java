package view;

import model.dungeons.PlayerInterface;
import model.dungeons.Treasure;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class extends JPanel and represents a panel that displays the statistics of the player
 * throughout the game.
 */
public class StatsPanel extends JPanel {

  /**
   * Constructor for the StatsPanel class. It initializes the panel components that are displayed
   * on the screen.
   *
   * @param model the model through which information to be displayed is retrieved.
   */
  public StatsPanel(PlayerInterface model) {

    this.setLayout(new GridLayout(0, 1));

    List<Treasure> r = model.getCurrentLocation().getTreasure();
    int rCount = 0;
    int sCount = 0;
    int dCount = 0;
    for (Treasure t : r) {
      if (t.equals(Treasure.RUBY)) {
        rCount++;
      }
      if (t.equals(Treasure.DIAMOND)) {
        dCount++;
      }
      if (t.equals(Treasure.SAPPHIRE)) {
        sCount++;
      }
    }

    JLabel treasureBag = new JLabel("(Press T to pick) Treasure Bag : ");
    add(treasureBag);
    JLabel treasureBagContent = new JLabel("Ruby: 0, Diamond: 0, Sapphire: 0");
    add(treasureBagContent);
    JLabel weaponBag = new JLabel("(Press W to pick) Weapon Bag : ");
    add(weaponBag);
    JLabel weaponBagContent = new JLabel(Integer.toString(model.getWeaponsBag().size()));
    add(weaponBagContent);
    JLabel locationDetails = new JLabel("Location Details : ");
    add(locationDetails);
    JLabel ruby = new JLabel("Ruby : ");
    add(ruby);
    JLabel rubyCount = new JLabel(Integer.toString(rCount));
    add(rubyCount);
    JLabel sapphire = new JLabel("Sapphire : ");
    add(sapphire);
    JLabel sapphireCount = new JLabel(Integer.toString(sCount));
    add(sapphireCount);
    JLabel diamond = new JLabel("Diamond : ");
    add(diamond);
    JLabel diamondCount = new JLabel(Integer.toString(dCount));
    add(diamondCount);
    JLabel smell = new JLabel("Smell : ");
    add(smell);
    JLabel smellLevel = new JLabel(model.smell().name());
    add(smellLevel);
    JLabel weapons = new JLabel("Weapons : ");
    add(weapons);
    JLabel weaponNumber = new JLabel(Integer
            .toString(model.getCurrentLocation().getWeapons().size()));
    add(weaponNumber);

    setVisible(true);

  }
}
