/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_s.data;

import br.uefs.ecomp.bc_s.model.Cliente;
import br.uefs.ecomp.bc_s.model.Conta;

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
 * Classe Dados, permite a persistência de dados do sistema através da leitura e
 * gravação de objetos serializáveis em arquivos tipo DATA.
 * 
 * @author Camille Jesus
 */
public class Dados implements Serializable {
    
    /** Os dados dos clientes são carregados para o sistema.
     *     
     * @return clientes
     */   
    public ArrayList<Cliente> carregarClientesSistema()  {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
             
            if (new File("clientes.data").exists()) {
                //Carrega o arquivo:
                FileInputStream file = new FileInputStream("clientes.data");
                //Classe responsavel por recuperar os objetos do arquivo:
                ObjectInputStream obj = new ObjectInputStream(file);
                //Grava o arquivo no objeto assistente:
                clientes = ((ArrayList) obj.readObject());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    /** Os dados dos clientes são salvos em arquivo.
     *     
     * @param clientes
     */ 
    public void salvarClientesSistema(ArrayList<Cliente> clientes) {
        
        try {
            //Gera o arquivo para armazenar o objeto:
            FileOutputStream file = new FileOutputStream("clientes.data");
            //Classe responsavel por inserir os objetos:
            ObjectOutputStream obj = new ObjectOutputStream(file);
            //Grava o objeto assistente no arquivo:
            obj.writeObject(clientes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Os dados das contas são carregados para o sistema.
     *     
     * @return contas
     */    
    public ArrayList<Conta> carregarContasSistema() {
        ArrayList<Conta> contas = new ArrayList<>();
        
        try {
            if (new File("contas.data").exists()) {
                //Carrega o arquivo:
                FileInputStream file = new FileInputStream("contas.data");
                //Classe responsavel por recuperar os objetos do arquivo:
                ObjectInputStream obj = new ObjectInputStream(file);
                //Grava o arquivo no objeto assistente:
                contas = ((ArrayList) obj.readObject());
            }          
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contas;
    }

    /** Os dados das contas são salvos em arquivo.
     *     
     * @param contas
     */ 
    public void salvarContasSistema(ArrayList<Conta> contas) {
        
        try {
            //Gera o arquivo para armazenar o objeto:
            FileOutputStream file = new FileOutputStream("contas.data");
            //Classe responsavel por inserir os objetos:
            ObjectOutputStream obj = new ObjectOutputStream(file);
            //Grava o objeto assistente no arquivo:
            obj.writeObject(contas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}