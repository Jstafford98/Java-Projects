public class DirectoryEntry{
  
  //Data Members
  private String mName,
                 mNumber ;

  //Constructors
  public DirectoryEntry(){
    this("Empty","0000000000") ;
  }
  /**
  * @param name
  * @param number
  */
  public DirectoryEntry(String name, String number){
    mName = name ;
    mNumber = number ;
  }

  //Getters
  public String getName(){
    return mName ;
  }
  public String getNumber(){
    return mNumber ;
  }

  //Setters
  public void setName(String name){
    mName = name ;
  }
  public void setNumber(String number){
    mNumber = number ;
  }

  //Other
  @Override
  public String toString(){
    return "Directory Entry[Name: " + mName + ", Number: " + mNumber + "]" ;
  }
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
