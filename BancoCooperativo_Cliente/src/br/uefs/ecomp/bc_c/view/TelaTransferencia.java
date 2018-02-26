package br.uefs.ecomp.bc_c.view;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Classe (Java Principal) TelaTransferencia, responsável pela transferencia realizada
 * pelo cliente no sistema Banco Cooperativo.
 * 
 * @author Camille Jesus
 */
public class TelaTransferencia extends Application {
    
    private static String numeroConta;   //Atributo auxiliar para comunicação entre telas
    
    /** Método que carrega a tela e inicializa a cena (frame).
     * 
     * @param stage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Chama o arquivo FXML correpondente:
        Parent root = FXMLLoader.load(getClass().getResource("TelaTransferencia.fxml"));        
        Scene scene = new Scene(root);
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
     * 
     * @return 
     */
    public static String getNumeroConta() {
        return numeroConta;
    }

    /** Método que modifica o número da conta atual (logada).
     * 
     * @param numeroConta
     */
    public static void setNumeroConta(String numeroConta) {
        TelaAcesso.numeroConta = numeroConta;
    }
    
}