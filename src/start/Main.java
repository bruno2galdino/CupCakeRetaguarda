/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/telas/FXMLHome.fxml"));
        final Scene scene = new Scene(root, 678, 351);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setTitle("CupCake Rataguarda");
        stage.setMinWidth(678);
        stage.setMinHeight(351);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
