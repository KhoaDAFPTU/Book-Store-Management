/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

/**
 *
 * @author DELL
 */
public class Book {
    private String bookId;
    private String bookName;
    private double bookPrice;
    private int quantity;
    private String publisherId;
    private String status;
    private String namePulisher;

    public Book(String bookId, String bookName, double bookPrice, int quantity, String publisherId, String status, String namePulisher) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.publisherId = publisherId;
        this.status = status;
        this.namePulisher = namePulisher;
    }

    public Book(String bookId) {
        this.bookId = bookId;
    }
    
    @Override
    public boolean equals(Object obj) {
        Book b = (Book) obj;
        return this.bookId.equalsIgnoreCase(b.bookId);
    }

    @Override
    public String toString() {
        return bookId + "," + bookName + "," + bookPrice + "," + quantity + ","
                + publisherId + "," + status + "," + namePulisher;
    }
    
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamePulisher() {
        return namePulisher;
    }

    public void setNamePulisher(String namePulisher) {
        this.namePulisher = namePulisher;
    }
    
    
    
}
