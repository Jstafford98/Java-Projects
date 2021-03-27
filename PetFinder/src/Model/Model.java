package Model ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class Model {

    private static final String DOG_CSV_FILE = "src/dog_breed_characteristics.csv", CAT_CSV_FILE = "src/cat_breed_characteristics.csv" ;
    public static final String BINARY_FILE = "Animals.dat";
    private static final String SMALL = "Small", MEDIUM = "Medium", LARGE = "Large" ;

    public static boolean binaryFileHasData() {
        //Create a File reference to the binary file
        File binaryFile = new File(BINARY_FILE);
        //Return whether the binary file exists and has data
        return binaryFile.exists() && binaryFile.length() > 0;
    }

    public static ObservableList<FourLeggedFriend> populateListFromBinaryFile() {
        ObservableList<FourLeggedFriend> allAnimals = FXCollections.observableArrayList();
        // Create a File reference to the binary file
        File binaryFile = new File(BINARY_FILE);
        // Check to see if the binary file exists
        if (binaryFileHasData()) {
            try {
                // Instantiate an ObjectInputStream reference to the binary file for reading
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // Create a temp array of Burrito objects to read from the binary file
                // Initialize the temp array from the binary file reader.
                FourLeggedFriend[] temp = (FourLeggedFriend[]) fileReader.readObject();
                // Add the temp array to the collection of all burritos (list)
                allAnimals.addAll(temp);
                // Close the binary file reader.
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ERROR READING " + BINARY_FILE.toUpperCase());
            }
        }

        return allAnimals;
    }

    public static boolean writeDataToBinaryFile(ObservableList<FourLeggedFriend> allAnimalsList) {
        //Create a File reference to the binary file
        File binaryFile = new File(BINARY_FILE);
        try {
            //Instantiate an ObjectOutputStream reference to the binary file for writing
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            //Create a temp array of Burrito objects to read from the binary file (length should match list size)
            FourLeggedFriend[] temp = new FourLeggedFriend[allAnimalsList.size()] ;
            //Loop through the temp array and initialize each element to the corresponding element in the list
            for (int i = 0 ; i < temp.length ; i++){
                temp[i] = allAnimalsList.get(i) ;
            }
            //Write to file
            fileWriter.writeObject(temp) ;
            //Close the binary file writer and return true.
            fileWriter.close() ;
            return true ;
        } catch (IOException e) {
            System.err.println("ERROR WRITING " + BINARY_FILE.toUpperCase());
        }
        return false;
    }

    public static ObservableList<FourLeggedFriend> populateListFromCSV() {

        ObservableList<FourLeggedFriend> allAnimals = FXCollections.observableArrayList();
        String[] data = null;
        String line = null;

        try {
            Scanner fileScanner = new Scanner(new File(DOG_CSV_FILE));

            // First read is for headings (skipped):
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {

                //Store line data
                line = fileScanner.nextLine();
                data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Instantiate Variables
                double weight = Double.parseDouble(data[2]) * 2.20462262 ;
                int popularity = Integer.parseInt(data[6]) ;
                String size,breed = data[0],temperament = data[3], group = data[1] ; ;
                boolean watchDog = Integer.parseInt(data[5]) > 3, intelligence = Integer.parseInt(data[4]) > 50 ;
                if (weight <= 22 && weight > 0)
                    size = SMALL ;
                else if (weight > 22 && weight <= 57)
                    size = MEDIUM ;
                else
                    size = LARGE ;

                temperament.replace("\"","").trim() ;

                //Add object
                allAnimals.add(new Dog(breed,weight,size,temperament,group,intelligence,popularity,watchDog));
            }

            fileScanner = new Scanner(new File(CAT_CSV_FILE));


            // First read is for headings (skipped):
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {

                line = fileScanner.nextLine();
                data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1);

                //Instantiate Variables
                int popularity = Integer.parseInt(data[5]) ;
                String size = "", temperament = data[4], furLength = data[2], breed = data[0] ;
                double weight = Double.parseDouble(data[3]) * 2.20462262 ;
                boolean lapCat = data[1].equals("Lap");

                if (weight <= 8 && weight > 0)
                    size = SMALL ;
                else if (weight > 8 && weight <= 13)
                    size = MEDIUM ;
                else
                    size = LARGE ;

                allAnimals.add(new Cat(weight,size,temperament,furLength,breed,popularity,lapCat));
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error opening CSV file: " + e.getMessage());
        }
        return allAnimals;
    }

}
