public class BorrowableItem {

    int id;
    float price;
    boolean issued;
    String location;
    String title;
    String type;

    public BorrowableItem() {}

    public BorrowableItem(int id, float price, boolean issued, String location, String title, String type) {
        this.id = id;
        this.price = price;
        this.issued = issued;
        this.location = location;
        this.title = title;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BorrowableItem{" +
                "id=" + id +
                ", price=" + price +
                ", issued=" + issued +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
