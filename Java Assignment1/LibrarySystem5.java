interface Issuable {
    void issueItem();
    void returnItem();
}

abstract class LibraryItem {
    protected String title;
    protected String itemId;
    protected boolean isIssued;

    public LibraryItem(String title, String itemId) {
        this.title = title;
        this.itemId = itemId;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }

    public abstract int calculateFine(int daysLate);

    public void displayInfo() {
        System.out.println("ID: " + itemId + ", Title: " + title + ", Issued: " + isIssued);
    }
}

class Book extends LibraryItem implements Issuable {

    public Book(String title, String itemId) {
        super(title, itemId);
    }

    @Override
    public int calculateFine(int daysLate) {
        return daysLate * 5;
    }

    @Override
    public void issueItem() {
        this.isIssued = true;
    }

    @Override
    public void returnItem() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return title + " (Book)";
    }
}

class Magazine extends LibraryItem implements Issuable {

    public Magazine(String title, String itemId) {
        super(title, itemId);
    }

    @Override
    public int calculateFine(int daysLate) {
        return daysLate * 2;
    }

    @Override
    public void issueItem() {
        this.isIssued = true;
    }

    @Override
    public void returnItem() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return title + " (Magazine)";
    }
}

public class LibrarySystem5 {

    public static void main(String[] args) {
        LibraryItem[] items = new LibraryItem[4];

        items[0] = new Book("Java Programming", "B101");
        items[1] = new Magazine("National Geographic", "M101");
        items[2] = new Book("Clean Code", "B102");
        items[3] = new Magazine("Time Magazine", "M102");

        for (LibraryItem item : items) {
            if (item instanceof Issuable) {
                ((Issuable) item).issueItem();
            }
        }

        int[] daysLate = {4, 4, 2, 2};

        for (int i = 0; i < items.length; i++) {
            System.out.println("Item: " + items[i] + " | Fine for " + daysLate[i] + " days late: Rs." + items[i].calculateFine(daysLate[i]));
        }
    }
}