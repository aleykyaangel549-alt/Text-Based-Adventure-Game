package game;

public class Enemy {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int rewardGold;

    // Constructor
    public Enemy(String name, int hp, int attack, int rewardGold) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.rewardGold = rewardGold;
    }

    // Attack Player
    public int attackPlayer() {
        return attack;
    }

    // Take Damage
    public void takeDamage(int damage) {

        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    // Check if Enemy is Alive
    public boolean isAlive() {
        return hp > 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getRewardGold() {
        return rewardGold;
    }

    // Optional Setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setRewardGold(int rewardGold) {
        this.rewardGold = rewardGold;
    }
}