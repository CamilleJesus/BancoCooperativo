package br.uefs.ecomp.bc_c.view;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 * Classe (Java Principal) TelaCadastro, responsável pelo cadastro do cliente e
 * criação da conta no sistema Banco Cooperativo.
 * 
 * @author Camille Jesus
 */
public class TelaCadastro extends Application {
    
    /** Método que carrega a tela e inicializa a cena (frame).
     * 
     * @param stage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Chama o arquivo FXML correpondente:
        Parent root = FXMLLoader.load(getClass().getResource("TelaCadastro.fxml"));   //Renomeia o frame      
        Scene scene = new Scene(root);
        stage.setTitle("Banco Cooperativo");       
        stage.setScene(scene);
        stage.show();
    }

    /** Método que inicia o programa.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}