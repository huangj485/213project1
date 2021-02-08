package src;
/** Library - the container class that defines the abstract data type Library to hold library catalog and its operations.
 * @author Jerry Huang, Adrian Thamburaj
 */
public class Library {
    private Book[] books; // array-based implementation of the catalog data structure, sorted by serial number
    private int numBooks; // the number of books currently in the catalog
    
    private final int minBooks = 4; //minimum number of books 
    private final String firstNumber = "10001";
    
    /** Creates a new Library, with a catalog size of 4 and no books in the catalog. 
     */
    public Library() { 
        this.numBooks = 0;
        this.books = new Book[this.minBooks];
    } //default constructor to create an empty catalog

    /** Checks to see if the specified book is in the library
     * @param book The book to be checked against the catalog.
     * @return An integer - the index of the book in the books array if found, -1 if it is not found.
     */
    private int find(Book book) { 
        for (int i=0; i<this.numBooks; i++){
            if (book.getNumber().equals(this.books[i].getNumber())){
                return i;
            }
        }
        return -1; //-1 represents not found, can be changed later
    }
    
    /** Expands the library by 4 books when a user attempts to add another book and the library is at maximum capacity
     */
    private void grow() { 
        Book[] tempBooks = new Book[this.numBooks + this.minBooks];
        for (int i=0; i<this.numBooks; i++){
            tempBooks[i] = this.books[i];
        }
        this.books = tempBooks;    
    }
    
    /** Helper method that generates new serial numbers for new books
     * @return A String containing the new serial number - the new serial number will always be the number after the last book currently in the library. This means that a new book may get a recently removed serial number, since we cannot track all books that have passed through the library in a consta
     */
    private String generateNewSerial(){ 
        if (this.numBooks == 0){
            return this.firstNumber;
        }
        else {
            int lastBook = this.numBooks-1;
            int tempVal = Integer.parseInt(this.books[lastBook].getNumber()) + 1;
            return String.valueOf(tempVal);
        }
    }

    /** Method to add a book to the library catalog
     * @param book A Book containing the book to be added
     */
    public void add(Book book) {
        int toAdd = 0;
        book.setNumber(generateNewSerial());
        while (toAdd < this.numBooks && this.books[toAdd] != null){
            toAdd++;
        }
        if (toAdd == this.books.length){
            this.grow();
        }
        this.books[toAdd] = book;
        this.numBooks++;
    }

    /** Removes a book from the catalog if it is in the catalog.
     * @param book A Book representing the book to be removed
     * @return A boolean - true if successful, false otherwise
     */
    public boolean remove(Book book) { 
        int foundBook = this.find(book);
        if(foundBook == -1){
            return false; //unable to remove
        } else{
            this.books[foundBook] = null;
            for(int i = foundBook; i < this.numBooks-1; i++){ //moves all books up the array
                this.books[i] = this.books[i+1];
            }
            this.numBooks--;
            return true; //successfully removed
        }
    }

    /** Checks out the specified book if it is in the library
     * @param book The book to be checked out, as a Book
     * @return A boolean - true if successful, false if the book is already checked out or not in the library.
     */
    public boolean checkOut(Book book) { 
        int foundBook = this.find(book);
        if (foundBook == -1){
            return false;
        }
        else if (this.books[foundBook].getCheckedOutStatus()){
            return false;
        }

        this.books[foundBook].setCheckedOutStatus(true);
        return true;  
    } //true if checking out is successful
    
    /** Returns the specified book to the library
     * @param book The book to be returned, as a Book
     * @return A boolean - true if successful, false if the book does not exist or was not checked out.
     */
    public boolean returns(Book book) { 
        int foundBook = this.find(book);
        if (foundBook == -1){
            return false;
        }
        else if (!this.books[foundBook].getCheckedOutStatus()){
            return false;
        }

        this.books[foundBook].setCheckedOutStatus(false);
        return true;  
    }

    /** Prints all the books in the catalog in the order they are listed.
     */
    public void print() {
        if(this.numBooks == 0 ){
            System.out.println("Library catalog is empty!");
        } else{
            System.out.println("**List of books in the library.");
            for (int i=0; i<this.numBooks; i++){
                System.out.println(this.books[i].toString());
            }
            System.out.println("**End of list");
        }
        
     } //print the list of books in the catalog

    /** Prints all the books in the catalog, sorted by ascending date.
     */
    public void printByDate() {
        if (this.numBooks == 0){
            System.out.println("Library catalog is empty!");
        } else{
            System.out.println("**List of books by the dates published");
            int [] sortedIndices = this.sortByDate();
            for(int i = 0; i < this.numBooks; i++){
                System.out.println(this.books[sortedIndices[i]].toString());
            }
            System.out.println("**End of list");
        }
    }

    /** Helper method to sort the catalog by date.
     * @return An array of integers that represent the indices of the books in the books[] array, sorted by oldest first.
     */
    private int[] sortByDate(){
        int [] sorted = new int[this.numBooks]; //returns array of indices from min to max
        Date [] dates = new Date[this.numBooks];
        
        for(int i = 0; i < this.numBooks; i++){
            dates[i] = this.books[i].getDate();
        }
        for(int i = 0; i < this.numBooks; i++){
            sorted[i] = i;
        }
        
        for(int i = 1; i < this.numBooks; i++){
            Date current = dates[i];
            int j = i - 1;
            while(j >= 0 && current.compare(dates[j])){
                dates[j+1] = dates[j];
                sorted[j+1] = sorted[j];
                j--;
            }
            dates[j+1] = current;
            sorted[j+1] = i;
        }
        
        return sorted;
    }

    /** Prints all the books in the catalog, sorted by ascending serial number. 
    */
    public void printByNumber() { 
        //Books are stored by number, so this is the same as printing normally.
        
        if(this.numBooks == 0 ){
            System.out.println("Library catalog is empty!");
        } else{
            System.out.println("**List of books by the book numbers.");
            for (int i=0; i<this.numBooks; i++){
                System.out.println(this.books[i].toString());
            }
            System.out.println("**End of list");
        }
       
    } 
}