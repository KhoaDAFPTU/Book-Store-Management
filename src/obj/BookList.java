/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import static management.BookStoreManagement.pubList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import tool.Menu;
import tool.MyUtil;

/**
 *
 * @author DELL
 */
public class BookList extends ArrayList<Book> {

    Scanner sc = new Scanner(System.in);
    private String fName = "src/file/book.dat";

    public BookList() {
        readFromFile();
    }

    // đọc file ra danh sách pulish
    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f); //giúp đọc 1 ký tự
            BufferedReader bf = new BufferedReader(fr); //đọc 1 dòng
            String line; //biến chứa 1 dòng text
            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String bookID = stk.nextToken().trim().toUpperCase();
                String name = stk.nextToken().trim().toUpperCase();
                double price = Double.parseDouble(stk.nextToken().trim().toUpperCase());
                int quantity = Integer.parseInt(stk.nextToken().trim().toUpperCase());
                String pulisherID = stk.nextToken().trim().toUpperCase();
                String status = stk.nextToken().trim().toUpperCase();
                String namePulisher = stk.nextToken().trim().toUpperCase();
                this.add(new Book(bookID, name, price, quantity, pulisherID, status, namePulisher));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //ghi danh sách lên file
    public void writeToFile() throws Exception {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = new PrintWriter(fName);
            for (Book b : this) {
                pw.println(b);
            }
            pw.close();
            System.out.println("Writting file: Done.");
        }
    }

    // Thêm 1 pulisher với data được nhập từ bàn phím
    public void addBook() {
        String bookID, name, pulisherID, status, namePulisher; //các biến chứa data được nhập
        double price;
        int quantity;

        while (true) {
            System.out.println("Data of new book: ");
            int pos;
            do {
                bookID = MyUtil.readPattern("ID", "B[\\d]{5}");
                pos = this.indexOf(new Book(bookID));
                if (pos < 0) {
                    break;
                }
                System.out.println("ID is duplicated");
            } while (true);

            name = MyUtil.readPattern("Name", ".{5,30}");

            price = MyUtil.readDouble("Price", 0);

            quantity = MyUtil.readInt("Quantity", 0);

            if (quantity > 0) {
                status = "AVAILABLE";
            } else {
                status = "NOT AVAILABLE";
            }

            do {
                System.out.print("PubliserID: ");
                pulisherID = sc.nextLine().trim().toUpperCase();
                pos = pubList.indexOf(new Publisher(pulisherID));
                if (pos != -1) {
                    namePulisher = pubList.get(pos).getPublisherName();
                    break;
                }
                System.out.println("Book not found");
            } while (true);

            this.add(new Book(bookID, name, price, quantity, pulisherID, status, namePulisher)); //thêm p vào danh sách

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    // tìm 1 book dựa trên ID được nhập
    public void search() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of book or pulisher: ");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getBookId().equals(ID) || this.get(i).getPublisherId().equals(ID)) {
                    System.out.println("======== ========");
                    System.out.println("Found index of book " + i + ":");
                    System.out.println("BookID: " + this.get(i).getBookId());
                    System.out.println("BookName: " + this.get(i).getBookName());
                    System.out.println("BookPrice: " + this.get(i).getBookPrice());
                    System.out.println("Quantity: " + this.get(i).getQuantity());
                    System.out.println("PulisherID: " + this.get(i).getPublisherId());
                    System.out.println("Status: " + this.get(i).getStatus());
                }
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    //xóa 1 pulisher dựa trên ID được nhập
    public void removeBook() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of removed book: ");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = this.indexOf(new Book(ID));
            if (pos < 0) {
                System.out.println("Not found");
            } else {
                this.remove(pos);
                System.out.println("Removed");
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    //sửa name, phone 1 pulisher dựa trên ID được nhập
    public void updateBook() {
        String bookID, name, pulisherID, status; //các biến chứa data được nhập
        double price;
        int quantity;

        while (true) {
            System.out.print("ID of updated book: ");
            bookID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = this.indexOf(new Book(bookID));
            if (pos < 0) {
                System.out.println("Not found");
            } else {
                name = MyUtil.readPattern("Name", ".{5,30}|");
                if (!name.isEmpty()) {
                    this.get(pos).setBookName(name);
                }

                price = MyUtil.readDouble("Price", 0);
                if (price != -1) {
                    this.get(pos).setBookPrice(price);
                }

                quantity = MyUtil.readInt("Quantity", 0);
                if (quantity != -1) {
                    this.get(pos).setQuantity(quantity);
                }

                if (this.get(pos).getQuantity() > 0) {
                    this.get(pos).setStatus("AVAILABLE");
                } else {
                    this.get(pos).setStatus("NOT AVAILABLE");
                }

                do {
                    System.out.print("PubliserID: ");
                    pulisherID = sc.nextLine().trim().toUpperCase();
                    pos = pubList.indexOf(new Publisher(pulisherID));
                    if (pos != -1) {
                        this.get(pos).setPublisherId(pulisherID);
                        this.get(pos).setNamePulisher(pubList.get(pos).getPublisherName());
                        break;
                    } else if (pulisherID.isEmpty()) {
                        break;
                    }
                    System.out.println("Book not found");
                } while (true);

                System.out.println("Updated");
            }

            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    // Asker after completing
    public boolean askUser(String title) {
        System.out.println("\n" + title);
        System.out.println("Yes/No\n");
        return !MyUtil.readAskUser();
    }

    public void sortByQuantityAndPrice() {
        Collections.sort(this, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                int result = Integer.compare(b2.getQuantity(), b1.getQuantity());
                if (result == 0) {
                    result = Double.compare(b1.getBookPrice(), b2.getBookPrice());
                }
                return result;
            }
        });
    }
    public void printAll() {
        System.out.printf("\n%-10s%-15s%-13s%-10s%-12s%-20s%s\n",
                "Id", "Name", "Price", "Quantity", "Subtotal", "PublisherName", "Status");
       this.sortByQuantityAndPrice();
        for (Book book : this) {
            System.out.printf("%-10s%-15s%-13s%-10s%-12s%-20s%s\n", book.getBookId(), book.getBookName(),
                    book.getBookPrice(), book.getQuantity(), book.getBookPrice() * book.getQuantity(),
                    book.getNamePulisher(), book.getStatus());
        }
        System.out.println("");
    }

    public void run() throws Exception {
        //Create menu for Book
        Menu menuBook = new Menu("Books Management");
        menuBook.addOption("Create a Book", "Search the Book", "Delete the Book",
                "Update the Book", "Print the Book list from the file",
                "Save the Book list to file", "Back");
        boolean change = false;
        while (true) {

            menuBook.show();

            switch (menuBook.getUser()) {
                case 1: {
                    this.addBook();
                    change = true;
                    break;
                }
                case 2: {
                    this.search();
                    break;
                }
                case 3: {
                    this.removeBook();
                    change = true;
                    break;
                }
                case 4: {
                    this.updateBook();
                    change = true;
                    break;
                }
                case 5: {
                    this.printAll();
                    break;
                }
                case 6: {
                    this.writeToFile();
                    change = false;
                    break;
                }
                case 7: {
                    if (change) {
                        System.out.println("You do not save data to file."
                                + "It can make lose some data.");
                        if (!askUser("Do you want to save before back?")) {
                            this.writeToFile();
                        }
                    }
                    return;
                }
            }
        }
    }

}
