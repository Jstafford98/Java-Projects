package View.Scenes;

import Model.Cat;
import Model.Dog;
import Model.FourLeggedFriend;
import View.ViewNavigator;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ResultScene extends Scene {

    private String type ;
    private ListView<FourLeggedFriend> friendsLV ;
    private GridPane pane = new GridPane(),
                     alterationsPane = new GridPane(),
                     selectionsPane = new GridPane() ;
    private Button addFriend = new Button("Add A Friend!"),
                   alterSpecifications = new Button("Alter your specifications!"),
                   mainMenu = new Button("Main Menu"),
                   editFriend = new Button("Edit Friend!");
    private Text alterationsPrompt = new Text("Don't see your ideal friend? Try one of these options:");
    private Label selectedAnimalPopularity = new Label(),
                  selectedAnimalBreed = new Label(),
                  breed = new Label("Selected Breed: "),
                  popularity = new Label("Breed Popularity (U.S.):") ;
    private HBox alterationButtons = new HBox(45),
                 breedSelectionLabels = new HBox(),
                 popularitySelectionLabels = new HBox();

    public ResultScene(ObservableList<FourLeggedFriend> list) {
        super(new GridPane(), 850, 850);
        type = list.get(0) instanceof Cat ? "Cat" : "Dog" ;
        //Add Event Listeners
        addFriend.setOnAction(e -> ViewNavigator.loadScene("Add A Friend!",new AddAnimalScene(this, type)));
        alterSpecifications.setOnAction(e -> {
            if (type.equals("Dog"))
                ViewNavigator.loadScene("Dog Preferences", new PreferenceScene("Dog")) ;
            else
                ViewNavigator.loadScene("Cat Preferences", new PreferenceScene("Cat"));
        });
        editFriend.setOnAction(e -> {
            FourLeggedFriend animalToModify = friendsLV.getSelectionModel().getSelectedItem() ;
            if (animalToModify == null)
                return ;
            if (type.equals("Dog"))
                ViewNavigator.loadScene("Dog Preferences", new ModifyAnimalScene(animalToModify, this)) ;
            else
                ViewNavigator.loadScene("Cat Preferences", new ModifyAnimalScene(animalToModify, this));
            updateDisplay() ;
        });
        mainMenu.setOnAction(e -> ViewNavigator.loadScene("Four Legged Love!", new MainScene()));
        //Stylize ListView
        friendsLV = new ListView<>(list) ;
        friendsLV.setPrefWidth(850);
        friendsLV.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue instanceof Dog){
                Dog d = (Dog) newValue ;
                selectedAnimalBreed.setText(d.getBreed()) ;
                selectedAnimalPopularity.setText(String.valueOf(d.getPopularity()));
            } else if (newValue instanceof Cat){
                Cat c = (Cat) newValue ;
                selectedAnimalBreed.setText(c.getBreed()) ;
                selectedAnimalPopularity.setText(String.valueOf(c.getPopularity()));
            }

        });
        //Stylize Selection Members
        selectionsPane.add(breed,0,0) ;
        selectionsPane.add(popularity,0,1) ;
        selectionsPane.add(selectedAnimalBreed,1,0) ;
        selectionsPane.add(selectedAnimalPopularity,1,1) ;
        //Stylize Alteration Members
        alterationButtons.getChildren().addAll(addFriend,alterSpecifications,editFriend) ;
        alterationsPane.add(alterationsPrompt,0,0) ;
        alterationsPane.add(alterationButtons,0,1) ;
        alterationsPane.setVgap(5);
        //Add to main GridPane
        pane.setVgap(25);
        pane.add(selectionsPane,0,0) ;
        pane.add(alterationsPane,0,1) ;
        pane.add(friendsLV,0,2,2,1) ;
        pane.add(mainMenu,0,3) ;
        setRoot(pane) ;
    }
    private void updateDisplay(){
        friendsLV.refresh();
    }
}
