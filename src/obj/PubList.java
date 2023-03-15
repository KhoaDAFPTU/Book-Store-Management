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
import java.util.StringTokenizer;
import tool.Menu;
import tool.MyUtil;

/*
 *
 * @author DELL
 */
public class PubList extends ArrayList<Publisher> {

    private String fName = "src/file/publisher.dat";

    public PubList() {
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
                String ID = stk.nextToken().trim().toUpperCase();
                String name = stk.nextToken().trim().toUpperCase();
                String phone = stk.nextToken().trim().toUpperCase();
                Publisher p = new Publisher(ID, name, phone);
                this.add(p);
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
            for (Publisher p : this) {
                pw.println(p);
            }
            pw.close();
            System.out.println("Writting file: Done.");
        }
    }

    // Thêm 1 pulisher với data được nhập từ bàn phím
    public void addPulisher() {
        String ID, name, phone; //các biến chứa data được nhập
        int pos;
        while (true) {
            do {
                System.out.println("Data of new pulisher: ");
                ID = MyUtil.readPattern("ID", "P[\\d]{5}");
                pos = this.indexOf(new Publisher(ID));
                if (pos < 0) {
                    break;
                }
                System.out.println("ID is duplicated");
            } while (true);
            name = MyUtil.readPattern("Name", ".{5,30}");
            phone = MyUtil.readPattern("Phone", "[\\d]{10,12}");
            Publisher p = new Publisher(ID, name, phone);
            this.add(p); //thêm p vào danh sách
            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    // tìm 1 pulisher dựa trên ID được nhập
    public void search() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of searched pulisher:");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = this.indexOf(new Publisher(ID));
            if (pos < 0) {
                System.out.println("Not found");
            } else {
                System.out.println("Found:");
                System.out.println("PulisherID: " + this.get(pos).getPublisherId());
                System.out.println("PulisherName: " + this.get(pos).getPublisherName());
                System.out.println("PulisherPhone: " + this.get(pos).getPhone());
            }
            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    //xóa 1 pulisher dựa trên ID được nhập
    public void removePulisher() {
        String ID;  //ID do user nhập
        while (true) {
            System.out.print("ID of removed pulisher:");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = this.indexOf(new Publisher(ID));
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
    public void updatePulisher() {
        String ID;
        String name;
        String phone;

        while (true) {
            System.out.print("ID of updated pulisher:");
            ID = MyUtil.sc.nextLine().trim().toUpperCase();
            int pos = this.indexOf(new Publisher(ID));
            if (pos < 0) {
                System.out.println("Not found");
            } else {
                Publisher p = this.get(pos);
                name = MyUtil.readPattern("Name:", ".{5,30}|");

                if (!name.isEmpty()) {
                    p.setPublisherName(name);
                }

                phone = MyUtil.readPattern("New phone: ", "[\\d]{10,12}|");
                if (!phone.isEmpty()) {
                    p.setPhone(phone);
                }

                System.out.println("Updated");
            }
            if (askUser("Do you want to continue?")) {
                break;
            }
        }
    }

    // Duyệt xuất ds pulisher
    public void printAll() {
        System.out.printf("\n%-10s%-15s%s\n",
                "Id", "Name", "Phone");

        Collections.sort(this, new Comparator<Publisher>() {
            @Override
            public int compare(Publisher p1, Publisher p2) {
                return p1.getPublisherName().compareToIgnoreCase(p2.getPublisherName());
            }
        });
        for (Publisher publisher : this) {
            System.out.printf("%-10s%-15s%s\n", publisher.getPublisherId(),
                    publisher.getPublisherName(), publisher.getPhone());
        }
        System.out.println("");
    }

    // Asker after completing
    public boolean askUser(String title) {
        System.out.println("\n" + title);
        System.out.println("Yes/No\n");
        return !MyUtil.readAskUser();
    }

    public void run() throws Exception {

        //Create menu for pulisher
        Menu menuPulisher = new Menu("Pulishers Management");
        menuPulisher.addOption("Create a Publisher", "Search the Pulisher", "Delete the Publisher",
                "Update the pulisher", "Print the Publisher list from the file",
                "Save the Publishers list to file", "Back");
        boolean change = false;
        while (true) {

            menuPulisher.show();

            switch (menuPulisher.getUser()) {
                case 1: {
                    this.addPulisher();
                    change = true;
                    break;
                }
                case 2: {
                    this.search();
                    break;
                }
                case 3: {
                    this.removePulisher();
                    change = true;
                    break;
                }
                case 4: {
                    this.updatePulisher();
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
