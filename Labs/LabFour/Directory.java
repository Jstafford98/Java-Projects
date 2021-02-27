import java.util.ArrayList ;

class Directory {
  
  private static ArrayList<DirectoryEntry> theDirectory ;

  /**
   * Creates the directory object to store our entries
   */
  public Directory() {
	  theDirectory = new ArrayList<>() ;
  }
  /** Add an entry to theDirectory or change an existing entry. 
  * @param aName the name of the person being added or changed
  * @param newNumber the new number to be assigned
  * @return the old number, or if a new entry, null
  */
  public static String addOrChangeEntry(String aName, String newNumber){

    String oldNumber ;

    for (DirectoryEntry dE : theDirectory){
        if(dE.getName().equals(aName)){
            oldNumber = dE.getNumber() ;
            dE.setNumber(newNumber) ;
            return oldNumber ;
        }
    }

    theDirectory.add(new DirectoryEntry(aName,newNumber)) ;

    return null ;
  }
  /** Remove an entry.
  * @param aName the name of the person being removed
  * @return the entry removed, or null if there is no entry for aName
  */
  public DirectoryEntry removeEntry(String aName){

    DirectoryEntry oldEntry ;

    for (DirectoryEntry dE : theDirectory){
        if(dE.getName().equals(aName)){
            return theDirectory.remove(theDirectory.indexOf(dE)) ;
        }
    }

    return null ;
  }
  /**
   * Print all directory entries
   */
  public static void printDirectory(){
      System.out.println("     Current Directory     ") ;
      System.out.println("---------------------------") ;
      for (DirectoryEntry dE : theDirectory)
          System.out.println(dE) ;
  }


}
