public class Journal extends BorrowableItem {

    int issueNumber;
    int pageCount;
    String publisher;
    String subject;

    public Journal(int issueNumber, int pageCount, String publisher, String subject, int id, float price,
                   boolean issued, String location, String type, String title) {
        super(id, price, issued, location, type, title);
        this.issueNumber = issueNumber;
        this.pageCount = pageCount;
        this.publisher = publisher;
        this.subject = subject;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Journal:" +
                "subject='" + subject + '\'' +
                ", issueNumber=" + issueNumber +
                ", pageCount=" + pageCount +
                ", publisher='" + publisher + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", issued=" + issued +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type ;
    }
}
