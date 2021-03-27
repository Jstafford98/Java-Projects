package View ;

import View.Scenes.MainScene;
import Controller.Controller ;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Four Legged Love!", new MainScene());
    }

    @Override
    public void stop() throws Exception {
        Controller.getInstance().saveData();
    }

    public static void main(String[] args) {
        Application.launch(args);

    }

}
