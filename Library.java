/*
    @author Jerry Huang, Adrian Thamburaj
    Library:
    • This is the container class that defines the abstract data type Library to hold library catalog and its
    operations. You CANNOT add other data members, EXCEPT constants. You CANNOT change the
    signatures of the methods listed. -2 points for each violation. You must implement all the methods listed,
    -2 points for each method not implemented or not used. You can add other methods if necessary.
    • You CANNOT use System.out in this class, except the 3 print() methods, -2 points for each violation.
    • The remove() method calls the helper method find() and finds the index of the book to be removed. This
    method maintains the current sequence of books in the array after the removal, -3 points if this is not done.
    • You can use any sorting algorithms for sorting.
*/

public class Library {
    private Book[] books; // array-based implementation of the bag data structure, sorted by serial number
    private int numBooks; // the number of books currently in the bag
    
    private final int minBooks = 4; //minimum number of books 
    
    public Library() { 
        this.numBooks = this.minBooks;
        this.books = new Book[this.minBooks];
    } //default constructor to create an empty bag

    private int find(Book book) { 
        for (int i=0; i<this.books.length; i++){
            if (book.number.equals(this.books[i].number)){
                return i;
            }
        }
        return -1; //-1 represents not found, can be changed later
    } // helper method to find a book in the bag
    
    private void grow() { 
        Book[] tempBooks = new Book[this.books.length + this.minBooks];
        for (int i=0; i<this.books.length; i++){
            tempBooks[i] = this.books[i];
        }
        this.books = tempBooks;    
    } // helper method to grow the capacity by 4
    
    private String generateNewSerial(){ //generates new serial number for new book HELPER METHOD
        for(int i = this.books.length - 1; i > 0; i--){ //should never reach below this.books.length-5 (i.e. 4th element from the end)
            if(this.books[i] != null){
                int tempVal = Integer.parseInt(this.books[i].number) + 1 //return one more than the serial number of the last book
                return String.valueOf(tempVal);
            }
        }
    }

    public void add(Book book) {
        int toAdd = 0;
        book.number = generateNewSerial();
        while (toAdd < this.books.length && this.books[toAdd] != null){
            toAdd++;
        }
        if (toAdd == this.books.length){
            this.grow();
        }
        this.books[toAdd] = book;
    }

    public boolean remove(Book book) { 
        int foundBook = this.find(book);
        if(foundBook == -1){
            //System.out.println("Unable to remove, the library does not have this book."); TODO remove pri
            return false; //presuming return false on unable to remove
        } else{
            String serialNumber = books[foundBook].number;
            this.books[foundBook] = null;
            for(int i = foundBook; i < this.books.length-1; i++){ //moves all books up the array
                this.books[i] = this.books[i+1];
            }
            //System.out.println("Book# " + serialNumber + " removed.");
            return true; //presuming return true on actual removal
        }
    }
    public boolean checkOut(Book book) { 
        int foundBook = this.find(book);
        if (foundBook == -1){
            return false;
        }
        else if (this.books[foundBook].checkedOut){
            return false;
        }

        this.books[foundBook].checkedOut = true;
        return true;  
    } //true if checking out is successful
    
    public boolean returns(Book book) { 
        int foundBook = this.find(book);
        if (foundBook == -1 || !this.books[foundBook].checkedOut){
            return false;
        }
        else if (!this.books[foundBook].checkedOut){
            return false;
        }

        this.books[foundBook].checkedOut = false;
        return true;  
    }

    public void print() {
        int curBook = 0;
        while (curBook < this.books.length && this.books[curBook] != null){
            System.out.println(this.books[curBook].toString());
        }
     } //print the list of books in the bag

    public void printByDate() { } //print the list of books by datePublished (ascending)

    public void printByNumber() { 
        this.print();
    } //print the list of books by number (ascending)
}