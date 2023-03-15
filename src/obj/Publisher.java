/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Publisher {
    private String publisherId; //format Pxxxxx, xxxxx is five digit and is not allowed to duplicate
    private String publisherName;   //5..30 chars
    private String phone;   //10..12 chars

    public Publisher(String publisherId) {
        this.publisherId = publisherId;
    }
    
    public Publisher(String publisherId, String publisherName, String phone) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.phone = phone;
    }

    // constructor phục vụ hức năng tìm kiếm

    @Override
    public boolean equals(Object obj) {
        Publisher p = (Publisher) obj;
        return this.publisherId.equalsIgnoreCase(p.publisherId);
    }

    @Override
    public String toString() {
        return publisherId + "," + publisherName + "," + phone;
    }
    
    // Getter and Setter
    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
