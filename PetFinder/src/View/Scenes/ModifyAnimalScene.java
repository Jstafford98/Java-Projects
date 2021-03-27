package View.Scenes ;

import Model.Cat;
import Model.Dog;
import Model.FourLeggedFriend;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import View.ViewNavigator ;
import Controller.Controller ;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ModifyAnimalScene extends Scene{

    //Local Variables
    private FourLeggedFriend mAnimalToModify ;
    private Scene mPrev ;
    private String mType, breed, size, furLength, group ;
    private Double weight ;
    private int popularity ;
    private boolean isLapCat, isWatchdog, isIntelligent ;
    private String[] temperament ;

    //Buttons
    private Button confirm = new Button("Confirm"), cancel = new Button("Cancel") ;

    //Comboboxes
    private ComboBox<String> sizeCB = new ComboBox<>(),
            furLengthCB = new ComboBox<>(),
            breedGroupCB = new ComboBox<>() ;

    //Labels
    private Label titleBar,breedLabel,groupLabel,furLengthLabel,sizeLabel,temperamentLabel, weightLabel,watchDogLabel,intelligentLabel,lapCatLabel, popularityLabel ;

    //Textfields
    TextField breedTF,weightTF, popularityTF ;

    //Other Members
    private String[][] temperaments = Controller.getMostPopularTemperaments() ;
    private CheckBox[] checkBoxes = new CheckBox[15] ;
    private GridPane checks = new GridPane(), pane = new GridPane() ;
    private ToggleGroup yesNoOne = new ToggleGroup(), yesNoTwo = new ToggleGroup() ;
    private RadioButton yesOne = new RadioButton("Yes"), noOne = new RadioButton("No"),
            yesTwo = new RadioButton("Yes"), noTwo = new RadioButton("No");
    private HBox yesNoCheckBoxOne = new HBox(), getYesNoCheckBoxTwo = new HBox() ;

    public ModifyAnimalScene(FourLeggedFriend animalToModify, Scene prev) {
        super(new GridPane(), 850, 850);

        //Set Local Variables
        mAnimalToModify = animalToModify ;

        temperament = animalToModify.getTemperament() ;
        mPrev = prev ;
        mType = animalToModify instanceof Dog ? "Dog" : "Cat" ;

        //Add actions
        confirm.setOnAction(e -> {
            modify();
        });
        cancel.setOnAction(e -> goBackToPrevScene());

        //Labels
        titleBar = new Label(mType.equals("Dog") ? "Edit This Canine Companion!" : "Edit This Feline Friend!") ;
        breedLabel = new Label("Breed:") ;
        groupLabel = new Label("Breed Group:") ;
        weightLabel  = new Label("Weight:") ;
        sizeLabel  = new Label("Size:") ;
        temperamentLabel  = new Label("Temperament:") ;
        watchDogLabel  = new Label("is a watchdog?") ;
        intelligentLabel  = new Label("is intelligent?") ;
        furLengthLabel  = new Label("Fur Length:") ;
        lapCatLabel  = new Label("is a Lapcat?") ;
        popularityLabel = new Label("Popularity (U.S.):") ;

        //Text Fields And Default Values
        breedTF = new TextField() ;
        breedTF.setText(mAnimalToModify.getBreed()) ;
        weightTF = new TextField() ;
        weightTF.setText(String.valueOf(mAnimalToModify.getWeight())) ;
        popularityTF = new TextField() ;
        popularityTF.setText(String.valueOf(mAnimalToModify.getPopularity())) ;
        sizeCB.getSelectionModel().select(mAnimalToModify.getSize()) ;

        //Toggle Group
        yesOne.setToggleGroup(yesNoOne);
        noOne.setToggleGroup((yesNoOne));
        yesNoCheckBoxOne.getChildren().addAll(yesOne,noOne) ;
        yesTwo.setToggleGroup(yesNoTwo);
        noTwo.setToggleGroup(yesNoTwo);
        getYesNoCheckBoxTwo.getChildren().addAll(yesTwo,noTwo) ;

        //Instantiate Checkboxes for display and add to checks gridpane
        int animalType = mType.equals("Cat") ? 1 : 0 ;
        for (int i = 0 ; i < 15 ; i++) {
            checkBoxes[i] = new CheckBox(temperaments[animalType][i].trim());
        }
        for (int i = 0 ; i < 15 ; i++) {
            if (i < 5)
                checks.add(checkBoxes[i],i,0);
            else if (i >= 5 && i < 10)
                checks.add(checkBoxes[i],i-5,1);
            else
                checks.add(checkBoxes[i],i-10,2);
        }
        //Get selected temperaments
        ArrayList<String> selectedTraits = Controller.arrayToArrayList(temperament);
        int nodeCounter = 0;
        for (Node n : checks.getChildren()) {
            CheckBox current = (CheckBox) n;
            if (selectedTraits.contains(current.getText())) {
                current.setSelected(true);
                current.setIndeterminate(false);
            }
            nodeCounter++;
        }



        //Instantiate Drop-down comboboxes
        breedGroupCB.getItems().addAll("Herding","Hound", "Non Sporting","Sporting","Terrier","Toy","Working") ;
        furLengthCB.getItems().addAll("Bald","Short","Medium","Long") ;
        sizeCB.getItems().addAll("Small","Medium","Large") ;

        //Add constant GUI elements
        pane.add(titleBar,1,0) ;
        titleBar.setStyle("-fx-font: 28 arial;") ;
        pane.add(breedLabel,0,1) ; pane.add(breedTF,1,1) ;
        pane.add(weightLabel,0,3) ; pane.add(weightTF,1,3) ;
        pane.add(sizeLabel,0,4) ; pane.add(sizeCB,1,4) ;
        pane.add(temperamentLabel,0,5) ; pane.add(checks,1,5) ;
        pane.add(popularityLabel,0,8) ; pane.add(popularityTF,1,8) ;
        pane.add(confirm,0,9) ; pane.add(cancel,1,9) ;

        pane.setVgap(10);

        //Add breed specific GUI elements
        if (mType.equals("Cat")){
            Cat temp = (Cat) mAnimalToModify ;

            //Set all default values
            furLengthCB.getSelectionModel().select(temp.getFurLength()) ;
            if (temp.isLapCat())
                yesOne.fire();
            else
                noOne.fire();


            //Add elements
            pane.add(furLengthLabel,0,2) ; pane.add(furLengthCB,1,2) ;
            pane.add(lapCatLabel,0,6) ; pane.add(yesNoCheckBoxOne,1,6) ;

        } else {
            Dog temp = (Dog) mAnimalToModify ;
            breedGroupCB.getSelectionModel().select(temp.getGroup()) ;
            //Set default values
            if (temp.isWatchdog())
                yesOne.fire() ;
            else
                noOne.fire() ;
            if (temp.isIntelligent())
                yesTwo.fire();
            else
                noTwo.fire();

            //Add elements
            pane.add(groupLabel,0,2) ; pane.add(breedGroupCB,1,2) ;
            pane.add(watchDogLabel,0,6) ; pane.add(yesNoCheckBoxOne,1,6) ;
            pane.add(intelligentLabel,0,7) ; pane.add(getYesNoCheckBoxTwo,1,7) ;


        }
        setRoot(pane) ;
    }


    private void modify() {

        //Constant Values
        breed = breed == null || breed.equals(" ") ? mAnimalToModify.getBreed() : breedTF.getText();
        size = sizeCB.getSelectionModel().getSelectedItem();

        //Breed Specific
        if (mType.equals("Dog")) {
            group = breedGroupCB.getSelectionModel().getSelectedItem();
            isIntelligent = yesNoTwo.getSelectedToggle() == yesOne ? true : false;
            isWatchdog = yesNoOne.getSelectedToggle() == yesOne ? true : false;
        } else {
            furLength = furLengthCB.getSelectionModel().getSelectedItem();
            isLapCat = yesNoOne.getSelectedToggle() == yesOne ? true : false;
        }

        //Validate weight
        try {
            weight = Double.parseDouble(weightTF.getText());
            if (weight < 0.0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            weight = mAnimalToModify.getWeight();
        }

        //Validate popularity
        try {
            popularity = Integer.parseInt(popularityTF.getText());
            if (popularity < 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            popularity = mAnimalToModify.getPopularity();
        }

        //Get selected temperaments
        ArrayList<String> selectedTraits = new ArrayList<>();
        int nodeCounter = 0;
        for (Node n : checks.getChildren()) {
            CheckBox current = (CheckBox) n;
            if (current.isSelected()) {
                selectedTraits.add(current.getText());
            }
            nodeCounter++;
        }
        Object[] temp = selectedTraits.toArray();
        String[] traits = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            traits[i] = (String) temp[i];
        }

        Dog tempDog;
        Cat tempCat;
        if (mType.equals("Dog")){
            tempDog = (Dog) mAnimalToModify;
            tempDog.setBreed(breed);
            tempDog.setGroup(group);
            tempDog.setSize(size);
            tempDog.setTemperament(Controller.arrayToString(traits));
            tempDog.setPopularity(popularity);
            tempDog.setWatchdog(isWatchdog);
            tempDog.setIntelligence(isIntelligent);
            mAnimalToModify = tempDog;
        } else{
            tempCat = (Cat) mAnimalToModify;
            tempCat.setBreed(breed);
            tempCat.setFurLength(furLength);
            tempCat.setSize(size);
            tempCat.setTemperament(Controller.arrayToString(traits));
            tempCat.setPopularity(popularity);
            tempCat.setLapCat(isLapCat);
            mAnimalToModify = tempCat;
        }

        goBackToPrevScene();

    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Your Potential Friends",mPrev);
    }
}
