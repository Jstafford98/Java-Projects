package View.Scenes ;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import View.ViewNavigator ;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MainScene extends Scene {


    private Button findDog = new Button("Dog"),
                   findCat = new Button("Cat");
    private Text header = new Text("Furry Friend Finder"),
                 prompt = new Text("Choose Your Animal Type:") ;
    private ImageView logo = new ImageView(new Image("icon.jpg")) ;

    public MainScene() {
        super(new GridPane(), 850, 400);

        findCat.setOnAction(e -> ViewNavigator.loadScene("Cat Preferences",new PreferenceScene("Cat"))) ;
        findDog.setOnAction(e -> ViewNavigator.loadScene("Dog Preferences",new PreferenceScene("Dog"))) ;

        header.setStyle("-fx-font: 42 arial;") ;


        GridPane pane = new GridPane();
        HBox buttons = new HBox(50) ;
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(0,findDog) ;
        buttons.getChildren().add(1,findCat) ;

        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));
        pane.setAlignment((Pos.CENTER));

        ColumnConstraints columns = new ColumnConstraints() ;
        columns.setHalignment(HPos.CENTER);
        pane.getColumnConstraints().add(columns) ;

        pane.add(header,0,0) ;
        pane.add(logo,0,1) ;
        pane.add(buttons,0,2) ;

        this.setRoot(pane);
    }








}
