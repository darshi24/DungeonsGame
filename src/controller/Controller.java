package controller;

import model.dungeons.PlayerInterface;

/**
 * This interface represents a text based controller for the game.  It handles user moves by
 * executing them using the model convey move outcomes to the user in some form.
 */
public interface Controller {

  /**
   * A method that executes a single game for the given player.When the game is over, the method
   * ends.
   *
   * @param player the player who will play the game
   */
  void playGame(PlayerInterface player);
}
