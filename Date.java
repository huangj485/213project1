/** Date: this class defines the properties of a Date object, specifically year, month, day.
 * @author Jerry Huang, Adrian Thamburaj
 */

import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;
    private static final int minYear = 1900; //date must be above 1900
    private static final int [] maxDaysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int february = 2; //FEBRUARY IS THE SECOND MONTH.
    private static final int numDaysInLeapYear = 29;
    private static final int monthsInAYear = 12;

    /** Creates a Date from a given String
     * @param date A String containing a date formated as "mm/dd/yyyy"
     */
    public Date(String date) { //taking mm/dd/yyyy and create a Date object
        String [] components = date.split("/"); //month, date, year

        this.month  = Integer.parseInt(components[0]);
        this.day    = Integer.parseInt(components[1]);
        this.year   = Integer.parseInt(components[2]);

        //do we check whether is valid in here?
    } 

    /** Default - creates today's date.
     */
    public Date() {//create an object with today’s date (see Calendar class)
        Calendar calendar = Calendar.getInstance();
        this.year   = calendar.get(Calendar.YEAR);
        this.month  = calendar.get(Calendar.MONTH)+1; //0-indexed months.
        this.day    = calendar.get(Calendar.DATE);
    } 

    /** Checks to make sure that a given date is valid
     * @return A boolean - true if valid, false if not
     */
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
        if(this.year < Date.minYear){
            return false;
        }

        //check whether month too big
        if(this.month > Date.monthsInAYear){
            return false;
        }

        //check whether leapyear
        if(this.month == Date.february && checkLeapYear()){
            if(this.day > Date.numDaysInLeapYear){
                return false;
            }
        } else{ //check whether day too big
            if(this.day > Date.maxDaysOfMonth[this.month-1]){
                return false;
            }
        }

        return true;
    }

    //HELPER METHODS

    /** Checks if the year is a leap year
     * @return A boolean - true if a leap year, false if not
     */
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

    /** Compares a date with this date
     * @param date The date to compare with this date
     * @return A boolean - true if this Date is less than the given parameter date. False otherwise
     */
    public boolean compare(Date date){ //returns true if this Date is less than date
        if (this.year > date.getYear()){
            return false; //year too far
        } else if(this.year == date.getYear()){ //year equal
            if (this.month > date.getMonth()){
                return false; //month too far
            } else if(this.month == date.getMonth() && this.day > this.getDay()){ //year equal already, checking month and day
                return false; //day too far
            }
        }
        return true;
    }

    //GETTER METHODS

    /** Returns Date in the format mm/dd/yyyy
     * @return A String representation of Date
     */
    @Override
    public String toString(){
        String toReturn = "";
        toReturn += String.valueOf(this.month) + "/";
        toReturn += String.valueOf(this.day) + "/";
        toReturn += String.valueOf(this.year) + "/";
        return toReturn;
    }

    /** Gets the year
     * @return A four-digit integer representing the year.
     */
    public int getYear(){
        return this.year;
    }

    /** Gets the month of the Date object
     * @return An integer between 1 and 12 representing the month (from January to December, respectively)
     */
    public int getMonth(){
        return this.month;
    }

    /** Gets the day of the date
     * @return An integer between 1 and 31 representing the day of the month.
     */
    public int getDay(){
        return this.day;
    }

    //TESTBED
    /** Testbed main for the isValid method
     * Tests 30 dates against their correct outputs for isValid.
     */
    public static void main(String[]args){
        //testcase1 "12/31/1899"
        //date too old
        test(false, "12/31/1899");

        //testcase2 "3/1/2021"
        //date in the future
        test(false, "12/1/2021");

        //testcase3 "4/31/2019"
        //there are 30 days in April
        test(false, "4/31/2019");

        //testcase4 "1/32/2000"
        //numbers of days too high
        test(false, "1/32/2000");

        //testcase5 "-7/19/2000"
        //negative month
        test(false, "-7/19/2000");

        //testcase6 = "7/-19/2000"
        //negative day
        test(false, "7/-19/2020");

        //testcase7 = "7/19/-2000"
        //negative year
        test(false, "7/19/-2000");
        
        //testcase8 = "13/13/2013"
        //invalid month
        test(false, "13/13/2013");

        //testcase9 = "12/13/2022"
        //year in the future
        test(false, "12/13/2022");

        //testcase10 = "1/1/1900"
        //this is the first allowed date
        test(true, "1/1/1900");

        //testcase11 = "2/29/2019"
        //bad leapyear
        test(false, "2/29/2019");
        
        //testcase12 = "2/29/2020"
        //good leap year
        test(true, "2/29/2020");

        //testcase13 = "2/29/1988"
        //good leap year
        test(true, "2/29/1988");

        //testcase14 = "2/29/1986"
        //bad leap year
        test(false, "2/29/1986");
        
        Calendar calendar = Calendar.getInstance();
        int currentYear  = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1; //0-indexed months.
        int currentDay   = calendar.get(Calendar.DATE);
        
        //testcase15 = today
        //Last valid day
        test(true, currentMonth+"/"+currentDay+"/"+currentYear);

        //testcase16 = "9/9/1999"
        //correct, normal test case
        test(true, "9/9/1999");

        //testcase17 = "9/9/99"
        //incorrect year format
        test(false, "9/9/99");

        //testcase18 = "09/09/1999"
        //should be fine with zeroes
        test(true, "09/09/1999");

        //testcase19 = "1/32/2020"
        //Test cases 19-30 are for the days exactly one above maximum
        test(false, "1/32/2020");

        //testcase20 = "2/30/2020"
        test(false, "2/30/2020");

        //testcase21 = "3/32/2020"
        test(false, "3/32/2020");

        //testcase22 = "4/31/2020"
        test(false, "4/31/2020");

        //testcase23 = "5/32/2020"
        test(false, "5/32/2020");
        
        //testcase24 = "6/31/2020"
        test(false, "6/31/2020");

        //testcase25 = "7/32/2020"
        test(false, "7/32/2020");

        //testcase26 = "8/32/2020"
        test(false, "8/32/2020");
        
        //testcase27 = "9/31/2020"
        test(false, "9/31/2020");

        //testcase28 = "10/32/2020"
        test(false, "10/32/2020");
        
        //testcase29 = "11/31/2020"
        test(false, "11/31/2020");
        
        //testcase30 = "12/32/2020"
        test(false, "12/32/2020");
        
    }

    /** Helper method for testbed main
     * @param actual The expected boolean value
     * @param testDate The String representing the date to be tested
     */
    //helper method for testbed
    private static void test(boolean actual, String testDate){
        Date date = new Date(testDate);
        System.out.println("Test Date: " + date.toString() + ", Expected: " + actual + ", Result: " + date.isValid());
    }
}