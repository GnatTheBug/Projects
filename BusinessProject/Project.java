import java.io.*;
import java.util.Scanner;

public class Project {

    /**
     * @param args The first menu, lets user sign in and logs out of the program
     *             when needed.
     * @Author Sam Huff
     * @Author Alec Stough
     */

    public static void main(String[] args) throws IOException {

        // User[] userList = new User[0];

        System.out.println("Welcome to the EHSSW Resource Management App!");
        System.out.println("Please sign in, or create an account to keep track of your resources.");

        boolean isDone = false;
        while (!isDone) {
            boolean catcher = false;
            int actionNum = 0;
            Scanner menu1Scanner = new Scanner(System.in);
            // This scanner is temporary. Intend to replace it with a FXML scene later. The
            // switch statements should still
            // be valid.
            System.out.println("0: Create User \n1: Enter User \n2: Exit Program");
            System.out.println("Please enter menu 1 info here:");

            try {
                actionNum = menu1Scanner.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Wrong input please try again");
                catcher = true;
            }

            if (!catcher) {
                switch (actionNum) {
                    case 0 -> {// Create account

                        // Scan userName
                        System.out.println("Please enter username: ");
                        menu1Scanner.nextLine();
                        String newUserName;
                        newUserName = menu1Scanner.nextLine(); // Creates username for new User
                        Category[] blankList = new Category[0]; // Creates blank list of Category objects for new User
                        User newUser = new User(newUserName, blankList);

                        // Add User to UserList

                        // User[] tempList = new User[userList.length + 1];

                        // Iterates over every item in old list and adds it to the new
                        // for (int i = 0; i < userList.length; i++) {
                        // tempList[i] = userList[i];
                        // }
                        // tempList[userList.length] = newUser;
                        // userList = tempList;

                        // Creates a new file for each user
                        File userFile = new File(newUserName + ".txt");
                        // Creates a filewriter
                        FileWriter userFW = new FileWriter(userFile);
                        // Creates print writer
                        PrintWriter userPW = new PrintWriter(userFW);
                        // prints the users inputted username into the file
                        userPW.println(newUserName);
                        // Closes the print writer
                        userPW.close();
                    }
                    case 1 -> {// Login

                        // enterUserName
                        System.out.println("Please enter username: ");

                        /*
                         * This Scanner is temporary - the fileName should be looked up and retrieved
                         * The file that is retrieved should have all of its content - Categories and
                         * their
                         * items - converted into objects, if possible
                         */

                        String userToSearchFor = menu1Scanner.nextLine(); //
                        userToSearchFor = menu1Scanner.nextLine(); // Enter userName for file

                        // Searches for userFile

                        try {

                            File foundFile = new File(userToSearchFor + ".txt"); // Looks for file
                            User discoveredUser = fileReadingProgram(foundFile); // Creates User object from File
                            System.out.println("File found and read.");
                            userMenu(discoveredUser, foundFile);

                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    case 2 -> {// Exit
                        isDone = true;
                        System.out.println("Thank you for using our program.");

                        // Code that saves the User to a new file, then overwrites old, should go here.

                        System.out.println("A copy of your program has been saved. You may now exit the program.");
                    }
                }
            }
        }
    }

    /**
     * userMenu Function
     *
     * @param currentUser Allows the user to create, manipulate, and destroy
     *                    Category in a single User instance
     * @author Sam Huff
     */
    public static void userMenu(User currentUser, File fileToDelete) throws IOException {

        System.out.println("Welcome, user " + currentUser.getUserName());

        boolean userIsDone = false;

        while (!userIsDone) {
            System.out.println("Number of Categories: " + (currentUser.getUserCategories().length));

            int actionNum = 0;
            Scanner userMenuScanner = new Scanner(System.in);
            // This scanner is temporary. Intend to replace it with a FXML scene later. The
            // switch statements should still
            // be valid.
            System.out.println(
                    "0: New Category \n1: Delete Category \n2: Modify Category \n3: Delete user \n4: Return to main");
            System.out.println("Please enter Category Menu info here:");

            actionNum = userMenuScanner.nextInt();

            switch (actionNum) {
                case 0:// Create category

                    // Instantiates the new item.
                    Item[] blankItemList = new Item[0];
                    Category newCat = new Category(currentUser.getUserName(), currentUser.getUserCategories(), null,
                            blankItemList);
                    System.out.println("Enter category name: ");

                    // Sets the category name
                    userMenuScanner.nextLine();
                    String newCatName = userMenuScanner.nextLine();

                    newCat.setCatName(newCatName);
                    // Adds the new category to the current User
                    currentUser.addNewCategory(newCat);

                    break;

                case 1:// Delete category - works
                    System.out.println("Please enter the name of the category you wish to delete: ");
                    try {
                        userMenuScanner.nextLine();
                        String categoryToDelete = userMenuScanner.nextLine();
                        currentUser.subtractCategory(categoryToDelete);
                    } catch (Exception e) {
                        System.out.println("Sorry, we did not find that category inside " + currentUser.getUserName());
                    }
                    break;

                case 2:// Investigate category

                    try {
                        // Enter name of category
                        System.out.println("Please enter the name of the category you wish to modify: ");
                        userMenuScanner.nextLine();
                        String categoryToModifyName = userMenuScanner.nextLine();

                        // Look for category inside the user
                        Category categoryToModify = null;
                        Category[] categoryList = currentUser.getUserCategories();

                        for (int counter = 0; counter < currentUser.getUserCategories().length; counter++) {
                            if (categoryList[counter].getCatName().equals(categoryToModifyName)) {
                                System.out.println("Found!");
                                categoryToModify = categoryList[counter];
                            }

                        }
                        // Access item menu
                        itemMenu(categoryToModify, currentUser);

                        // Catch if there is no item or if error occurs
                    } catch (Exception e) {
                        System.err.println(e);
                        System.out.println("Sorry, this category is not inside " + currentUser.getUserName());
                    }

                    break;

                case 3:// Delete user
                    System.out.println("Are you sure you want to do this?");
                    Scanner replyScan = new Scanner(System.in);
                    String userReply = replyScan.nextLine();
                    if (userReply.toLowerCase().equals("yes")) {
                        // delete currentUser, using file.delete()
                        fileToDelete.delete(); // DELETE DELETE DELETE
                        currentUser = null;
                        userIsDone = true;
                        break;

                    } else {
                        // User decides not to delete file, returns to menu
                        break;
                    }
                case 4:// Log out of user
                    fileWritingProgram(currentUser);
                    userIsDone = true;
                    break;

            }

        }

    }

    /**
     * itemMenu Function
     *
     * @param currentCategory Allows the user to create, manipulate, and destroy
     *                        Item in a single Category
     * @author Sam Huff
     */
    public static void itemMenu(Category currentCategory, User previousUser) {

        // Finds category name and prints it
        String categoryName = currentCategory.getCatName();
        System.out.println("This is Category: " + categoryName);

        // While user chooses to stay in menu, does not exit
        boolean returnToMenu = false;
        while (returnToMenu == false) {
            System.out.println("Number of Items: " + (currentCategory.getItemList().length));

            // Allows user to choose which option they want
            int actionNum = 0;
            Scanner itemMenuScanner = new Scanner(System.in);
            // This scanner is temporary. Intend to replace it with a FXML scene later. The
            // switch statements should still
            // be valid.
            System.out.println("0: New Item \n1: Delete Item \n2: Modify Item Quantity" +
                    "\n3: Modify Item Name\n4: Modify Item Suffix\n5: Return to category");
            System.out.println("Please enter item Menu info here:");

            actionNum = itemMenuScanner.nextInt();

            // The options:
            switch (actionNum) {
                case 0:// Create item

                    // Creates a blank copy of Item
                    Item newItem = new Item(null, null, null, null, null, null, 0);

                    // Creates a while loop while new Item is incomplete
                    boolean parameterChange = false;
                    while (parameterChange == false) {
                        try {

                            // Try to enter item name using Scanner
                            System.out.println("Enter item name: ");
                            itemMenuScanner.nextLine();
                            String newName = itemMenuScanner.nextLine();
                            newItem.setItemName(newName);

                            // Try to enter item suffix using Scanner
                            System.out.println("Enter item suffix, if desired: ");
                            // itemMenuScanner.nextLine();
                            String newSuffix = itemMenuScanner.nextLine();
                            newItem.setItemSuffix(newSuffix);

                            // Try to enter item suffix using Scanner
                            System.out.println("Enter item quantity: ");
                            // itemMenuScanner.nextLine();
                            Double newQuantity = itemMenuScanner.nextDouble();
                            newItem.setItemQuantityDouble(newQuantity);

                            // If successful, the parameter changes to True
                            currentCategory.addNewItem(newItem);
                            parameterChange = true;

                        } catch (Exception e) {
                            // If exception caught, prints error message and restarts the loop
                            System.err.println(e);
                            System.out.println("Wrong data type. Please try again.");
                        }
                    }
                    // Adds the new item to the current category
                    break;

                case 1:// Delete item

                    // User enters item name, relies on Category's subtractCategory method to
                    // delete.
                    System.out.println("Enter the name of the item you wish to delete: ");
                    itemMenuScanner.nextLine();
                    String itemToDelete = itemMenuScanner.nextLine();
                    currentCategory.subtractItem(itemToDelete);

                    break;

                case 2:// Change item quantity
                    System.out.println("Enter the name of the item you want to change: ");
                    itemMenuScanner.nextLine();
                    String itemToChange = itemMenuScanner.nextLine();
                    try {
                        // Find item and modify quantity
                        // Looks through the list and tries to modify it
                        boolean foundBool = false;
                        Item[] itemList = currentCategory.getItemList();
                        for (int counter = 0; counter < currentCategory.getItemList().length; counter++) {
                            if (itemList[counter].getItemName().equals(itemToChange)) { // use getItemName

                                // If item in list, User enters new item quantity
                                System.out.println("Enter new item quantity: ");
                                itemMenuScanner.nextLine();
                                boolean qualityLoop = false;
                                while (qualityLoop == false) {
                                    try {
                                        System.out.println("Enter new item quantity: "); // deleted old newLine
                                                                                         // character
                                        Double newQuantity = itemMenuScanner.nextDouble();
                                        itemList[counter].setItemQuantityDouble(newQuantity);
                                        qualityLoop = true;
                                    } catch (Exception e) {
                                        System.out.println("Wrong data type. Please enter again: ");

                                    }

                                }
                                foundBool = true;
                            }
                        }

                        if (foundBool == false) {
                            System.out.println("We could not find the item in this category.");
                        }

                    } catch (Exception e) {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:// Change item name
                    System.out.println("Enter the name of the item you want to rename: ");
                    itemMenuScanner.nextLine();
                    String itemToRename = itemMenuScanner.nextLine();
                    try {
                        // Find item and modify name

                        // Looks through the list and tries to modify it
                        boolean foundBool = false;
                        Item[] itemList = currentCategory.getItemList();
                        for (int counter = 0; counter < currentCategory.getItemList().length; counter++) {

                            // If item found
                            if (itemList[counter].getItemName().equals(itemToRename)) {

                                // Lets user enter new name
                                System.out.println("Enter new item name: ");
                                String newName = itemMenuScanner.nextLine();
                                itemList[counter].setItemName(newName);
                                foundBool = true;
                            }
                        }
                        // If item not found, informs user
                        if (!foundBool) {
                            System.out.println("We could not find the item in this category.");
                        }

                    } catch (Exception e) {
                        // Error handler
                        System.err.println(e);
                        System.out.println("Item not found.");
                    }
                    break;

                case 4: // Change item suffix
                    System.out.println("Enter the name of the item whose suffix you want to rename: ");
                    itemMenuScanner.nextLine();
                    String itemWSuffixToRename = itemMenuScanner.nextLine();
                    try {
                        // Find item and modify name

                        // Looks through the list and tries to modify it
                        boolean foundBool = false;
                        Item[] itemList = currentCategory.getItemList();
                        for (int counter = 0; counter < currentCategory.getItemList().length; counter++) {

                            // If item found
                            if (itemList[counter].getItemName().equals(itemWSuffixToRename)) {

                                // Lets user enter new name
                                System.out.println("Enter new item suffix name: ");
                                String newSuffix = itemMenuScanner.nextLine();
                                itemList[counter].setItemSuffix(newSuffix);
                                foundBool = true;
                            }
                        }
                        // If item not found, informs user
                        if (!foundBool) {
                            System.out.println("We could not find the item in this category.");
                        }

                    } catch (Exception e) {
                        // Error handler
                        System.err.println(e);
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:// Return to userMenu
                       // Returns to menu.
                    returnToMenu = true;
                    break;
            }
        }
    }

    /**
     * fileWritingProgram Function
     *
     * @param
     * @return void
     * @author Alec Stough
     * @author Sam Huff
     */

    public static void fileWritingProgram(User closingUser) throws IOException {

        // Creates a new file for each user
        File userFile = new File(closingUser.getUserName() + ".txt");
        // Creates a filewriter
        FileWriter userFW = new FileWriter(userFile);
        // Creates print writer
        PrintWriter userPW = new PrintWriter(userFW);

        // prints the users inputted username into the file
        userPW.println(closingUser.getUserName());
        Category[] tempCatList = closingUser.getUserCategories(); // Temporary category list
        for (int counter = 0; counter < closingUser.getUserCategories().length; counter++) {
            // ^ For each category in User

            userPW.println("CATEGORY" + " " + tempCatList[counter].getCatName()); //
            System.out.println("Category" + tempCatList[counter].getCatName() + "saved.");
            Item[] tempItemList = tempCatList[counter].getItemList(); // Temporary item list

            for (int counter2 = 0; counter2 < tempItemList.length; counter2++) {
                // ^ For each item in the current Category
                userPW.println("ITEMNAME" + " " + tempItemList[counter2].getItemName() + " "
                        + tempItemList[counter2].getItemSuffix() + " "
                        + tempItemList[counter2].getItemQuantityDouble());
                System.out.println("Item" + tempItemList[counter2].getItemName() + "saved.");
            }

        }
        // Closes the print writer
        System.out.println("File has been saved.");
        userPW.close();
    }

    /**
     * fileReadingProgram Function
     *
     * @param oldFile Reads an existing file and creates a modifiable User object
     *                from it
     * @return User
     * @author Sam Huff
     */

    public static User fileReadingProgram(File oldFile) throws FileNotFoundException {
        System.out.println("File reading in progress");
        Scanner fileReader = new Scanner(oldFile); // Creates a file reading Scanner
        String userNameInFile = fileReader.nextLine(); // Finds the file's name from the first line
        Category[] userCategoryList = new Category[0]; // Creates a blank list of categories
        User readUser = new User(userNameInFile, userCategoryList);
        Category newCat = null;

        while (fileReader.hasNextLine()) { // While EOF is not reached
            System.out.println("Line read");
            String eachLine = fileReader.nextLine();
            System.out.println(eachLine);
            String[] eachLineSplit = eachLine.split(" ");

            if (eachLineSplit[0].equals("CATEGORY")) { // If first word equals CATEGORY
                newCat = null; // Reset the current Category
                System.out.println("Category found");

                Item[] blankItemList = new Item[0];
                newCat = new Category(userNameInFile, userCategoryList, eachLineSplit[1], blankItemList);
                readUser.addNewCategory(newCat);
                System.out.println("Category added.");
            }

            else if (eachLineSplit[0].equals("ITEMNAME")) { // If a first word equals ITEMNAME
                System.out.println("Item found");
                try {
                    Item newItem = new Item(userNameInFile, userCategoryList, newCat.getCatName(), newCat.getItemList(),
                            eachLineSplit[1], eachLineSplit[2], Double.valueOf(eachLineSplit[3]));
                    newCat.addNewItem(newItem);
                    System.out.println("Item added");
                } catch (Exception ArrayIndexOutOfBoundsException) {
                    Item newItem = new Item(userNameInFile, userCategoryList, newCat.getCatName(), newCat.getItemList(),
                            "BlankItem", null, 0);
                    System.out.println("Item added, but corruption detected");
                }
                // ^ Adds new Item, with parameters.
            }

            System.out.println();
        }
        System.out.println("User has been created.");
        fileReader.close(); // Need to close user to delete a file if need be
        return readUser;
    }
}