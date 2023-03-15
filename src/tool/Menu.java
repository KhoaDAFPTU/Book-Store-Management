/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Menu {
    private String title = "";
    private ArrayList list = new ArrayList();
    Scanner sc = new Scanner(System.in);

    public Menu() {}
    
    public Menu(String title) {
        this.title = title;
    }
    
    public void setTile(String title) {
        this.title = title;
    }
    
    public void addOption(String... listOption) {
        for(String str : listOption) {
            list.add(str);
        }
    }
    
    public void addOption(String str) {
        list.add(str);
    }
    
    public void reset() {
        list.clear();
        this.title = "";
    }
    
    public void show() {
        System.out.println("====== [" + title + "] ======");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("");
    }
    
    public byte getUser() {
        int choice;
        do {            
            try {
                System.out.print("Your choice is: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= list.size()) {
                    return (byte) choice;
                }
                System.out.println("Your input is error");
            } catch (Exception e) {
                System.out.println("Your input is error");
            }
        } while (true);
    }
    
}
