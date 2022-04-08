package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class extends JPanel and represents a panel that pops up to notify when the player has lost
 * or won.
 */
public class AlertPanel extends JPanel {


  /**
   * Constructor for the AlertPanel class. It initializes the panel components.
   */
  public AlertPanel(String m) {

    JLabel message = new JLabel(m);
    JButton ok = new JButton("Ok");

    ok.addActionListener(l -> System.exit(0));
    add(message);
    add(ok);
    setVisible(true);
  }
}
