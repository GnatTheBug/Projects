/**
 * Category Class
 * 
 * @author Nathan Whitehead
 *
 */

public class Category extends User {

    // Defining class-specific variables
    private String catName;
    private Item[] itemList;

    /**
     * Constructor for Category
     * 
     * @param userName
     * @param userCategories
     * @param catName
     * @param itemList
     */

    // ***************Not sure if constructor parameters are correct!***************
    public Category(String userName, Category[] userCategories, String catName, Item[] itemList) {
        super(userName, userCategories);
        this.catName = catName;
        this.itemList = itemList;
    }

    // Accessors
    public String getCatName() {
        return catName;
    }

    public Item[] getItemList() {
        return itemList;
    }

    // Mutators
    public void setItemList(Item[] itemList) {
        this.itemList = itemList;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    // mergeSort algorithm with O(n log n) complexity
    public static void mergeSort(Item[] itemList, int length) {
        // Checks if list is of length 1 or less
        if (length < 2) {
            return;
        }

        // Assigns the left and right half of the list to their own new lists
        int middle = length / 2;
        Item[] leftHalf = new Item[middle];
        Item[] rightHalf = new Item[length - middle];

        // Adds the left half of the list elements into the leftHalf list
        for (int i = 0; i < middle; i++) {
            leftHalf[i] = itemList[i];
        }

        // Adds the right half of the list elements into the rightHalf list
        for (int i = 0; i < length; i++) {
            rightHalf[i - middle] = itemList[i];
        }

        // Recursively calls mergeSort with the left and right half lists
        mergeSort(leftHalf, middle);
        mergeSort(rightHalf, middle);

        // Merges the lists together
        merge(itemList, leftHalf, rightHalf, middle, length - middle);

    }

    // Method used to merge the list elements together
    public static void merge(
            Item[] itemList, Item[] leftHalf, Item[] rightHalf, int leftLength, int rightLength) {

        // Initializes the counting variables
        int i = 0;
        int j = 0;
        int k = 0;

        int a = leftHalf[i].getItemName().toLowerCase().compareTo(rightHalf[j].getItemName().toLowerCase());

        //
        while (i < leftLength && j < rightLength) {
            if (a <= 0) {
                itemList[k++] = leftHalf[i++];
            } else {
                itemList[k++] = rightHalf[j++];
            }
        }

        //
        while (i < leftLength) {
            itemList[k++] = leftHalf[i++];
        }
        while (j < rightLength) {
            itemList[k++] = rightHalf[j++];
        }
    }

    // Adds a new item to the item list
    public void addNewItem(Item newItem) {
        // Creates a new list
        System.out.println("Adding Item");
        Item[] tempList = new Item[getItemList().length + 1];

        // Iterates over every item in old list and adds it to the new
        for (int i = 0; i < getItemList().length; i++) {
            tempList[i] = getItemList()[i];
        }
        // Adds the new item at the end of the list, then replaces the old list
        tempList[tempList.length - 1] = newItem;

        // Sorts the current item list using the mergeSort algorithm
        // mergeSort(tempList, tempList.length);
        // NOTE: ^ MergeSort prevents the files from being modded.
        // Sets the current item list to the updated item list
        setItemList(tempList);
    }

    // Subtracts an item from the item list
    public void subtractItem(String subtrahend) {

        // Temporary list
        System.out.println("Subtracting Item");
        Item[] tempList = new Item[getItemList().length - 1];

        // Marks if subtrahend is found in list
        boolean found = false;

        // Iterates through itemList and adds all items that are not the subtrahend to a
        // new list
        for (int i = 0; i < (getItemList().length); i++) {
            if (getItemList()[i].getItemName().equals(subtrahend)) {
                found = true;
            }

            else {
                // Proceed as normal
                if (found == false) {
                    tempList[i] = getItemList()[i];
                }
                // When subtrahend is found, shift tempList index down one to accommodate
                // skipping itemList index.
                else {
                    tempList[i - 1] = getItemList()[i];
                }
            }

        }

        // Sets the current item list to the updated item list
        setItemList(tempList);

    }

}