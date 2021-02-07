/*
    @author Jerry Huang, Adrian Thamburaj
    Book:
    • This class defines the abstract data type Book, which encapsulates the data fields and methods of a book.
    The book number is a 5-digit serial number automatically generated starting 10001. You CANNOT add
    other data members, EXCEPT static variables or constants.
    • You CANNOT read from console or use System.out in this class. -2 points for each violation.
    • toString() method returns a textual representation of a book in the following format.
    Book#10007::Design Patterns::5/30/1996::is available.
    • equals() method returns true if the serial numbers for the 2 book objects are the same.
    • You CANNOT change the signatures of the toString() and equals() methods. You cannot remove the
    @Override tags. -2 points for each violation. You CAN add constructors, private methods (helper
    methods) and public methods.
*/

public class Book {
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut; //set to true if the book has been checked out
    private Date datePublished;

    public Book(String number, String name, Date datePublished){
        this.number = number;
        this.name = name;
        this.datePublished = datePublished;
    }

    public Book(String name, Date datePublished){
        this("", name, datePublished);
    }

    public Book(String number){
        this(number, "", null);
    }

    @Override
    public boolean equals(Object obj){
        if (instanceof()) //something something instanceof, check notes later
    }

    @Override
    public String toString() {
        //example output
        //Book#10007::Design Patterns::5/30/1996::is available.
        String toReturn = "Book#";
        toReturn += this.number         + "::";
        toReturn += this.name           + "::";
        toReturn += this.datePublished  + "::";
        toReturn += checkedOut ? "is unavailable" : "is available";
        return toReturn;
    }
}