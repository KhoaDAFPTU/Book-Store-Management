/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import obj.BookList;
import obj.PubList;
import obj.Publisher;
import tool.Menu;

/**
 *
 * @author DELL
 */
public class BookStoreManagement {

    /**
     * @param args the command line arguments
     */
    public static PubList pubList = new PubList();
    public static BookList bookList = new BookList();

    public static void main(String[] args) throws Exception {
        // TODO code application logic here     

        Menu menu = new Menu("Book Store Management");
        menu.addOption("Pulishers management", "Book management", "Exit");

        while (true) {

            menu.show();

            switch (menu.getUser()) {
                case 1: {
                    pubList.run();
                    break;
                }
                case 2: {
                    bookList.run();
                    break;
                }
                case 3: {
                    System.exit(0);
                }
            }

        }
    }
}
