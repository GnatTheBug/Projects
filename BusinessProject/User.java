/**
 * User Class
 * 
 * @author Nathan Whitehead
 *
 */
public class User {

    private String userName;
    private Category[] userCategories;

    public User(String userName, Category[] userCategories) {

        this.userName = userName;

        this.userCategories = userCategories;
    }

    // Accessors
    public String getUserName() {
        return userName;
    }

    public Category[] getUserCategories() {
        return userCategories;
    }

    // Mutators
    public void setItemList(Category[] userCategories) {
        this.userCategories = userCategories;
    }

    public void setCatName(String userName) {
        this.userName = userName;
    }

    // mergeSort algorithm with O(n log n) complexity
    public static void mergeSort(Category[] userCategories, int length) {
        // Checks if list is of length 1 or less
        if (length < 2) {
            return;
        }

        // Assigns the left and right half of the list to their own new lists
        int middle = length / 2;
        Category[] leftHalf = new Category[middle];
        Category[] rightHalf = new Category[length - middle];

        // Adds the left half of the list elements into the leftHalf list
        for (int i = 0; i < middle; i++) {
            leftHalf[i] = userCategories[i];
        }

        // Adds the right half of the list elements into the rightHalf list
        for (int i = 0; i < length; i++) {
            rightHalf[i - middle] = userCategories[i];
        }

        // Recursively calls mergeSort with the left and right half lists
        mergeSort(leftHalf, middle);
        mergeSort(rightHalf, middle);

        // Merges the lists together
        merge(userCategories, leftHalf, rightHalf, middle, length - middle);

    }

    // Method used to merge the list elements together
    public static void merge(
            Category[] userCategories, Category[] leftHalf, Category[] rightHalf, int leftLength, int rightLength) {

        // Initializes the counting variables
        int i = 0;
        int j = 0;
        int k = 0;

        int a = leftHalf[i].getCatName().toLowerCase().compareTo(rightHalf[j].getCatName().toLowerCase());

        //
        while (i < leftLength && j < rightLength) {
            if (a <= 0) {
                userCategories[k++] = leftHalf[i++];
            } else {
                userCategories[k++] = rightHalf[j++];
            }
        }

        //
        while (i < leftLength) {
            userCategories[k++] = leftHalf[i++];
        }
        while (j < rightLength) {
            userCategories[k++] = rightHalf[j++];
        }
    }

    // Adds a new item to the item list
    public void addNewCategory(Category newCat) {
        // Creates a new list
        Category[] tempList = new Category[getUserCategories().length + 1];

        // Iterates over every item in old list and adds it to the new
        for (int i = 0; i < getUserCategories().length; i++) {
            tempList[i] = getUserCategories()[i];
        }
        // Adds the new item at the end of the list, then replaces the old list
        tempList[tempList.length - 1] = newCat;

        // Sorts the current item list using the mergeSort algorithm
        // mergeSort(tempList, tempList.length);

        // Sets the current item list to the updated item list
        setItemList(tempList);
    }

    // Subtracts an item from the item list
    public void subtractCategory(String subtrahend) {

        // Temporary list
        Category[] tempList = new Category[getUserCategories().length - 1];

        // Marks if subtrahend is found in list
        boolean found = false;

        // Iterates through itemList and adds all items that are not the subtrahend to a
        // new list
        for (int i = 0; i < getUserCategories().length; i++) {
            if (getUserCategories()[i].getCatName().equals(subtrahend)) {
                found = true;
            }

            else {
                // Proceed as normal
                if (found == false) {
                    tempList[i] = getUserCategories()[i];
                }
                // When subtrahend is found, shift tempList index down one to accommodate
                // skipping itemList index.
                else {
                    tempList[i - 1] = getUserCategories()[i];
                }
            }

        }

        // Sets the current item list to the updated item list
        setItemList(tempList);

    }
}
