package br.uefs.ecomp.bc_c.view;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Classe (Java Principal) TelaAcesso, responsável pelo acesso da conta pelo cliente
 * no sistema Banco Cooperativo.
 * 
 * @author Camille Jesus
 */
public class TelaAcesso extends Application {
        
    public static String numeroConta;   //Atributo auxiliar para comunicação entre telas
    
    /** Método que carrega a tela e inicializa a cena (frame).
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Chama o arquivo FXML correpondente:
        Parent root = FXMLLoader.load(getClass().getResource("TelaAcesso.fxml"));        
        Scene scene = new Scene(root);
        stage.setTitle("Banco Cooperativo");   //Renomeia o frame
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

    /** Método que recupera o número da conta atual (logada).
     */
    public static String getNumeroConta() {
        return numeroConta;
    }

    /** Método que modifica o número da conta atual (logada).
     */
    public static void setNumeroConta(String numeroConta) {
        TelaAcesso.numeroConta = numeroConta;
    }
    
}