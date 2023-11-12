import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;
    public static void main(String[] args) {

        contacts = new ArrayList<>();
        System.out.println("Welcome");
        showInitialOptions();

    }
    private static void showInitialOptions(){
        System.out.println("Please select one : "+
                "\n\t1. Manage Contacts"+
                "\n\t2. Messages"+
                "\n\t3. Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1 :
                manageContacts();
                break;
            case 2 :
                manageMessages();
                break;
            default:
                break;

        }

    }
    private static void manageMessages(){
        System.out.println("Please select one : "+
                "\n\t1. Show all messages"+
                "\n\t2. Send a new message"+
                "\n\t3. Go back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllmessages();
                break;
            case 2:
                sendNewmessage();
                break;
            default:
                showInitialOptions();
                break;

        }

    }
    private static void sendNewmessage(){
        System.out.println("To : ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter name.");
            sendNewmessage();
        }else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("Enter your message : ");
                String text = scanner.next();
                if (text.equals("")){
                    System.out.println("Please enter your message.");
                    sendNewmessage();
                }else {
                    id++;
                    Message message = new Message(text,name,id);
                    for (Contact c: contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(message);
                            Contact currentcontact = c;
                            currentcontact.setMessages(newMessages);
                            contacts.remove(c);
                            contacts.add(currentcontact);
                        }
                    }
                }
            }else {
                System.out.println("There is no such a contact");
            }
        }
        showInitialOptions();

    }
    private static void showAllmessages(){
        ArrayList<Message> allmesages = new ArrayList<>();
        for (Contact c:contacts){
            allmesages.addAll(c.getMessages());

        }
        if (allmesages.size()>0){
            for (Message m:allmesages){
                m.getDetails();
                System.out.println("---------------");
            }
        }else {
            System.out.println("You do not have any message.");
        }
        showInitialOptions();
    }
    private static void manageContacts(){
        System.out.println("Please select one : "+
                "\n\t1. Show all contacts"+
                "\n\t2. Add new contacts"+
                "\n\t3. Search for contacts"+
                "\n\t4. Delete a contact"+
                "\n\t5. Go back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;


        }
    }
    private static void deleteContact(){
        System.out.println("Please enter contact name : ");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            deleteContact();

        }else {
            boolean doesExist = false;
            for (Contact c: contacts){
                doesExist = true;
                contacts.remove(c);

            }
            if (!doesExist){
                System.out.println("There is no such a contact!");
            }
        }
        showInitialOptions();

    }
    private static void searchForContact(){
        System.out.println("Please enter the contact name : ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name");
            searchForContact();
        }else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist){
                System.out.println("There is no such a contact in you phone");

            }
        }
        showInitialOptions();
    }
    private static void addNewContact(){
        System.out.println("Adding a new contact ..."+
                "\nPlease enter contacts name : ");
        String name = scanner.next();
        System.out.println("Please enter contact's number : ");
        String number = scanner.next();
        System.out.println("Please enter the contact's email : ");
        String email = scanner.next();
        if (name == "" || number =="" || email == ""){
            System.out.println("Please enter all of the information");
            addNewContact();
        }else {
            Contact contact = new Contact(name,number,email);
            contacts.add(contact);
        }
        showInitialOptions();
    }
    private static void showAllContacts(){
        for (Contact c:contacts){
            c.getDetails();
        }
        showInitialOptions();
    }
}
