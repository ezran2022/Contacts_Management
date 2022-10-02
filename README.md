# Contacts_Management
## This is a Java Desktop Application which is used to manage the contacts info by adding some  in store and removing them

As we see this App start by loading all information in store and give you 3 posssible choices 

![image](https://user-images.githubusercontent.com/103323625/193466342-c39e3746-aa04-4636-b4a7-1192ef5959d6.png)

And by choice i choose 'a' means add new contacts and it give me how to pass all needed info to fullfill registration

![image](https://user-images.githubusercontent.com/103323625/193466399-1c6c7dd1-3c74-4ab5-88aa-45a2ca9cdb00.png)

After that it load an updated list of contacts including the one you pass there and as we see the pass 3 information by we
get 4 info including the age ,just after getting your birth date we can easy calculate  your current year old with java special library in java 

![image](https://user-images.githubusercontent.com/103323625/193466503-bb51b69f-97de-4f9c-b839-6cb1eb37f54a.png)

another think to know about this task of adding is that our system can not tolerate a blank or 
 a phone number less than 5 character because it is not exist ,so we give you a message of alert without crashing an app
 
 ![image](https://user-images.githubusercontent.com/103323625/193466683-c9a42450-5e9f-4bc4-abfe-002d0de237ab.png)

Don't accept phone number less than 5 character

 ![image](https://user-images.githubusercontent.com/103323625/193466755-ce4558f3-269c-49e9-9789-3f5a447566c0.png)

It can remove a contact which have to be removed 

![image](https://user-images.githubusercontent.com/103323625/193466821-bffc3f71-4923-4182-a753-77241a149763.png)

### This is how this Desktop application work!!!!!!!


### Code

```java
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


```
