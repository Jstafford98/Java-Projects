package View.Scenes;

import Controller.Controller;
import View.ViewNavigator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PreferenceScene extends Scene {

    //Labels
    private Label titleBar,breedLabel,furLengthLabel,sizeLabel,temperamentLabel ;

    //Buttons
    private Button mainMenuButton = new Button("Main Menu"),
                   findFriendButton = new Button("Find My Friend!") ;

    //Comboboxes
    private ComboBox<String> sizeCB = new ComboBox<>(),
                   furLengthCB = new ComboBox<>(),
                   breedGroupCB = new ComboBox<>() ;

    //Other Members
    private String[][] temperaments = Controller.getMostPopularTemperaments() ;
    private CheckBox[] checkBoxes = new CheckBox[15];
    private HBox buttonSelections = new HBox(25) ;
    private GridPane checks = new GridPane(), pane = new GridPane() ;

    public PreferenceScene(String type){

        //Call to super
        super(new GridPane(), 850, 400);

        titleBar = new Label(type.equals("Cat") ? "Lets Find Your Purrfect Feline Friend!" : "Lets Find Your Pawfect Canine Companion!") ;

        //Add event driven actions
        mainMenuButton.setOnAction(e -> ViewNavigator.loadScene("Four Legged Love!", new MainScene()));
        findFriendButton.setOnAction(e -> {
            if (type.equals("Cat")){
                String furLength = furLengthCB.getSelectionModel().getSelectedItem(),
                       catSize = sizeCB.getSelectionModel().getSelectedItem() ;
                ArrayList<String> selectedTraits = new ArrayList<>() ;
                int nodeCounter = 0 ;
                for (Node n : checks.getChildren()){
                    CheckBox current = (CheckBox) n ;
                    if (current.isSelected()){
                        selectedTraits.add(current.getText()) ;
                    }
                    nodeCounter++ ;
                }
                ViewNavigator.loadScene("Your Potential Friends", new ResultScene(Controller.filterCats(furLength,catSize,selectedTraits))) ;
            } else {
                String group = breedGroupCB.getSelectionModel().getSelectedItem(),
                        dogSize = sizeCB.getSelectionModel().getSelectedItem() ;
                ArrayList<String> selectedTraits = new ArrayList<>() ;
                int nodeCounter = 0 ;
                for (Node n : checks.getChildren()){
                    CheckBox current = (CheckBox) n ;
                    if (current.isSelected()){
                        selectedTraits.add(current.getText()) ;
                    }
                    nodeCounter++ ;
                }
                ViewNavigator.loadScene("Your Potential Friends", new ResultScene(Controller.filterDogs(group,dogSize,selectedTraits))) ;
            }
        });

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

        //Set Title Stylization
        titleBar.setStyle("-fx-font: 28 arial;") ;

        //Stylize Checkboxes
        checks.setHgap(10);
        checks.setVgap(10);

        //Stylize Button Options
        buttonSelections.getChildren().addAll(findFriendButton,mainMenuButton) ;
        buttonSelections.setAlignment(Pos.CENTER_LEFT);

        //Stylize Labels
        breedLabel = new Label("Choose Your Ideal Breed Group:") ;
        furLengthLabel = new Label("Choose Your Ideal Cat Fur Length:") ;
        sizeLabel = new Label(type.equals("Cat") ? "Choose Your Ideal Cat Size:" : "Choose Your Ideal Dog Size:") ;
        temperamentLabel = new Label("Select Your Ideal Personality Traits:") ;

        //Stylize root gridpane
        pane.setVgap(25);

        //Add all objects to root gridpane
        pane.add(titleBar,1,0) ;
        pane.add(type.equals("Cat") ? furLengthLabel : breedLabel,0,1) ;
        pane.add(sizeLabel,0,2) ;
        pane.add(temperamentLabel,0,3) ;
        pane.add(type.equals("Cat") ? furLengthCB : breedGroupCB,1,1) ;
        pane.add(sizeCB,1,2) ;
        pane.add(checks,1,4) ;
        pane.add(buttonSelections,1,5) ;

        //Set root gridpane
        this.setRoot(pane) ;
    }
}
