package game;

import java.util.ArrayList;

public class Player {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int gold;

    private ArrayList<String> inventory;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.maxHp = 100;
        this.hp = maxHp;
        this.attack = 20;
        this.defense = 5;
        this.gold = 0;

        inventory = new ArrayList<>();
        inventory.add("Potion");
    }

    // Attack enemy
    public int attackEnemy() {
        return attack;
    }

    // Take damage
    public void takeDamage(int damage) {

        damage -= defense;

        if (damage < 0) {
            damage = 0;
        }

        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    // Heal
    public void heal(int amount) {

        hp += amount;

        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    // Use potion
    public boolean usePotion() {

        if (inventory.contains("Potion")) {

            inventory.remove("Potion");

            heal(30);

            return true;
        }

        return false;
    }

    // Add item
    public void addItem(String item) {
        inventory.add(item);
    }

    // Add gold
    public void addGold(int amount) {
        gold += amount;
    }

    // Alive?
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

    public int getDefense() {
        return defense;
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }
}