package view;

import controller.GuiControllerInterface;
import model.dungeons.PlayerInterface;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * An interface that defines the view functionalities of the GUI based game of dungeons.
 */
public interface IFrame {


  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  void addClickListener(GuiControllerInterface listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  IFrame refresh(PlayerInterface model, GuiControllerInterface listener);

  /**
   * This method makes the cell that the player moves to visible along with the information of the
   * location contents.
   *
   * @param cell the cell to be displayed
   * @param icon the image to be displayed in the cell.
   */
  void changeColorforVisitedCell(Component cell, ImageIcon icon);

  /**
   * This method retrieves the panel that displays the dungeon.
   * @return the panel that displays the dungeon
   */
  public JPanel getDungeon();

  /**
   * This method updates the treasure bag contents that are displayed on the screen every time the
   * player picks the treasure by pressing the 'T' key.
   */
  public void updateTreasureBagDisplay();

  /**
   * This method updates the weapon bag contents that are displayed on the screen every time the
   * player picks the weapons by pressing the 'W' key.
   */
  public void updateWeaponsBagDisplay();

  /**
   * This method updates the information panel that contains information about the location
   * every time the player makes a move through a key press or a mouse click.
   */
  public void updateLocationInfoDisplay();

  /**
   * This method pops up an alert box on the screen when the player is dead or when it wins.
   */
  public void showAlert(String message);

}
