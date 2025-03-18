public class Book extends BorrowableItem {

    String Author;
    String publisher;
    int pageCount;

    public Book() {
    }

    public Book(String Author, String publisher, int pageCount, int id,
                float price, boolean issued, String location, String type, String title) {
        super(id, price, issued, location, type, title);
        this.Author = Author;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book: " +
                ", ID=" + id + '\''+
                "Author='" + Author + '\'' +
                ", Title='" + title + '\'' +
                ", pageCount=" + pageCount + '\''+
                ", publisher=" + publisher + '\''+
                ", price=" + price + '\''+
                ", issued=" + issued + '\''+
                ", location=" + location + '\''+
                ", type=" + type + '\'';
    }

}
