package Controller ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.FourLeggedFriend ;
import Model.Dog ;
import Model.Cat ;
import Model.Model ;

import java.util.*;

public class Controller {

    private static Controller theInstance;
    private static ObservableList<FourLeggedFriend> mAllAnimalsList;
    private static ObservableList<FourLeggedFriend> mFilteredAnimalsList;

    private Controller() {}

    public static Controller getInstance() {
        if (theInstance == null) {
            theInstance = new Controller();
            //If the binary file has data, populate the mAllBurritosList from the binary file. Else, populate it from the CSV file.
            theInstance.mAllAnimalsList = Model.binaryFileHasData() ? Model.populateListFromBinaryFile() : Model.populateListFromCSV() ;
            theInstance.mFilteredAnimalsList = FXCollections.observableArrayList();
        }
        return theInstance;
    }
    public static ObservableList<FourLeggedFriend> getAllAnimals() {
        return mAllAnimalsList;
    }
    public ObservableList<FourLeggedFriend> getFilteredAnimalsList(){
        return mFilteredAnimalsList ;
    }
    public static ObservableList<FourLeggedFriend> filterDogs(String group, String size, ArrayList<String> traits)
    {
        mFilteredAnimalsList.clear();
        boolean meetsCriteria;
        for (FourLeggedFriend f : mAllAnimalsList){
            if (f instanceof Dog){
                Dog d = (Dog) f ;
                meetsCriteria = true ;
                if (group != null && !group.equals(" ") && !group.equals(d.getGroup()))
                    meetsCriteria = false ;
                if (size != null && !size.equals(" ") && !size.equals(d.getSize()))
                    meetsCriteria = false ;
                ArrayList<String> dogTraits = Controller.arrayToArrayList(d.getTemperament());
                if (traits != null) {
                    for (String s : traits)
                        if (traits.contains(s))
                            meetsCriteria = true ;
                }
                if (meetsCriteria)
                    mFilteredAnimalsList.add(d) ;
            }
        }
        if (mFilteredAnimalsList.isEmpty())
            return mAllAnimalsList ;
        return mFilteredAnimalsList ;
    }
    public static ObservableList<FourLeggedFriend> filterCats(String furLength, String size, ArrayList<String> traits)
    {
        mFilteredAnimalsList.clear();
        boolean meetsCriteria;
        for (FourLeggedFriend f : mAllAnimalsList) {
            if (f instanceof Cat) {
                Cat c = (Cat) f;
                meetsCriteria = true;
                if (furLength != null && !furLength.equals(" ") && !furLength.equals(c.getFurLength()))
                    meetsCriteria = false;
                if (size != null && !size.equals(" ") && !size.equals(c.getSize()))
                    meetsCriteria = false;
                ArrayList<String> catTraits = Controller.arrayToArrayList(c.getTemperament());
                if (traits != null) {
                    for (String s : traits)
                        if (traits.contains(s))
                            meetsCriteria = true ;
                }
                if (meetsCriteria)
                    mFilteredAnimalsList.add(c);
            }
        }
        if (mFilteredAnimalsList.isEmpty())
            return mAllAnimalsList ;
        return mFilteredAnimalsList ;
    }
    public void saveData() {
        Model.writeDataToBinaryFile(mAllAnimalsList);
    }
    public static String[][] getMostPopularTemperaments(){
        getInstance() ;
        String[][] returnValues = new String[2][15] ;
        String[] uniqueDogWords = new String[15], uniqueCatWords = new String[15] ;
        HashMap<String,Integer> dogWords = new HashMap<>(), catWords = new HashMap<>() ;
        for (FourLeggedFriend f : mAllAnimalsList) {
            if (f instanceof Dog){
                String[] temp = f.getTemperament() ;
                for (int i = 0 ; i < temp.length ; i++){
                    if (dogWords.containsKey(temp[i])){
                        dogWords.put(temp[i],dogWords.get(temp[i])+1) ;
                    } else {
                        dogWords.put(temp[i],1) ;
                    }
                }
            } else if (f instanceof Cat){
                String[] temp = f.getTemperament() ;
                for (int i = 0 ; i < temp.length ; i++){
                    if (catWords.containsKey(temp[i])){
                        catWords.put(temp[i],catWords.get(temp[i])+1) ;
                    } else {
                        catWords.put(temp[i],1) ;
                    }
                }
            }
        }

        //Code credit goes to https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
        List<Map.Entry<String, Integer> > dogList =
                new LinkedList<Map.Entry<String, Integer> >(dogWords.entrySet());
        List<Map.Entry<String, Integer> > catList =
                new LinkedList<Map.Entry<String, Integer> >(catWords.entrySet());

        // Sort the lists In Most Common Order
        Collections.sort(dogList, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.sort(catList, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        //End code credit

        for (int i = 0 ; i < 15 ; i++){
            uniqueDogWords[i] = dogList.get(i).getKey() ;
            uniqueCatWords[i] = catList.get(i).getKey() ;
        }

        returnValues[0] = uniqueDogWords ;
        returnValues[1] = uniqueCatWords ;

        return returnValues ;

    }
    public static String arrayToString(String[] arr){
       String out = "" ;
       if (arr.length == 0){
           return "" ;
       }
        for (int i = 0 ; i < arr.length-1 ; i++){
            out += arr[i] ;
            out += ", " ;
        }
        return out += arr[arr.length-1] + "." ;
    }
    public static ArrayList<String> arrayToArrayList(String[] arr){
        ArrayList<String> out = new ArrayList<String>() ;
        if (arr == null)
            return out ;
        for (String s : arr)
            out.add(s) ;
        return out ;
    }
}
