/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HomeController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnVenda;

    @FXML
    private AnchorPane anchorPanePrincipal;

    @FXML
    public Button btnProduto;

    @FXML
    private Button btnCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnProduto.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/FXMLProduto.fxml"));
                    Node node = loader.load();
                    anchorPanePrincipal.getChildren().add(node);
                    ProdutoController.getPai(anchorPanePrincipal);

                } catch (Exception e) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);

                }
            }
        });

    }

}
