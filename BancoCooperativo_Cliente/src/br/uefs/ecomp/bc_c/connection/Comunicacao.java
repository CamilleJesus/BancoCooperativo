/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_c.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Comunicacao, responsável por estabelecer um protocolo de comunicação entre
 * as aplicações do Cliente e do Servidor. As requisições são gravadas num pacote
 * (strings concatenadas) juntamente com as informações necessárias e enviadas para
 * o servidor. A mensagem de retorno é ouvida e retornada (para a interface de usuário).
 * 
 * @author Camille Jesus
 */
public class Comunicacao  {
    
    private Socket socketCliente;
    private ObjectOutputStream paraServidor;
    private ObjectInputStream doServidor;
    private String requisicao, pacote, retorno;
    private static String ip;   //Atributo estático referente ao IP do Servidor
            
    /** Construtor da classe Comunicacao, cria o socket de conexão e inicializa os
     * objetos de escrita e leitura do cliente em relação ao servidor.
     */
    public Comunicacao() {
        
        try {
            socketCliente = new Socket(ip, 27279);   //Informa IP e porta do Servidor
            this.paraServidor = new ObjectOutputStream(socketCliente.getOutputStream());
            this.doServidor = new ObjectInputStream(socketCliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de saída de um cliente no Banco.
     *
     */
    public void sair() {
        requisicao = "0";
        pacote = requisicao;
        
        try {
            paraServidor.writeObject(pacote);
            socketCliente.close();
            paraServidor.close();
            doServidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de cadastro de um cliente no Banco.
     *
     * @param nomeC
     * @param tipoCliente
     * @param cpfCnpj
     */
    public void cadastrarCliente(String nomeC, String tipoCliente, String cpfCnpj) {
        requisicao = "1";
        pacote = requisicao + ":" + nomeC + ":" + tipoCliente + ":" + cpfCnpj;
        
        try {
            paraServidor.writeObject(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de cadastro de uma conta no Banco.
     *
     * @param tipoConta
     * @param senha
     * @return numeroConta
     */
    public String cadastrarConta(String tipoConta, String senha) {
        
        try {
            requisicao = "2";
            pacote = requisicao + ":" + tipoConta + ":" + senha;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /** Requisição de vinculação de titular cadastrado à conta no Banco.
     *
     * @param cpfCnpj
     * @param numeroConta
     */
    public void adicionarTitular(String cpfCnpj, String numeroConta) {
        requisicao = "3";
        pacote = requisicao + ":" + cpfCnpj + ":" + numeroConta;
        
        try {
            paraServidor.writeObject(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de login em conta no Banco.
     *
     * @param numeroConta
     * @param senha
     * @return true (1)/false (0)
     */
    public String fazerLogin(String numeroConta, String senha) {
        
        try {
            requisicao = "4";
            pacote = requisicao + ":" + numeroConta + ":" + senha;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /** Requisição de depósito numa conta no Banco.
     *
     * @param numeroConta
     * @param valor
     */
    public void depositar(String numeroConta, String valor) {
        requisicao = "5";
        pacote = requisicao + ":" + numeroConta + ":" + valor;
        
        try {
            paraServidor.writeObject(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de transferência da conta atual (logada) para outra conta no
     * Banco.
     *
     * @param contaAtual
     * @param numeroConta
     * @param valor
     */
    public void transferir(String contaAtual, String numeroConta, String valor) {
        requisicao = "6";
        pacote = requisicao + ":" + contaAtual + ":" + numeroConta + ":" + valor;
        
        try {
            paraServidor.writeObject(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Requisição de vinculação de outros titulares à uma conta previamente
     * cadastrada no Banco.
     *
     * @param nomeC
     * @param cpfCnpj
     * @param numeroConta
     */
    public void adicionarTitular(String nomeC, String cpfCnpj, String numeroConta) {
        requisicao = "7";
        pacote = requisicao + ":" + nomeC + ":" + cpfCnpj + ":" + numeroConta;
        
        try {
            paraServidor.writeObject(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Requisição de exibição de saldo de uma conta no Banco.
     *
     * @param numeroConta
     * @return saldo
     */
    public String verificarSaldo(String numeroConta) {
        
        try {
            requisicao = "8";
            pacote = requisicao + ":" + numeroConta;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /** Requisição de exibição de informações adicionais de uma conta no Banco.
     *
     * @param numeroConta
     * @return tipo de conta, tipo de cliente e titulares   //numa só string
     */
    public String exibirInfoGeral(String numeroConta) {
        
        try {
            requisicao = "9";
            pacote = requisicao + ":" + numeroConta;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
            
    /** Requisição de verificação de existência de uma conta no Banco.
     *
     * @param numeroConta
     * @return true (1)/false (0)
     */
    public String contaExiste(String numeroConta) {
        
        try {
            requisicao = "10";
            pacote = requisicao + ":" + numeroConta;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /** Requisição de verificação de titular para adição dele na conta.
     *
     * @param contaAtual
     * @param cpfCnpj
     * @return true (1)/false (0)
     */
    public String titularAdicionado(String contaAtual, String cpfCnpj) {
        
        try {
            requisicao = "11";
            pacote = requisicao + ":" + contaAtual + ":" + cpfCnpj;
            paraServidor.writeObject(pacote);
            retorno = ((String) doServidor.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /** Método que retorna o IP do Servidor.
     * 
     * @return IP
     */
    public static String getIp() {
        return ip;
    }

    /** Método que altera o IP do Servidor.
     * 
     * @param ip 
     */
    public static void setIp(String ip) {
        Comunicacao.ip = ip;
    }
    
}