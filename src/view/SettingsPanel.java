package view;

import controller.GuiControllerInterface;
import model.dungeons.Dungeon;
import model.dungeons.Player;
import model.random.RandomClass;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * A class that extends JPanel and represents a Panel that is displayed when the player wants to
 * specify the configurations of the game.
 */
public class SettingsPanel extends JPanel {

  JLabel rowsLabel;
  JLabel colsLabel;
  JLabel dungeonTypeLabel;
  JLabel interconnectivityLabel;
  JLabel percentageOfCavesLabel;
  JLabel numberOfOtyughsLabel;

  /**
   * Constructor for the SettingsPanel class. It initializes the panel components.
   *
   * @param controller the controller that acts as a listener for various events made by the user.
   */
  public SettingsPanel(GuiControllerInterface controller) {

    this.setLayout(new GridLayout(0, 2));
    rowsLabel = new JLabel("Enter rows in dungeon:");


    colsLabel = new JLabel("Enter cols in dungeon:");

    dungeonTypeLabel = new JLabel("Enter type of dungeon");

    interconnectivityLabel = new JLabel("Enter interconnectivity in dungeon:");

    percentageOfCavesLabel = new JLabel("Enter percentage of treasure:");

    numberOfOtyughsLabel = new JLabel("Enter number of otyughs in caves:");


    add(rowsLabel);
    JTextField rows = new JTextField();
    add(rows);
    add(colsLabel);
    JTextField cols = new JTextField();
    add(cols);
    add(dungeonTypeLabel);
    JTextField dungeonType = new JTextField();
    add(dungeonType);
    add(interconnectivityLabel);
    JTextField interconnectivity = new JTextField();
    add(interconnectivity);
    add(percentageOfCavesLabel);
    JTextField percentage = new JTextField();
    add(percentage);
    add(numberOfOtyughsLabel);
    JTextField otyughs = new JTextField();
    add(otyughs);

    JButton start = new JButton("Begin Game");
    start.addActionListener((event) -> controller
            .refresh(new Player(new Dungeon(dungeonType.getText(),
            Integer.parseInt(percentage.getText()),
            Integer.parseInt(rows.getText()),
            Integer.parseInt(cols.getText()),
            Integer.parseInt(interconnectivity.getText()),
            Integer.parseInt(otyughs.getText()), new RandomClass()))));
    start.addActionListener((event) -> JOptionPane.getRootFrame().dispose());
    add(start);


  }
}
