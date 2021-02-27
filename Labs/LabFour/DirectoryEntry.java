public class DirectoryEntry{
  
  //Data Members
  private String mName,
                 mNumber ;

  //Constructors
  /**
 * Creates a generic DirectoryEntry without parameters
 */
public DirectoryEntry(){
    this("Empty","0000000000") ;
  }
 /**Creates a Directory Entry object
 * @param name - the name of the person represented by the directory
 * @param number - the number of the person represented by the directory
 */
public DirectoryEntry(String name, String number){
    mName = name ;
    mNumber = number ;
  }

  //Getters
  /**Returns the name
 * @return - the name of the person represented by the directory
 */
public String getName(){
    return mName ;
  }
  /**Returns the number
 * @return - the number of the person represented by the directory
 */
public String getNumber(){
    return mNumber ;
  }

  //Setters
  /** Sets the name
 * @param name - the name of the person represented by the directory
 */
public void setName(String name){
    mName = name ;
  }
  /** Sets the number 
 * @param number - the number of the person represented by the directory
 */
public void setNumber(String number){
    mNumber = number ;
  }

  //Other
  /**
 *	Returns a formatted string for display
 */
@Override
  public String toString(){
    return "Directory Entry[Name: " + mName + ", Number: " + mNumber + "]" ;
  }
  /**
 * Used to test equality of two objects
 */
@Override
  public boolean equals(Object o){
    if (this == o)
      return true ;
    if (o instanceof DirectoryEntry){
      DirectoryEntry other = (DirectoryEntry) o ;
      return mName.equals(other.getName()) && mNumber.equals(other.getNumber()) ;
    }
    return false ;
  }
}
