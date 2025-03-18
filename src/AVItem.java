public class AVItem extends BorrowableItem{

    int duration;
    String format;

    public AVItem(int duration, String format, int id, float price, boolean issued, String location, String type, String title) {
        super(id, price, issued, location, type, title);
        this.duration = duration;
        this.format = format;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "AVItem:" +
                "duration=" + duration +
                ", format='" + format + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", issued=" + issued + '\'' +
                ", price=" + price + '\'' +
                ", id=" + id ;
    }
}
