package game;

public class Game {

    private Player player;
    private Enemy enemy;

    private String location;
    private String story;

    // Constructor
    public Game() {
        restartGame();
    }

    // ==========================
    // Start / Restart Game
    // ==========================
    public void restartGame() {

        player = new Player("Hero");

        enemy = null;

        location = "Castle";

        story = "🏰 Welcome to the Text Adventure Game!\n\n"
                + "You wake up inside an ancient castle.\n\n"
                + "Your mission is to defeat the Wolf,\n"
                + "collect gold and survive.\n\n"
                + "Click 'Go Forest' to begin your adventure!";
    }

    // ==========================
    // Go Forest
    // ==========================
    public void goForest() {

        location = "Forest";

        if (enemy == null) {
            enemy = new Enemy("Wolf", 50, 10, 50);
        }

        story = "🌲 You entered the Forest.\n\n"
                + "A Wild Wolf Appears!\n\n"
                + "Enemy HP : " + enemy.getHp()
                + "\n\nClick FIGHT to battle!";
    }

    // ==========================
    // Go Village
    // ==========================
    public void goVillage() {

        location = "Village";

        enemy = null;

        story = "🏠 Welcome to the Village.\n\n"
                + "You are safe here.\n\n"
                + "Use a Potion to restore your HP.";
    }

    // ==========================
    // Fight
    // ==========================
    public void fight() {

        if (enemy == null) {

            story = "⚠ There is no enemy here.\n\nGo to the Forest first.";

            return;
        }

        // Player attacks
        enemy.takeDamage(player.attackEnemy());

        if (!enemy.isAlive()) {

            int reward = enemy.getRewardGold();

            player.addGold(reward);
            player.addItem("Potion");

            story = "🎉 Victory!\n\n"
                    + "You defeated the Wolf!\n\n"
                    + "Reward:\n"
                    + "+ " + reward + " Gold\n"
                    + "+ 1 Potion";

            enemy = null;

            return;
        }

        // Enemy attacks
        player.takeDamage(enemy.attackPlayer());

        if (!player.isAlive()) {

            story = "💀 GAME OVER!\n\n"
                    + "The Wolf defeated you.\n\n"
                    + "Click Restart to play again.";

            return;
        }

        story = "⚔ Battle Continues!\n\n"
                + "Wolf HP : " + enemy.getHp() + "/" + enemy.getMaxHp()
                + "\nYour HP : " + player.getHp() + "/" + player.getMaxHp();
    }

    // ==========================
    // Use Potion
    // ==========================
    public void usePotion() {

        if (player.usePotion()) {

            story = "🧪 Potion Used!\n\n"
                    + "HP restored successfully.\n\n"
                    + "Current HP : " + player.getHp();

        } else {

            story = "❌ No Potion available!";
        }
    }

    // ==========================
    // Getters
    // ==========================
    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public String getLocation() {
        return location;
    }

    public String getStory() {
        return story;
    }
}