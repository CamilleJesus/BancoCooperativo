/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_s.connection;

import java.io.IOException;

import java.net.ServerSocket;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe Servidor, entidade que redireciona um novo cliente para um ThreadCliente.
 * 
 * @author Camille Jesus
 */
public class BancoCooperativo_Servidor implements Runnable {
    
    private ServerSocket socketServidor;
    
    /** Construtor da classe Servidor que cria um ServerSocket passando a porta
     * informada e inicia a thread.
     * 
     * @param porta
     * @throws Exception 
     */
    public BancoCooperativo_Servidor(int porta) throws Exception {
        socketServidor = new ServerSocket(porta);
        new Thread(this).start();
        System.out.println("Servidor iniciado...");
        System.out.println("Porta: " + porta);
    }
    
    /** Roda o servidor, a espera de um cliente para redirecionar ao ThreadCliente.
     */
    @Override
    public void run() {
        
        try {
            while (true) {
                new ThreadCliente(socketServidor.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(BancoCooperativo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Classe que instancia o servidor passando a porta por parâmetro.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        try {
            new BancoCooperativo_Servidor(27279);
        } catch (Exception ex) {
            Logger.getLogger(BancoCooperativo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}