import java.util.ArrayList;
import java.util.List;

public class Inventory {

     private ArrayList<BorrowableItem> allItems;
     private BorrowableItem currentItem;
     private int currentRecord;
     private ArrayList<Integer> idEvidence;
     //We generate the ID automatically
     int idGenerator;

    public Inventory() {
        allItems = new ArrayList<>();
        currentRecord =0;
        currentItem = null;
        idGenerator = 0;
        idEvidence = new ArrayList<>();

    }

    public void addAVItem(int duration, String format, float price,
                          boolean issued, String location, String type, String title){

        this.idGenerator++;
        int id = idGenerator;

        AVItem avItem = new AVItem(duration, format, id, price, issued, location, type, title);
        allItems.add(avItem);
        idEvidence.add(id);
        currentItem = avItem;

    }

    public void addBook(String Author, String publisher, int pageCount,
                        float price, boolean issued, String location, String type, String title){

        this.idGenerator++;
        int id = idGenerator;

        Book book = new Book(Author, publisher, pageCount, id, price, issued, location, type, title);
        allItems.add(book);
        idEvidence.add(id);
        currentItem = book;
    }

    public void addJournal (int issueNumber, int pageCount, String publisher, String subject, float price,
                            boolean issued, String location, String type, String title){

        this.idGenerator++;
        int id = idGenerator;
        Journal journal = new Journal(issueNumber, pageCount, publisher, subject, id, price, issued, location, type, title);
        allItems.add(journal);
        idEvidence.add(id);
        currentItem = journal;
    }

    public float calculateInsuranceCost() {

        float insuranceCost = 0.0F;

        if (!allItems.isEmpty()) {
            for (BorrowableItem item : allItems) {

                insuranceCost += item.getPrice();

            }

            return insuranceCost * 0.5F;
        }

            return 0.0F;

    }

    public float calculateTotalCost(){

        float totalCost = 0.0F;

        if(!allItems.isEmpty()){
            for(BorrowableItem item : allItems){
                totalCost += item.getPrice();
            }
            return totalCost;
        }

        return 0.0F;
    }

    public BorrowableItem findItemById(int id){

        if(!allItems.isEmpty()){
            for(BorrowableItem item : allItems){
                if(item.getId() == id){
                    return item;
                }
            }
        }
         return null;
    }


    //TODO Check if these are mandatory for the assessment
//    public BorrowableItem getItemByPosition(int position){
//
//        return null;
//    }

    public int getNumItem(){

        return allItems.size();
    }
    //TODO Check if these are mandatory for the assessment

//    public void incrementCurrentRecord(){
//
//        currentRecord++;
//        if(currentRecord < allItems.size()){
//            currentItem = allItems.get(currentRecord);
//        }
//    }
//
//    public void decrementCurrentRecord(){
//        currentRecord--;
//        if(currentRecord >= 0){
//            currentItem = allItems.get(currentRecord);
//        }
//    }

    public BorrowableItem getCurrentItem(){

        return currentItem;
    }

    public void removeItem(int id){
        for(BorrowableItem item : allItems){
            if(item.getId() == id){
                allItems.remove(item);
                break;
            }
        }
    }

    public List<BorrowableItem> getAllItems(){

        return allItems;

    }

    public ArrayList<Integer> getIdEvidence() {
        return idEvidence;
    }

}
