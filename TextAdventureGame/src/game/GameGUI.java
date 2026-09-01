package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameGUI extends JFrame {

    private Game engine;

    private JLabel imageLabel;

    private JTextArea storyArea;
    private JTextArea inventoryArea;

    private JLabel nameLabel;
    private JLabel hpLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;
    private JLabel goldLabel;
    private JLabel locationLabel;

    private JLabel enemyNameLabel;
    private JLabel enemyHpLabel;

    private JButton forestButton;
    private JButton villageButton;
    private JButton fightButton;
    private JButton potionButton;
    private JButton restartButton;

    public GameGUI() {

        engine = new Game();

        setTitle("Text Adventure Game");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildGUI();
        registerEvents();

        updateScreen();

        setVisible(true);
    }
 // ========================================
 // BUILD GUI
 // ========================================

 private void buildGUI() {

     // ---------- TOP IMAGE ----------

     imageLabel = new JLabel();
     imageLabel.setHorizontalAlignment(JLabel.CENTER);
     imageLabel.setPreferredSize(new Dimension(1000, 250));

     loadImage("castle.png");

     add(imageLabel, BorderLayout.NORTH);

     // ---------- PLAYER PANEL ----------

     JPanel leftPanel = new JPanel();
     leftPanel.setLayout(new GridLayout(6, 1));
     leftPanel.setBorder(BorderFactory.createTitledBorder("Player"));

     nameLabel = new JLabel();
     hpLabel = new JLabel();
     attackLabel = new JLabel();
     defenseLabel = new JLabel();
     goldLabel = new JLabel();
     locationLabel = new JLabel();

     leftPanel.add(nameLabel);
     leftPanel.add(hpLabel);
     leftPanel.add(attackLabel);
     leftPanel.add(defenseLabel);
     leftPanel.add(goldLabel);
     leftPanel.add(locationLabel);

     add(leftPanel, BorderLayout.WEST);

     // ---------- STORY PANEL ----------

     storyArea = new JTextArea();
     storyArea.setEditable(false);
     storyArea.setLineWrap(true);
     storyArea.setWrapStyleWord(true);
     storyArea.setFont(new Font("Serif", Font.PLAIN, 18));

     JScrollPane storyScroll = new JScrollPane(storyArea);

     add(storyScroll, BorderLayout.CENTER);

     // ---------- RIGHT PANEL ----------

     JPanel rightPanel = new JPanel(new BorderLayout());

     JPanel enemyPanel = new JPanel(new GridLayout(2, 1));
     enemyPanel.setBorder(BorderFactory.createTitledBorder("Enemy"));

     enemyNameLabel = new JLabel();
     enemyHpLabel = new JLabel();

     enemyPanel.add(enemyNameLabel);
     enemyPanel.add(enemyHpLabel);

     inventoryArea = new JTextArea();
     inventoryArea.setEditable(false);

     JScrollPane inventoryScroll = new JScrollPane(inventoryArea);
     inventoryScroll.setBorder(
             BorderFactory.createTitledBorder("Inventory"));

     rightPanel.add(enemyPanel, BorderLayout.NORTH);
     rightPanel.add(inventoryScroll, BorderLayout.CENTER);

     add(rightPanel, BorderLayout.EAST);

     // ---------- BUTTON PANEL ----------

     JPanel buttonPanel = new JPanel(new FlowLayout());

     forestButton = new JButton("Go Forest");
     villageButton = new JButton("Go Village");
     fightButton = new JButton("Fight");
     potionButton = new JButton("Use Potion");
     restartButton = new JButton("Restart");

     buttonPanel.add(forestButton);
     buttonPanel.add(villageButton);
     buttonPanel.add(fightButton);
     buttonPanel.add(potionButton);
     buttonPanel.add(restartButton);

     add(buttonPanel, BorderLayout.SOUTH);
 }


 // ========================================
 // LOAD IMAGE
 // ========================================

 private void loadImage(String imageName) {

     URL url = getClass().getResource("/assets/" + imageName);

     if (url != null) {

         imageLabel.setText("");
         imageLabel.setIcon(new ImageIcon(url));

     } else {

         imageLabel.setIcon(null);
         imageLabel.setText("Image not found: " + imageName);

     }
 }
//========================================
//REGISTER BUTTON EVENTS
//========================================

private void registerEvents() {

  // Go to Forest
  forestButton.addActionListener(e -> {

      engine.goForest();

      loadImage("forest.jpg");

      updateScreen();

  });

  // Go to Village
  villageButton.addActionListener(e -> {

      engine.goVillage();

      loadImage("village.jpg");

      updateScreen();

  });

  // Fight
  fightButton.addActionListener(e -> {

      engine.fight();

      if (engine.getEnemy() == null) {

          loadImage("castle.png");

      } else {

          loadImage("forest.jpg");

      }

      updateScreen();

      checkGameStatus();

  });

  // Use Potion
  potionButton.addActionListener(e -> {

      engine.usePotion();

      updateScreen();

  });

  // Restart Game
  restartButton.addActionListener(e -> {

      engine.restartGame();

      loadImage("castle.png");

      updateScreen();

  });

}


//========================================
//GAME STATUS
//========================================

private void checkGameStatus() {

  Player player = engine.getPlayer();

  if (!player.isAlive()) {

      JOptionPane.showMessageDialog(
              this,
              "💀 GAME OVER!\n\nClick Restart to play again.",
              "Game Over",
              JOptionPane.ERROR_MESSAGE);

      return;
  }

  if (engine.getEnemy() == null &&
          engine.getLocation().equals("Forest")) {

      JOptionPane.showMessageDialog(
              this,
              "🎉 Congratulations!\n\nYou defeated the Wolf!\n\nReward:\n+50 Gold\n+1 Potion",
              "Victory",
              JOptionPane.INFORMATION_MESSAGE);

  }

}
//========================================
//UPDATE SCREEN
//========================================

private void updateScreen() {

 Player player = engine.getPlayer();

 // Player Information
 nameLabel.setText("Name : " + player.getName());
 hpLabel.setText("HP : " + player.getHp() + " / " + player.getMaxHp());
 attackLabel.setText("Attack : " + player.getAttack());
 defenseLabel.setText("Defense : " + player.getDefense());
 goldLabel.setText("Gold : " + player.getGold());
 locationLabel.setText("Location : " + engine.getLocation());

 // Story
 storyArea.setText(engine.getStory());

 // Inventory
 StringBuilder inventory = new StringBuilder();

 if (player.getInventory().isEmpty()) {

     inventory.append("No Items");

 } else {

     for (String item : player.getInventory()) {

         inventory.append("• ").append(item).append("\n");

     }

 }

 inventoryArea.setText(inventory.toString());

 // Enemy Information
 if (engine.getEnemy() == null) {

     enemyNameLabel.setText("Enemy : None");
     enemyHpLabel.setText("HP : -");

 } else {

     enemyNameLabel.setText("Enemy : " + engine.getEnemy().getName());

     enemyHpLabel.setText(
             "HP : "
                     + engine.getEnemy().getHp()
                     + " / "
                     + engine.getEnemy().getMaxHp());

 }

 repaint();
}


//========================================
//MAIN METHOD
//========================================

public static void main(String[] args) {

 SwingUtilities.invokeLater(() -> new GameGUI());

}

}