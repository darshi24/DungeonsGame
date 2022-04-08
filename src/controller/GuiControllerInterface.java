package controller;

import model.dungeons.PlayerInterface;

import java.awt.Component;
import java.awt.event.KeyEvent;


/**
 * This interface extends the Controller interface of the Dungeon game. It provides all the
 * functionalities to play the game with graphical user interface.
 */
public interface GuiControllerInterface extends Controller {

  /**
   * This method is used to create a new game with new settings.
   *
   * @param model the new model with new parameters
   */
  public void refresh(PlayerInterface model);

  /**
   * This method handles the player's input when it clicks on the cell that it wants to move to.
   * If the player clicks on a cell that it cannot move to, no difference in output is shown.
   *
   * @param cell the cell that the user clicks
   */
  public void handleCellClickForMove(Component cell);

  /**
   * This method handles various types of key presses that the player makes during the game.
   * The up arrow moves the player north, the down arrow moves the player south, the left arrow
   * moves the player west and the right arrow moves the player east. When player types T, treasure
   * is picked and when the player types W, weapons are picked from the location that the player
   * is currently in.
   *
   * @param e the key event made by the user through the keyboard
   */
  public void handleKeyPressForMove(KeyEvent e);

  /**
   * This method starts a new game for the player view the same settings.
   */
  public void startSameGame();
}
