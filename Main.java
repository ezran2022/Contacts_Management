import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import models.Contact;
import models.ContactManager;

public class Main {

    static ContactManager manager = new ContactManager();

    public static void main(String[] args) {
       try{
        loadContacts("contacts.txt");
        System.out.println( "CONTACTS LOADED \n\n" + manager);
       }catch(FileNotFoundException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
       }
       manageContacts();

    }

    public static void loadContacts(String filename)throws FileNotFoundException {
        
        FileInputStream fis = new FileInputStream(filename);
        Scanner scanFile = new Scanner(fis);

        while(scanFile.hasNextLine()){
         try{   
            Contact contact = new Contact(scanFile.next(), scanFile.next(), scanFile.next());
            manager.addContact(contact);
         }catch(ParseException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
        }
        scanFile.close();
    }

    public static void manageContacts(){
     Scanner input = new Scanner(System.in);
      while(true){
         System.out.println("Would you like to : \n\ta) add another contact\n\tb) remove a contact \n\tc) exit");
         String response = input.nextLine();
         if(response.equalsIgnoreCase("a")){
            System.out.print("\tName: ");
            String name = input.nextLine();
            System.out.print("\tPhone Number: ");
            String phoneNum = input.nextLine();
            System.out.print("\tBirth Date: ");
            String birthdate = input.nextLine();
            if(name.isBlank()|| phoneNum.isBlank() || phoneNum.length() < 5){
               System.out.println("\nThe input you provide is invalid. Registration Failed!!");
            }else{
               try{
                  manager.addContact(new Contact(name, phoneNum, birthdate));
               }catch(ParseException e){
                  System.out.println(e.getMessage());
               }finally{
                  System.out.println("\n\nUPDATED CONTACTS\n\n"+ manager);
               }
            }
         }else if(response.equalsIgnoreCase("b")){
            System.out.println("\nWho would you to remove?");
            manager.removeContact(input.nextLine());
            System.out.println("\n\nUPDATED CONTACTS\n\n"+ manager);
         }else{
            break;
         }
      }
      input.close();
    }
}
