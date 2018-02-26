package br.uefs.ecomp.bc_s.connection;

import br.uefs.ecomp.bc_s.controller.ControllerServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ThreadCliente, thread que é criada para tratamento de cada cliente aceito
 * no servidor.
 * 
 * @author Camille Jesus
 */
public class ThreadCliente extends Thread {

    private Socket socketCliente;
    private ObjectInputStream doCliente = null;
    private ObjectOutputStream paraCliente = null;
    private ControllerServidor controllerServidor = new ControllerServidor();
    private String mensagem;
    private String retorno;
    
    /** Construtor da classe que recebe um cliente.
     * 
     * @param socket 
     */
    public ThreadCliente(Socket socket) {
        socketCliente = socket;
    }
    
    /** Método executado para cada cliente aceito, carregas os dados de clientes
     * e contas no sistema, inicia a leitura e escrita de objetos pela porta e apresenta
     * as condições de execução e envio para cada requisição possível.
     */
    public void run() {
        controllerServidor.carregarClientesSistema();
        controllerServidor.carregarContasSistema();
        
        try {
            doCliente = new ObjectInputStream(socketCliente.getInputStream());
            paraCliente = new ObjectOutputStream(socketCliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(BancoCooperativo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            
            if (socketCliente.isConnected()) {   //Se o cliente está conectado
                
                try {
                    mensagem = ((String) doCliente.readObject());   //Lê a mensagem recebida
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(BancoCooperativo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
                String parte[] = mensagem.split(":");   //Separa as partes da mensagem

                try {
                    switch (parte[0]) {   //Para cada tipo de requisição:
                        case "0":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita logout de cliente");
                            socketCliente.close();
                            doCliente.close();
                            paraCliente.close();
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou logout de cliente");
                            break;
                        case "1":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita cadastro de cliente");
                            controllerServidor.cadastrarCliente(parte[1], parte[2], parte[3]);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou cadastro de cliente");
                            break;
                        case "2":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita criação de conta");
                            retorno = controllerServidor.cadastrarConta(parte[1], parte[2]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou criação de conta");
                            break;
                        case "3":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita vínculo de cliente à conta atual");
                            controllerServidor.adicionarTitular(parte[1], parte[2]);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou vínculo de cliente à conta");
                            break;
                        case "4":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita login de cliente");
                            retorno = controllerServidor.fazerLogin(parte[1], parte[2]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou login de cliente");
                            break;
                        case "5":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita depósito");
                            controllerServidor.depositar(parte[1], parte[2]);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou depósito");
                            break;
                        case "6":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita transferência");
                            controllerServidor.transferir(parte[1], parte[2], parte[3]);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou transferência");
                            break;
                        case "7":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita adição de titular");
                            controllerServidor.adicionarTitular(parte[1], parte[2], parte[3]);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou adição de titular");
                            break;
                        case "8":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita verificação de saldo");
                            retorno = controllerServidor.verificarSaldo(parte[1]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou verificação de saldo");
                            break;
                        case "9":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita informações de acesso");
                            retorno = controllerServidor.exibirInfoGeral(parte[1]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou visualização de informações de acesso");
                            break;
                        case "10":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita verificação de conta");
                            retorno = controllerServidor.contaExiste(parte[1]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou verificação de de conta");
                            break;
                        case "11":
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Solicita verificação de titular");
                            retorno = controllerServidor.titularAdicionado(parte[1], parte[2]);
                            paraCliente.writeObject(retorno);
                            System.out.println(socketCliente.getInetAddress().getHostAddress() + " - Efetuou verificação de titular");
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BancoCooperativo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }
    
}