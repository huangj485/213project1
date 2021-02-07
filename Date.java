/*
    @author Jerry Huang, Adrian Thamburaj
    Date:
    • This class defines the properties of a Date object. You CANNOT add other data members to this class,
    and you CANNOT use System.out in this class. -2 points for each violation.
    • The isValid() method checks if a date is valid.
        o For a date with the year less than 1900 or a date beyond today’s date is invalid.
        o For the month, January, March, May, July, August, October and December, each has 31 days; April,
        June, September and November, each has 30 days; February has 28 days in a normal year, and 29 days
        in a leap year. To determine whether a year is a leap year, follow these steps:
            Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
            Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
            Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
            Step 4. The year is a leap year.
            Step 5. The year is not a leap year.
        o You MUST design the test cases to thoroughly test the isValid() method. You must write a testbed
        main and implement the test cases. You must follow the instructions in the “Test Specification” section
        of the Software Development Ground Rules. You will lose 10 points if you do not submit the test
        document, or not follow the format. In the testbed main, you MUST write code to print out the test
        results to the console showing the test cases are passed or not passed; -5 points if the testbed is missing.
*/

import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;
    private final int minYear = 1900; //date must be above 1900
    private final int [] maxDaysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int february = 2; //FEBRUARY IS THE SECOND MONTH.
    private final int numDaysInLeapYear = 29;
    private final int monthsInAYear = 12;

    public Date(String date) { //taking mm/dd/yyyy and create a Date object
        String [] components = date.split("/"); //month, date, year

        //test code commences
        for(int i = 0; i < components.length; i++){
            System.out.println(components[i]);
        } //should display month, date, year
        //test code uncommences

        this.month  = Integer.parseInt(components[0]);
        this.day    = Integer.parseInt(components[1]);
        this.year   = Integer.parseInt(components[2]);

        //do we check whether is valid in here?
    } 
    public Date() {//create an object with today’s date (see Calendar class)
        Calendar calendar = Calendar.getInstance();
        this.year   = calendar.get(Calendar.YEAR);
        this.month  = calendar.get(Calendar.MONTH)+1; //0-indexed months.
        this.day    = calendar.get(Calendar.DATE);
    } 

    public boolean isValid() {
        //Check for negatives
        if(this.year < 0 || this.month < 0 || this.day < 0){
            return false;
        }
        
        //check whether past current date
        Calendar calendar = Calendar.getInstance();
        int currentYear  = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1; //0-indexed months.
        int currentDay   = calendar.get(Calendar.DATE);
        if (this.year > currentYear){
            return false; //year too far
        } else if(this.year == currentYear){ //year equal
            if (this.month > currentMonth){
                return false; //month too far
            } else if(this.month == currentMonth && this.day > currentDay){ //year equal already, checking month and day
                return false; //day too far
            }
        }

        //check whether too old (year too low)
        if(this.year < this.minYear){
            return false;
        }

        //check whether month too big
        if(this.month > this.monthsInAYear){
            return false;
        }

        //check whether leapyear
        if(this.month == this.february && checkLeapYear()){
            if(this.day > this.numDaysInLeapYear){
                return false;
            }
        } else{ //check whether day too big
            if(this.day > this.maxDaysOfMonth[this.month-1]){
                return false;
            }
        }

        return true;
    }
    
    //HELPER METHODS

    private boolean checkLeapYear(){ //returns true if leap year
        if(this.year % 4 == 0){
            if(this.year % 100 == 0){
                if(this.year % 400 == 0){
                    return true;
                } else{
                    return false;
                }
            } else{
                return true;
            }
        } else{
            return false;
        }
    }
}