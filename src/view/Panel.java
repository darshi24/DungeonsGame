package view;

import model.dungeons.Dungeon;
import model.dungeons.PlayerInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class that extends JPanel and represents the panel that displays the dungeon layout on the
 * screen.
 */
public class Panel extends JPanel {

  JLabel[][] panelCells;
  private final int dimension = 50;

  /**
   * Constructor for the Panel class. It initializes the model that is required to update the game
   * state and the panel components.
   *
   * @param model the model representing the player
   */
  public Panel(PlayerInterface model) {

    panelCells = new JLabel[model.getDungeon().getRowsDungeon()]
            [model.getDungeon().getColsDungeon()];
    Dungeon d = model.getDungeon();
    int panelRows = d.getRowsDungeon();
    int panelColumns = d.getColsDungeon();
    GridLayout gl = new GridLayout(panelRows, panelColumns);
    this.setLayout(gl);

    int startingLocation = d.getStart().getId();

    int x = startingLocation % panelColumns == 0 ? (startingLocation / panelColumns) - 1 :
            startingLocation / panelColumns;
    int colX = (panelColumns * (x));
    int y = startingLocation - colX - 1;


    String fileName = "";
    if (model.isMovePossible("north") != null) {
      fileName += "N";
    }
    if (model.isMovePossible("south") != null) {
      fileName += "S";
    }
    if (model.isMovePossible("east") != null) {
      fileName += "E";
    }
    if (model.isMovePossible("west") != null) {
      fileName += "W";
    }

    ImageIcon imageIcon = new ImageIcon(ClassLoader
            .getSystemResource("images/" + fileName + ".png"));

    for (int i = 0; i < panelRows; i++) {
      for (int j = 0; j < panelColumns; j++) {
        panelCells[i][j] = new JLabel();


        if (i == x && j == y) {
          panelCells[i][j].setIcon(imageIcon);
        } else {
          panelCells[i][j].setBackground(Color.DARK_GRAY);
        }

        panelCells[i][j].setOpaque(true);
        panelCells[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        panelCells[i][j].setPreferredSize(new Dimension(dimension, dimension));
        this.add(panelCells[i][j]);
        setVisible(true);
      }
    }
    Dimension dims  = new Dimension(dimension * panelRows, dimension * panelColumns);
    this.setSize(dims);
    setVisible(true);

  }
}
