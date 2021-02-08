package src;
/** Represents a book in the library. This class defines the abstract data type Book, which encapsulates the data fields and methods of a book. 
 * @author Jerry Huang, Adrian Thamburaj
*/
public class Book {
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut; //set to true if the book has been checked out
    private Date datePublished;

    /** Creates a book with the specified number, name, and publishing date
     * @param number A String representing serial number of the book
     * @param name A String representing the name of the book
     * @param datePublished A Date object of the date the book was published.
     */
    public Book(String number, String name, Date datePublished){
        this.number = number;
        this.name = name;
        this.datePublished = datePublished;
        this.checkedOut = false;
    }

    /** Creates a book with specified name and date and a blank number.
     * @param name A String representing the name of the book.
     * @param datePublished A Date object of the date the book was published.
     */
    public Book(String name, Date datePublished){
        this("", name, datePublished);
    }

    /** Creates a book with specified number and blank name and date.
     * @param number the serial number of the book.
     */
    public Book(String number){
        this(number, "", null);
    }

    /** Default Book constructor - creates an empty Book
     */
    public Book(){
        this("", "", null);
    }

    /** Compares an object to the book to check for equality.
     * @param obj an object to compare to the book.
     * @return true if the serial number of the input obj matches the book's number.
     */
    @Override
    public boolean equals(Object obj){
    //equals() method returns true if the serial numbers for the 2 book objects are the same.
        if (obj instanceof Book){
            Book book = (Book) obj;
            return book.getNumber().equals(this.number);
        }
        return false;
    }

    /** Represents a Book object and its attributes as a string.
     * @return A string representation of a Book.
     */
    @Override
    public String toString() {
    /*toString() method returns a textual representation of a book in the following format.
    Book#10007::Design Patterns::5/30/1996::is available.
    */
        String toReturn = "Book#";
        toReturn += this.number         + "::";
        toReturn += this.name           + "::";
        toReturn += this.datePublished.toString()  + "::";
        toReturn += checkedOut ? "is unavailable" : "is available";
        return toReturn;
    }

    //GETTERS AND SETTERS
    /** Gets the book's serial number.
     * @return A string representing the book's serial number.
     */
    public String getNumber(){
        return this.number;
    }

    /** Sets the book's serial number.
     * @param number A string to represent the serial number of a book.
     */
    public void setNumber(String number){
        this.number = number;
    }

    /** Gets the checked out status of the book.
     * @return A boolean - true if checked out, false if not.
     */
    public boolean getCheckedOutStatus(){
        return this.checkedOut;
    }

    /** Sets the checked out status of the book.
     * @param checkedOut The new checked out status of the book.
     */
    public void setCheckedOutStatus(boolean checkedOut){
        this.checkedOut = checkedOut;
    }
    
    /** Gets the date a book was published.
     * @return The date the book was published as a Date.
     */
    public Date getDate(){
        return this.datePublished;
    }
}