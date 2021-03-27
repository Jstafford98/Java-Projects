package View.Scenes ;

import Model.Cat;
import Model.Dog;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import View.ViewNavigator ;
import Controller.Controller ;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class AddAnimalScene extends Scene {

    private String mType, breed, size, furLength, group ;
    private Double weight ;
    private int popularity ;
    private boolean isLapCat, isWatchdog, isIntelligent ;
    private String[] temperament ;
    //Labels
    private Label titleBar,breedLabel,groupLabel,furLengthLabel,sizeLabel,temperamentLabel, weightLabel,watchDogLabel,intelligentLabel,lapCatLabel, popularityLabel ;

    //Buttons
    private Button Add = new Button("Add Friend"), cancel = new Button("Cancel");

    //Comboboxes
    private ComboBox<String> sizeCB = new ComboBox<>(),
            furLengthCB = new ComboBox<>(),
            breedGroupCB = new ComboBox<>() ;

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


    private Scene mPrevScene ;


    public AddAnimalScene(Scene prev,String type) {

        super(new GridPane(), 850, 400);

        mType = type ;
        mPrevScene = prev ;

        //Buttons
        Add.setOnAction(e -> addAnimal());
        cancel.setOnAction(e -> goBackToPrevScene());

        //Labels
        titleBar = new Label(type.equals("Dog") ? "Let's Add Your Ideal Canine Companion!" : "Let's Add Your Ideal Feline Friend!") ;
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

        //Text Fields
        breedTF = new TextField() ;
        weightTF = new TextField() ;
        popularityTF = new TextField() ;

        //Toggle Group
        yesOne.setToggleGroup(yesNoOne);
        noOne.setToggleGroup((yesNoOne));
        yesNoCheckBoxOne.getChildren().addAll(yesOne,noOne) ;
        yesTwo.setToggleGroup(yesNoTwo);
        noTwo.setToggleGroup(yesNoTwo);
        getYesNoCheckBoxTwo.getChildren().addAll(yesTwo,noTwo) ;

        //Instantiate Checkboxes for display and add to checks gridpane
        int animalType = type.equals("Cat") ? 1 : 0 ;
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
        pane.add(Add,0,9) ; pane.add(cancel,1,9) ;

        pane.setVgap(10);

        //Add breed specific GUI elements
        if (type.equals("Cat")){
            pane.add(furLengthLabel,0,2) ; pane.add(furLengthCB,1,2) ;
            pane.add(lapCatLabel,0,6) ; pane.add(yesNoCheckBoxOne,1,6) ;
        } else {
            pane.add(groupLabel,0,2) ; pane.add(breedGroupCB,1,2) ;
            pane.add(watchDogLabel,0,6) ; pane.add(yesNoCheckBoxOne,1,6) ;
            pane.add(intelligentLabel,0,7) ; pane.add(getYesNoCheckBoxTwo,1,7) ;
        }

        setRoot(pane) ;

    }

    public void addAnimal(){

        ArrayList<String> selectedTraits = new ArrayList<>() ;
        for (Node n : checks.getChildren()){
            if (n instanceof CheckBox){
                CheckBox cB = (CheckBox) n ;
                if (cB.isSelected())
                    selectedTraits.add(cB.getText()) ;
            }
        }
        String tmp = String.join(",",selectedTraits) ;

        if (mType.equals("Dog")) {
            breed = breedTF.getText() ;
            //Validate weight
            try {
                weight = Double.parseDouble(weightTF.getText());
                if (weight < 0.0)
                    throw new NumberFormatException() ;
            }
            catch (NumberFormatException e) {
                weight = 0.0 ;
            }
            //Validate popularity
            try {
                popularity = Integer.parseInt(popularityTF.getText()) ;
                if (popularity < 0)
                    throw new NumberFormatException() ;
            }
            catch (NumberFormatException e) {
                popularity = 0 ;
            }
            size = sizeCB.getSelectionModel().getSelectedItem() ;
            group = breedGroupCB.getSelectionModel().getSelectedItem() ;
            isIntelligent = yesNoOne.getSelectedToggle() == yesOne ? true : false ;
            isWatchdog = yesNoTwo.getSelectedToggle() == yesTwo ? true : false ;
            Controller.getAllAnimals().add(new Dog(breed, weight, size, tmp, group, isIntelligent, popularity, isWatchdog));

        } else if (mType.equals("Cat")) {
            breed = breedTF.getText() ;
            //Validate weight
            try {
                weight = Double.parseDouble(weightTF.getText());
                if (weight < 0.0)
                    throw new NumberFormatException() ;
            }
            catch (NumberFormatException e) {
                weight = 0.0 ;
            }
            //Validate popularity
            try {
                popularity = Integer.parseInt(popularityTF.getText()) ;
                if (popularity < 0)
                    throw new NumberFormatException() ;
            }
            catch (NumberFormatException e) {
                popularity = 0 ;
            }
            size = sizeCB.getSelectionModel().getSelectedItem() ;
            furLength = furLengthCB.getSelectionModel().getSelectedItem() ;
            isLapCat = yesNoOne.getSelectedToggle() == yesOne ? true : false ;
            Controller.getAllAnimals().add(new Cat(weight, size, tmp, furLength, breed, popularity, isLapCat));
        }

        goBackToPrevScene();

    }
    public void goBackToPrevScene(){
        ViewNavigator.loadScene("Your Potential Friends",mPrevScene) ;
    }


}
