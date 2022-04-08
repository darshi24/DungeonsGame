package controller;


import model.dungeons.Location;
import model.dungeons.PlayerInterface;
import view.Frame;
import view.IFrame;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


/**
 * This class implements the GuiControllerInterface and provides all the functionalities that the
 * game view offers.
 */
public class GuiController implements GuiControllerInterface {

  private PlayerInterface model;
  private IFrame view;

  /**
   * Constructor for the GuiController. It initializes the view and the model required
   * in order to display the changes in the game state.
   *
   * @param model the model that the controller requires in order to reflect game state changes
   */
  public GuiController(PlayerInterface model) {
    this.model = model;
    this.view = new Frame(model, this);
  }

  @Override
  public void playGame(PlayerInterface model) {
    //
  }

  @Override
  public void refresh(PlayerInterface model) {
    this.model = model;
    this.view = view.refresh(model, this);

  }

  @Override
  public void handleCellClickForMove(Component cell) {

    int x = cell.getX();
    int y = cell.getY();
    int cellW = cell.getWidth();
    int cellH = cell.getHeight();

    int offsetX = x % cellW;
    int offsetY = y % cellH;

    int row = (x - offsetX) / cellW;
    int col = (y - offsetY) / cellH;

    Location nextLocation = model.getDungeon().getLocations()[col][row];
    if (model.canPlayerMoveHere(nextLocation)) {
      model.setCurrentLocation(nextLocation);

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

      view.changeColorforVisitedCell(cell, imageIcon);
      view.updateLocationInfoDisplay();
      if (model.isPlayerDead()) {
        view.showAlert("You are Dead");
      }
      if (model.didPlayerWin()) {
        view.showAlert("You reached to the end. You Win !");
      }
    }

  }

  @Override
  public void handleKeyPressForMove(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      model.move("north");
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      model.move("south");
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      model.move("west");
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      model.move("east");
    } else if (e.getKeyCode() == KeyEvent.VK_T) {
      model.pickTreasure();
      view.updateTreasureBagDisplay();
    } else if (e.getKeyCode() == KeyEvent.VK_W) {
      model.pickWeapons();
      view.updateWeaponsBagDisplay();
    }

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

    int newStartLocId = model.getCurrentLocation().getId();
    ImageIcon imageIcon = new ImageIcon(ClassLoader
            .getSystemResource("images/" + fileName + ".png"));
    view.changeColorforVisitedCell(view.getDungeon().getComponent(newStartLocId - 1), imageIcon);
    view.updateLocationInfoDisplay();
    if (model.isPlayerDead()) {
      view.showAlert("You are Dead");
    }
    if (model.didPlayerWin()) {
      view.showAlert("You reached to the end. You Win !");
    }

  }

  @Override
  public void startSameGame() {
    refresh(this.model);
  }


}
