//package com.driver;
//
// import java.util.*;
//import java.util.Date;
//
//public class Gmail extends Email {
//
//    int inboxCapacity; //maximum number of mails inbox can store
//    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
//    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
//    public Gmail(String emailId, int inboxCapacity) {
//        super(emailId);
//        this.inboxCapacity=inboxCapacity;
//    }
//
//    public void receiveMail(Date date, String sender, String message){
//        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
//        // It is guaranteed that:
//        // 1. Each mail in the inbox is distinct.
//        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
//
//    }
//
//    public void deleteMail(String message){
//        // Each message is distinct
//        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
//
//    }
//
//    public String findLatestMessage(){
//        // If the inbox is empty, return null
//        // Else, return the message of the latest mail present in the inbox
//
//    }
//
//    public String findOldestMessage(){
//        // If the inbox is empty, return null
//        // Else, return the message of the oldest mail present in the inbox
//
//    }
//
//    public int findMailsBetweenDates(Date start, Date end){
//        //find number of mails in the inbox which are received between given dates
//        //It is guaranteed that start date <= end date
//
//    }
//
//    public int getInboxSize(){
//        // Return number of mails in inbox
//
//    }
//
//    public int getTrashSize(){
//        // Return number of mails in Trash
//
//    }
//
//    public void emptyTrash(){
//        // clear all mails in the trash
//
//    }
//
//    public int getInboxCapacity() {
//        // Return the maximum number of mails that can be stored in the inbox
//    }
//}
package com.driver;

import java.util.*;

public class Gmail extends Email {

    private int inboxCapacity;
    private Queue<Mail> inbox; // Using a Queue to simulate inbox (FIFO)
    private Queue<Mail> trash; // Using a Queue to simulate trash (FIFO)

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        Mail newMail = new Mail(date, sender, message);
        if (inbox.size() >= inboxCapacity) {
            trash.add(inbox.poll()); // Move oldest mail in inbox to trash
        }
        inbox.add(newMail); // Add new mail to inbox
    }

    public void deleteMail(String message) {
        inbox.removeIf(mail -> mail.getMessage().equals(message));
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) return null;
        return inbox.peek().getMessage(); // Peek at the latest mail's message
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) return null;
        return ((LinkedList<Mail>) inbox).getLast().getMessage(); // Get the last (oldest) mail's message
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    // Helper class to represent an email
    private static class Mail {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getMessage() {
            return message;
        }
    }
}

