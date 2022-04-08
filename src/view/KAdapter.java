package view;

import controller.GuiControllerInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class represents an adapter class for the KeyAdapter. It handles events generated by
 * key presses made by the user during the game.
 */
public class KAdapter extends KeyAdapter {
  GuiControllerInterface listener;

  /**
   * Constructor for the KAdapter class. It initializes the listener that is the controller.
   *
   * @param listener the controller for the gui based game
   */
  public KAdapter(GuiControllerInterface listener) {
    this.listener = listener;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    listener.handleKeyPressForMove(e);
  }

}