//import java.time.LocalDate;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Date {
   /*
   * Instance data :
   */
  private int year, month, day;
  public boolean state;
  private LocalDate date;
  /*
   * Constructor :
   */
  public Date(int y, int m, int d){
   this.year = y;
   this.month = m;
   this.day = d;
   this.date = LocalDate.of(year, month, day);
   }

  /*
   * dateIsValid :
   * 
   * check if the year, month, and day values passed
   * to it can be used to create a valid date and
   * returns true if the condition can be satisfied
   * and false otherwise.
   *
   */

public boolean isDateValid() 
  {
    if (year > 1899 && year < 2100){
      if(isleapyear()){
        switch(month){
          case 1:case 3:case 5:case 7:case 8:case 10:case 12:
            if (day < 32)
              state = true;
            break;
          case 4:case 6:case 9:case 11:
            if (day < 31) 
              state = true;
          case 2:
            if (day < 30)
              state = true;
            break;
          default:
            state = false;
        }
      }
      else
        switch(month){
          case 1:case 3:case 5:case 7:case 8:case 10:case 12:
            if (day < 32)
              state = true;
            break;
          case 4:case 6:case 9:case 11:
            if (day < 31) 
              state = true;
          case 2:
            if (day<29)
              state = true;
            break;
          default:
            state = false;
        }
    }
    else
      state = false;
        return state; 
  }  
  
public boolean isleapyear(){
  boolean leapyear;
  if (year % 4 == 0){
    if (year % 100 !=0){
      if (year % 400 == 0)
        leapyear = true;
      else
        leapyear = false;
    } else
      leapyear = true;
  } else 
    leapyear = false;
  return leapyear;
}

// Calculates the duration between two dates
public static int calculateDuration(Date start, Date end) {
    Period period = Period.between(start.date, end.date);
    return period.getDays();
}

// Formats the date to a specific pattern
public String formatDate(String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return date.format(formatter);
}
  /*
   * difference:
   * 
   * returns the difference in years
   * between the dates that are passed in
   
  public static int difference(Date fromDate, Date toDate) {
      if (fromDate.isDateValid() && toDate.isDateValid())
      return  (fromDate.year - toDate.year);
    else
      return (-1);
  }*/

  public String toString(){
    String date =( year + "/" + month + "/" + day);
      return date;
  }


}