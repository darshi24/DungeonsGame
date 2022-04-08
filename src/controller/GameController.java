package controller;

import java.io.IOException;
import java.util.Scanner;

import model.dungeons.Player;
import model.dungeons.PlayerInterface;
import model.dungeons.Smell;

/**
 * This class represents a text based controller for the game that takes in input from the user
 * through the console and outputs the result to the console.
 */
public class GameController implements Controller {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the GameController.
   *
   * @param in the source to read the inputs from
   * @param out the target to print the output to
   */
  public GameController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(PlayerInterface player) {
    try {
      if (!(player instanceof Player)) {
        throw new IllegalArgumentException();
      }
      out.append("Welcome ! Your game has begun. Here are your stats : ").append("\n");
      out.append(player.toString()).append("\n");
      int initWeapons = player.pickWeapons();
      if (initWeapons > 0) {
        out.append(String.format("You found %d bonus weapons !", initWeapons)).append("\n");

      }
      int initTreasures = player.pickTreasure();
      if (initTreasures > 0) {
        out.append(String.format("You found %d bonus treasures !", initTreasures)).append("\n");
      }
      Smell initSmell = player.smell();
      if (initSmell.equals(Smell.WEAK)) {
        out.append("Something smells a little...").append("\n");
      } else if (initSmell.equals(Smell.STRONG)) {
        out.append("Something smells really bad...").append("\n");
      }

      String input = "";
      while (!player.isPlayerDead() && !input.equalsIgnoreCase("3")
              && !player.didPlayerWin()) {
        out.append("\nPress 1 to move\nPress 2 to shoot weapon\nPress 3 to exit game").append("\n");
        input = scan.next();
        try {
          int opt = Integer.parseInt(input);
          switch (opt) {
            case 1:
              out.append("Enter direction to move : ");
              String dir = scan.next();
              try {
                if (isValidInputDirection(dir)) {
                  throw new IllegalArgumentException();
                }
                player.move(dir);
                int weapons = player.pickWeapons();
                if (weapons > 0) {
                  out.append(String.format("You found %d weapons !%n", weapons)).append("\n");
                }
                int treasures = player.pickTreasure();
                if (treasures > 0) {
                  out.append(String.format("You found %d treasures !%n", treasures)).append("\n");
                }
                Smell smell = player.smell();
                if (smell.equals(Smell.WEAK)) {
                  out.append("Something smells a little...").append("\n");
                } else if (smell.equals(Smell.STRONG)) {
                  out.append("Something smells really bad...").append("\n");
                }
                out.append(player.toString()).append("\n");
              } catch (IllegalArgumentException e) {
                out.append("You can only enter one of the following : ")
                        .append("north/south/east/west")
                        .append("\n");
              }
              break;
            case 2:
              if (player.getWeaponsBag().size() == 0) {
                out.append("You are out of weapons !").append("\n");
                break;
              }
              out.append("Enter direction to shoot : ");
              String dirc = scan.next();
              try {
                if (isValidInputDirection(dirc)) {
                  throw new IllegalArgumentException();
                }
                out.append("Enter distance to shoot : ");
                String distStr = scan.next();
                int dist = Integer.parseInt(distStr);
                if (player.shootWeapon(dirc, dist)) {
                  out.append("You hit the monster !").append("\n");
                } else {
                  out.append("Oops! You missed the monster.").append("\n");
                }
                out.append(player.toString());
              } catch (IllegalArgumentException e) {
                if (e instanceof NumberFormatException) {
                  out.append("You need to type a number.").append("\n");
                }
                out.append("You can only enter one of the following : ")
                        .append("north/south/east/west")
                        .append("\n");
              }
              break;
            case 3:
              out.append("Game quit!\n");
              break;
            default:
              out.append("Invalid operation number").append("\n");
              break;
          }

        } catch (NumberFormatException e) {
          out.append("You need to type a number.").append("\n");
        }
      }
      if (player.isPlayerDead()) {
        out.append("Sorry, you're Dead.").append("\n");
      } else if (player.didPlayerWin()) {
        out.append("Congrats ! You win.").append("\n");
      }

    } catch (IOException e) {
      scan.close();
      throw new IllegalStateException("Some error occurred while displaying output.");
    }
    scan.close();
  }

  private boolean isValidInputDirection(String direction) {
    return !direction.equalsIgnoreCase("north")
            && !direction.equalsIgnoreCase("south")
            && !direction.equalsIgnoreCase("east")
            && !direction.equalsIgnoreCase("west");
  }
}
