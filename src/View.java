public class View {

    Inventory inventory;

    public View(){

    }

    public void displayMenu(){
        System.out.println("1. Add Item");
        System.out.println("2. List all items");
        System.out.println("3. Find item by id");
        System.out.println("4. Edit Item");
        System.out.println("5. Get insurance Cost");
        System.out.println("6. Total Cost");
        System.out.println("7. Number of Items in library");
        System.out.println("0. Quit");

    }


    public void displayAddItemMenu(){
        System.out.println("1. Add Book");
        System.out.println("2. Add AVItem");
        System.out.println("3. Add Journal");
    }

    public void printItem(BorrowableItem item){


        System.out.println(item.toString());
    }

    public void getAllItemsOption(){

        System.out.println("Print Options");
        System.out.println("1. Print entire Item");
        System.out.println("2. Print only Title");
    }

    public void printTypeOptions(){

        System.out.println("Choose Item Type");
        System.out.println("1 - Book");
        System.out.println("2 - AVItem");
        System.out.println("3 - Journal");
    }

    public void printFormatOptions(){
        System.out.println("Choose Item Format");
        System.out.println("1 - CD");
        System.out.println("2 - DVD");
        System.out.println("3 - VHS");
        System.out.println("4 - Blu-ray");
    }



}
