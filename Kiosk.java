/** Kiosk class for processing the command lines from the console.
 * @author Jerry Huang, Adrian Thamburaj
 */

import java.util.Scanner;

public class Kiosk {

    /** A method to run the user interface of the Library.
     */
    public void run() { 
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (line.charAt(0) != 'Q'){
            String[] parsed = line.split(",");
            String command = parsed[0];
            
            if (command.equals("A")){
                //adding to library
                String title = parsed[1]; //Second element after command
                Date date = new Date(parsed[2]); //Third element of line
                Book book = new Book(title, date);
                library.add(book);
            }

            else if (command.equals("R")){  
                //removing from library
                String number = parsed[1];
                Book book = new Book(number);

                if (library.remove(book)){
                    System.out.println("Book#" + number + " removed.");
                }
                else {
                    System.out.println("Unable to remove, the library does not have this book.");
                }
            }

            else if (command.equals("O")){
                //checking out from library
                String number = parsed[1];
                Book book = new Book(number);

                if (library.checkOut(book)){
                    System.out.println("You've checked out Book#" + number + ". Enjoy!");
                }
                else {
                    System.out.println("Book#" + number + " is not available.");
                }
            }

            else if (command.equals("I")){
                //returning to library
                String number = parsed[1];
                Book book = new Book(number);

                if (library.returns(book)){
                    System.out.println("Book#" + number + " return has completed. Thanks!");
                }
                else {
                    System.out.println("Unable to return Book#" + number + ".");
                }
            }
            else if (command.equals("PA")){
                //print list of books
                library.print();
            }
            else if (command.equals("PD")){
                //print list of books by ascending dates
                library.printByDate();
            }
            else if (command.equals("PN")){
                //print list of books by book number, ascending
                library.printByNumber();
            }
            else {
                System.out.println("Invalid command!");
            }

            line = sc.nextLine();
        }

        sc.close();
    }
}   