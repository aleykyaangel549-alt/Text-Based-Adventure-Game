package game;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<String> items;

    // Constructor
    public Inventory() {
        items = new ArrayList<>();
    }

    // Add Item
    public void addItem(String item) {
        items.add(item);
    }

    // Remove Item
    public boolean removeItem(String item) {
        return items.remove(item);
    }

    // Check if Item Exists
    public boolean hasItem(String item) {
        return items.contains(item);
    }

    // Get All Items
    public ArrayList<String> getItems() {
        return items;
    }

    // Clear Inventory
    public void clearInventory() {
        items.clear();
    }

    // Number of Items
    public int size() {
        return items.size();
    }

    @Override
    public String toString() {

        if (items.isEmpty()) {
            return "Inventory is empty.";
        }

        StringBuilder sb = new StringBuilder();

        for (String item : items) {
            sb.append("• ").append(item).append("\n");
        }

        return sb.toString();
    }
}