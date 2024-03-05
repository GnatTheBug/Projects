public class Item extends Category {

    String itemName;
    String itemSuffix;
    int itemQuantityInt;
    double itemQuantityDouble;

    public Item(String userName, Category[] userCategories, String categoryName, Item[] itemList, String itemName,
            String itemSuffix, int itemQuantityInt, double itemQuantityDouble) {
        super(userName, userCategories, categoryName, itemList);
        this.itemName = itemName;
        this.itemSuffix = itemSuffix;
        this.itemQuantityInt = itemQuantityInt;
        this.itemQuantityDouble = itemQuantityDouble;

    }

    // Accessors
    public String getItemName() {
        return itemName;
    }

    public String getItemSuffix() {
        return itemSuffix;
    }

    public int getItemQuantityInt() {
        return itemQuantityInt;
    }

    public double getItemQuantityDouble() {
        return itemQuantityDouble;
    }

    // Mutators

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemSuffix(String itemSuffix) {
        this.itemSuffix = itemSuffix;
    }

    public void setItemQuantityInt(int itemQuantityInt) {
        this.itemQuantityInt = itemQuantityInt;
    }

    public void setItemQuantityDouble(double itemQuantityDouble) {
        this.itemQuantityDouble = itemQuantityDouble;
    }
}