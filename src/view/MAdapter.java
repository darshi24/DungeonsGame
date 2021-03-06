package view;

import controller.GuiControllerInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class represents an adapter class for the MouseAdapter. It handles events generated by
 * mouse clicks made by the user during the game.
 */
public class MAdapter extends MouseAdapter {
  GuiControllerInterface listener;

  /**
   * Constructor for the MAdapter class. It initializes the listener that is the controller.
   *
   * @param listener the controller for the gui based game
   */
  public MAdapter(GuiControllerInterface listener) {
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);

    listener.handleCellClickForMove(e.getComponent());

  }
}
