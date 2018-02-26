package br.uefs.ecomp.bc_s.model;

import br.uefs.ecomp.bc_s.data.Dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe Conta, permite criar conta poupança ou corrente, tem um iterador crescente
 * para gerar um número único e imutável para cada conta, possui uma senha, o saldo
 * e uma lista de titulares da mesma.
 * 
 * @author Camille Jesus
 */
public class Conta implements Serializable  {   //É serializável
        
    private boolean corrente;
    private final int numeroConta;
    private static Integer auxNumeroConta = 0;
    private String senha;
    private double saldo = 0.0;
    private ArrayList<Cliente> titulares;

    /**Construtor da classe, recebe como parâmetro as informações para inicializar
     * os atributos e instanciar um novo objeto conta.
     * 
     * @param corrente
     * @param senha
     */
    public Conta(boolean corrente, String senha) {
        this.carregaNumeroConta();   //Lê do arquivo o número a ser iterado
        this.numeroConta = ++auxNumeroConta;
        this.corrente = corrente;
        this.senha = senha;
        this.titulares = new ArrayList<>();
        this.salvaNumeroConta();   //Escreve no arquivo o número da última conta adicionada
    }

    /** Método que retorna o tipo da conta.
     * 
     * @return true/false
     */
    public boolean isCorrente() {
        return corrente;   //True, se corrente; false, se poupança.
    }
    
    /** Método que determina o tipo da conta.
     * 
     * @param corrente
     */
    public void setCorrente(boolean corrente) {
        this.corrente = corrente;
    }

    /** Método que retorna o número da conta.
     * 
     * @return numeroConta
     */
    public int getNumeroConta() {
        return numeroConta;
    }

    /** Método que retorna a senha da conta.
     * 
     * @return senha
     */
    public String getSenha() {
        return senha;
    }

    /** Método que altera a senha da conta.
     * 
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /** Método que retorna o saldo da conta.
     * 
     * @return saldo
     */
    public Double getSaldo() {
        return saldo;
    }

    /** Método altera o saldo da conta.
     * 
     * @param saldo 
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /** Método que retorna os titulares da conta.
     * 
     * @return titulares
     */
    public ArrayList<Cliente> getTitulares() {
        return titulares;
    }

    /** Método que adiciona um titular à conta.
     * 
     * @param titular
     */
    public void addTitular(Cliente titular) {
        this.titulares.add(titular);
    }
    
    /** Método que verifica se duas contas são iguais retornando um valor booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param obj
     * @return true ou false
     */
    @Override
    public boolean equals(Object obj){
        
        if(obj instanceof Conta) {
            
            if (this.numeroConta == ((Conta) obj).getNumeroConta()) {
                return true;
            }
        }
        return false;
    }
    
    /** Método que lê um número do arquivo.
     * 
     * @return numeroConta
     */
    private void carregaNumeroConta() {
                
        try {
             
            if (new File("numeroConta.data").exists()) {
                //Carrega o arquivo:
                FileInputStream file = new FileInputStream("numeroConta.data");
                //Classe responsavel por recuperar os objetos do arquivo:
                ObjectInputStream obj = new ObjectInputStream(file);
                //Grava o arquivo no objeto assistente:
                Conta.auxNumeroConta = ((Integer) (obj.readObject()));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (Conta.auxNumeroConta == null) {
            Conta.auxNumeroConta = 0;
        }
    }
    
    /** Método grava um número no arquivo.
     */
    private void salvaNumeroConta() {
        
        try {
            //Gera o arquivo para armazenar o objeto:
            FileOutputStream file = new FileOutputStream("numeroConta.data");
            //Classe responsavel por inserir os objetos:
            ObjectOutputStream obj = new ObjectOutputStream(file);
            //Grava o objeto assistente no arquivo:
            obj.writeObject(Conta.auxNumeroConta);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}