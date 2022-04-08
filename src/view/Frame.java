package view;

import controller.GuiControllerInterface;
import model.dungeons.PlayerInterface;
import model.dungeons.Treasure;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;


/**
 * A class that extends JFrame and implements IFrame. It represents the main window of the GUI-based
 * game of Dungeons. It contains two panels - one where the dungeon is displayed and the other where
 * the player statistics are displayed.
 */
public class Frame extends JFrame implements IFrame {

  private final JPanel dungeon;
  private final JMenuBar menuBar;
  private final JPanel statsPanel;
  private final PlayerInterface model;
  private JLabel currentState;

  /**
   * Constructore for the Frame class. It initializes the model and the other game components that
   * are displayed on the screen.
   *
   * @param model    the model that provides information to be displayed to the view.
   * @param listener the listener for the mouse clicks and key presses made by the user.
   */
  public Frame(PlayerInterface model, GuiControllerInterface listener) {

    super("Jumptastic Games - Dungeons");
    int width = 500;
    int height = 700;
    this.setSize(width, height);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.model = model;

    JSplitPane window = new JSplitPane();
    window.setOrientation(JSplitPane.VERTICAL_SPLIT);
    window.setDividerLocation((this.getSize().height / 2));

    menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");

    JMenu resMenu = new JMenu("Restart");
    JMenuItem newDungeonMenuItem = new JMenuItem("Restart with new Dungeon");
    JMenuItem oldDungeonMenuItem = new JMenuItem("Restart with old Dungeon");
    resMenu.add(newDungeonMenuItem);
    resMenu.add(oldDungeonMenuItem);

    JMenuItem newMenuItem = new JMenuItem("Settings...");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    fileMenu.add(newMenuItem);
    fileMenu.add(resMenu);
    fileMenu.add(exitMenuItem);
    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    dungeon = new Panel(model);
    JScrollPane scrollingPane = new JScrollPane(dungeon);
    window.setTopComponent(scrollingPane);

    statsPanel = new StatsPanel(model);
    window.setBottomComponent(statsPanel);

    this.add(window);
    setVisible(true);
    setResizable(false);

    addClickListener(listener);

  }

  @Override
  public void addClickListener(GuiControllerInterface listener) {

    MAdapter mouseAdpater = new MAdapter(listener);

    for (int i = 0; i < model.getDungeon().getColsDungeon() * model.getDungeon().getRowsDungeon();
         i++) {
      dungeon.getComponent(i).addMouseListener(mouseAdpater);
    }

    KAdapter keyAdpater = new KAdapter(listener);
    dungeon.addKeyListener(keyAdpater);

    dungeon.setFocusable(true);

    Object[] options = {};
    menuBar.getMenu(0).getItem(0).addActionListener(l ->
            JOptionPane.showOptionDialog(null, new SettingsPanel(listener),
                    "Dungeon Settings", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null));

    JMenu temp = (JMenu) menuBar.getMenu(0).getItem(1);
    temp.getItem(0).addActionListener(l -> JOptionPane.showOptionDialog(null,
            new SettingsPanel(listener), "Dungeon Settings", JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE, null, options, null));

    temp.getItem(1).addActionListener(l -> listener.startSameGame());


  }

  @Override
  public IFrame refresh(PlayerInterface model, GuiControllerInterface listener) {
    this.dispose();
    return new Frame(model, listener);
  }

  @Override
  public void changeColorforVisitedCell(Component cell, ImageIcon icon) {
    JLabel label = (JLabel) cell;
    Image image = icon.getImage();
    Image resizedImage = image
            .getScaledInstance(cell.getWidth(), cell.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(resizedImage);
    label.setIcon(imageIcon);
    if (currentState != null) {
      currentState.setBorder(null);
    }
    currentState = label;
    currentState.setBorder(BorderFactory.createLineBorder(Color.RED));

  }

  @Override
  public JPanel getDungeon() {
    return dungeon;
  }

  @Override
  public void updateTreasureBagDisplay() {
    JLabel d = (JLabel) statsPanel.getComponent(1);
    List<Treasure> treasures = model.getTreasureBag();
    int r = 0;
    int di = 0;
    int s = 0;
    for (Treasure t : treasures) {
      if (t.equals(Treasure.RUBY)) {
        r++;
      } else if (t.equals(Treasure.DIAMOND)) {
        di++;
      } else if (t.equals(Treasure.SAPPHIRE)) {
        s++;
      }
    }
    d.setText("Ruby: " + r + ", Diamond: " + di + ", Sapphire: " + s);

  }

  @Override
  public void updateWeaponsBagDisplay() {
    JLabel d = (JLabel) statsPanel.getComponent(3);
    d.setText(Integer.toString(model.getWeaponsBag().size()));

  }

  @Override
  public void updateLocationInfoDisplay() {
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
    JLabel rCountLabel = (JLabel) statsPanel.getComponent(6);
    JLabel sCountLabel = (JLabel) statsPanel.getComponent(8);
    JLabel dCountLabel = (JLabel) statsPanel.getComponent(10);

    rCountLabel.setText(Integer.toString(rCount));
    sCountLabel.setText(Integer.toString(sCount));
    dCountLabel.setText(Integer.toString(dCount));

    JLabel smellInfoLabel = (JLabel) statsPanel.getComponent(12);
    smellInfoLabel.setText(model.smell().name());


    JLabel weaponNumberLabel = (JLabel) statsPanel.getComponent(14);
    weaponNumberLabel.setText(Integer.toString(model.getCurrentLocation().getWeapons().size()));
  }

  @Override
  public void showAlert(String message) {
    Object[] options = {};
    JOptionPane.showOptionDialog(null, new AlertPanel(message),
            "Alert", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, null);
  }

}
