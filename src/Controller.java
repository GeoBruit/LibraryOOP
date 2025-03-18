import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Controller {

    Scanner scanner;
    View view;
    Inventory inventory;
    boolean run;


    public Controller(){
        inventory = new Inventory();
        view = new View();
        scanner = new Scanner(System.in);
        run = true;

    }


    public void run(){
        System.out.println("Welcome to the Library!");

        while (run){
            view.displayMenu();

            String input = getUserInput();
            handleUserInput(input);

        }

    }

    /**
     * Reads user input from the console and returns it as a string.
     * If an exception occurs, notifies the user of the invalid input
     * and recursively re-prompts until valid input is received.
     *
     * @return the user's valid input as a string
     */
    public String getUserInput(){

        String input;

        try{
            input = scanner.nextLine();
            return input;
        } catch (Exception e){

            System.out.println("Input Invalid");
            return getUserInput();
        }
    }


    /**
     * Handles user input and performs the appropriate action based on the given input.
     * This method supports various operations such as listing items, finding items by ID,
     * editing items, calculating costs, and adding new items to the inventory. The method
     * gracefully handles invalid input and any exceptions that occur during operation.
     *
     * @param input the user input string used to determine the operation to execute
     */
    public void handleUserInput(String input){

        try{

            switch (input){
                case "1" -> addItem();
                case "2" -> listAllItems();
                case "3" -> findItemById();
                case "4" -> editItem();
                case "5" -> getInsuranceCost();
                case "6" -> getTotalCost();
                case "7" -> getNumberOfItems();
                case "0" -> {
                    System.out.println("Good Bye");
                    run=false;
                }
                default -> {
                    System.out.println("Invalid input");
                    getUserInput();
                }
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }

    public String getUserInputForItemDetails(){

        try{

            return scanner.nextLine();
        }catch (Exception e){
            System.out.println("Invalid input, Try again!");

            return null;
        }

    }


    //Validators


    /**
     * Validates and retrieves a unique ID from the user.
     * This method prompts the user to input an ID and ensures it is not already present in the inventory.
     * If the input is invalid (e.g., non-numeric) or the ID already exists, the user is re-prompted until
     * a valid and unique ID is entered. The validation process is recursive, retrying as necessary until
     * valid input is provided.
     *
     * @return the validated, unique ID as an integer
     */
    public int validateId(){

        try{
            int id = validateInt("Enter ID for item you want to edit: :)");

            if(inventory.getIdEvidence().contains(id)){

                return id;
            }
            else{
                System.out.println("No item with this ID found, try again!");
                return validateId();
            }

        }catch (Exception e){
            System.out.println("Invalid input, Try again!");
            return validateId();
        }

    }


    /**
     * Validates the 'issued' status of an item by prompting the user for input.
     * The user is asked to specify whether the item is issued or not.
     *
     * The method accepts input as:
     * - "y" for true (item is issued),
     * - "n" for false (item is not issued).
     * If invalid input is provided, the user is notified and re-prompted recursively.
     *
     * @return true if the item is issued, false otherwise
     */
    public boolean validateIssued(){


            System.out.println("Enter Issued (y/n)");
            String issued = getUserInputForItemDetails();

            if(Objects.equals(issued, "y")){

                return true;
            } else if (Objects.equals(issued, "n")) {
                return false;

            }else {
                System.out.println("Invalid input, Try again!");
                return validateIssued();
            }
    }



    public String validateString(String Context){

        try {
            System.out.println(Context);
            return getUserInputForItemDetails();
        }catch (Exception e){
            System.out.println("Invalid input, Try again!");
            return validateString(Context);
        }

    }

    public float validateFloat(String Context){

        try {
            System.out.println(Context);
            return Float.parseFloat(getUserInputForItemDetails());
        }catch (Exception e){
            System.out.println("Invalid input, Try again!");
            return validateFloat(Context);
        }
    }

    public String validateFormat(){

        view.printFormatOptions();
        String format = validateString("");

        switch (format){
            case "1" -> {
                return "CD";
            }
            case "2" -> {
                return "DVD";
            }
            case "3" -> {
                return "VHS";
            }
            case "4" -> {
                return "Blu-ray";
            }
            default -> {
                System.out.println("Format not valid, try again!");
                return validateFormat();
            }
        }

    }

    public BorrowableItem getBorrowableItem(){

        return null;

    }

    public String validateType(){


         view.printTypeOptions();

        String userInput = validateString("");


        String type;
        switch (userInput){
            case "1" -> {
                type = "Book";
            }
            case "2" -> {
                type = "Audio/Video";
            }
            case "3" -> {
                type = "Journal";
            }
            default -> {
                System.out.println("Type not valid, try again!");

                return validateType();

            }
        }
        return type;
    }

    int validateInt(String Context){
        try {
            System.out.println(Context);
            return Integer.parseInt(getUserInputForItemDetails());
        }catch (Exception e){
            System.out.println("Invalid input, Try again!");
            return validateInt(Context);
        }

    }

    public BorrowableItem getGeneralItem(){

        String title = validateString("Enter Title:");
        String type = validateType();
        float price = validateFloat("Enter Price:");
        boolean issued = validateIssued();
        String location = validateString("Enter Location:");


        //we set the initial id to 0
        return new BorrowableItem(0, price, issued, location, title, type);
    }

    public void addBook(){

        BorrowableItem item = getGeneralItem();
        String Author = validateString("Enter Author:");
        String publisher = validateString("Enter Publisher:");
        int pageCount = validateInt("Enter Page Count:");

        inventory.addBook(Author, publisher, pageCount, item.getPrice(), item.isIssued(), item.getLocation(), item.getType(), item.getTitle());
        System.out.println("Book added successfully!");
    }

    public void addAVItem(){
        BorrowableItem item = getGeneralItem();

        int duration = validateInt("Enter Duration:");
        String format = validateFormat();


        inventory.addAVItem(duration, format, item.getPrice(), item.isIssued(), item.getLocation(), item.getType(), item.getTitle());
        System.out.println("AV Item added successfully!");

    }

    public void addJournal(){
        BorrowableItem item = getGeneralItem();

        int issueNumber = Integer.parseInt(validateString("Enter Issue Number:"));
        int pageCount = Integer.parseInt(validateString("Enter Page Count:"));
        String publisher = validateString("Enter Publisher:");
        String subject = validateString("Enter Subject:");

        inventory.addJournal(issueNumber, pageCount, publisher, subject, item.getPrice(), item.isIssued(), item.getLocation(), item.getType(), item.getTitle());
        System.out.println("Journal added successfully!");
    }

    private void addItem() {

        view.displayAddItemMenu();
        String input = getUserInput();

        switch (input){
            case "1" -> addBook();
            case "2" -> addAVItem();
            case "3" -> addJournal();
            default -> System.out.println("Invalid input");
        }
    }

    private void getNumberOfItems() {

        System.out.println("Number of items: " + inventory.getNumItem());
    }

    private void getTotalCost() {

        System.out.println("Total cost: " + inventory.calculateTotalCost());
    }

    private void getInsuranceCost() {

        System.out.println("Total insurance Cost: " + inventory.calculateInsuranceCost());

    }


    /**
     * Allows the user to edit the details of a specific item in the inventory.
     *
     * This method facilitates updating the attributes of an item identified by its unique ID.
     * It first prompts the user to input the ID of the item they wish to edit, validates the input,
     * and retrieves the corresponding item from the inventory. If no matching item is found,
     * the user is notified and prompted again until a valid ID is provided.
     *
     * Once the item is retrieved, the method dynamically modifies its fields, using reflection
     * to identify and update each field based on its type:
     * - For fields of type {@code String}, the user is prompted to input a new string value.
     * - For fields of type {@code int}, the user inputs a new integer value.
     * - For fields of type {@code float}, the user provides a new floating-point value.
     * - A special case exists for {@code boolean} fields, where the method validates the 'issued' status.
     *
     * The method updates fields both in the child class and in the superclass
     * (if the retrieved item is a subclass of {@code BorrowableItem}), while ensuring that certain fields
     * (e.g., the unique {@code id} field) remain unaffected.
     *
     * If an error occurs during the update process (e.g., illegal access to a field), the
     * method catches and logs the exception to assist with debugging.
     */
    private void editItem() {
        System.out.println("Enter ID of item you want to edit: :)");
        int id = validateId();

        BorrowableItem item = inventory.findItemById(id);

        try{
            //first we modify the fields for the child class
            for(Field field : item.getClass().getDeclaredFields()){

                if (field.getType().equals(String.class)){

                    field.set(item, validateString("Enter new " + field.getName() + ":"));

                }else if (field.getType().equals(int.class)){

                    field.set(item, validateInt("Enter new " + field.getName() + ":"));


                } else if (field.getType().equals(float.class)) {

                    field.set(item, validateFloat("Enter new " + field.getName() + ":"));

                }
            }
            //then we change the fields of the superclass (BorrowableItem)
            //this is done because the get declared fields only works for the declared fiedls in the current class
            for (Field field : item.getClass().getSuperclass().getDeclaredFields()){
                if (field.getType().equals(boolean.class)){
                    field.set(item, validateIssued());
                } else if (field.getType().equals(String.class)) {
                    field.set(item, validateString("Enter new " + field.getName() + ":"));
                } else if (field.getType().equals(float.class)) {
                    field.set(item, validateFloat("Enter new " + field.getName() + ":"));
                    //here we we access the `price` field without giving access to the `id`
                } else if (field.getType().equals(int.class) && !field.getName().equals("id")) {
                    field.set(item, validateInt("Enter new " + field.getName() + ":"));
                }
            }

        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

    }

    /**
     * Prompts the user to input an item ID, retrieves the item from the inventory using the provided ID,
     * and displays the item details using the view. If the input is invalid or the ID is not found,
     * the user is notified, and the method recursively re-prompts for a valid ID.
     *
     * This method handles invalid inputs (e.g., non-numeric input) by catching exceptions
     * and providing the user with an opportunity to re-enter the information. If no matching
     * item is found in the inventory, a null value will result in no action when passed to the
     * `view.printItem` method.
     */
    private void findItemById() {

        try{
            int id;

            System.out.println("Enter ID");
            id = Integer.parseInt(getUserInput());

            view.printItem(inventory.findItemById(id));

        } catch (Exception e){
            System.out.println("Invalid input");
            findItemById();
        }



    }

    private void listAllItems() {

        view.printTypeOptions();

        System.out.println("4 To print all items");
        List<BorrowableItem> allItems = inventory.getAllItems();

        String option = getUserInput();

        switch (option){
            case "1" -> {


                for(BorrowableItem item : allItems){
                    if(item instanceof Book){
                        view.printItem(item);
                    }
                }
            }
            case "2" -> {
                for(BorrowableItem item : allItems){
                    if(item instanceof AVItem){
                        view.printItem(item);
                    }
                }
            }

            case "3" -> {
                for(BorrowableItem item : allItems){
                    if(item instanceof Journal){
                        view.printItem(item);
                    }
                }
            }

            case "4" ->{

                for(BorrowableItem item : allItems){
                    view.printItem(item);
                }

            }
            default -> System.out.println("Invalid input");
        }

    }

}
