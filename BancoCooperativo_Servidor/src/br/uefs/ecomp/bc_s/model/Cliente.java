package br.uefs.ecomp.bc_s.model;

import java.io.Serializable;


/**
 * Classe Cliente, permite criar cliente físico ou jurídico, possui um nome e um
 * atributo identificador (CPF/CNPJ).
 * 
 * @author Camille Jesus
 */
public class Cliente implements Serializable {   //É serializável
    
    private String nomeCompleto;
    private boolean juridica;
    private String cpfCnpj;

    /** Construtor da classe, recebe como parâmetro as informações para inicializar
     * os atributos e instanciar um novo objeto cliente.
     * 
     * @param nomeCompleto 
     * @param juridica
     * @param cpfCnpj 
     */
    public Cliente(String nomeCompleto, boolean juridica, String cpfCnpj) {
        this.nomeCompleto = nomeCompleto;
        this.juridica = juridica;
        this.cpfCnpj = cpfCnpj;
    }

    /** Método que retorna o nome do cliente.
     * 
     * @return nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /** Método que altera o nome do cliente.
     * 
     * @param nomeCompleto 
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    /** Método que retorna o tipo do cliente.
     * 
     * @return true/false
     */
    public boolean isJuridica() {
        return juridica;   //True, se jurídico; false, se físico.
    }

    /** Método que altera o tipo do cliente.
     * 
     * @param juridica  
     */
    public void setJuridica(boolean juridica) {
        this.juridica = juridica;
    }

    /** Método que retorna o CPF/CNPJ do cliente.
     * 
     * @return CPF/CNPJ
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /** Método que altera o CPF/CNPJ do cliente.
     * 
     * @param cpfCnpj 
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
    
    /** Método que verifica se dois clientes são iguais retornando um valor booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param obj
     * @return true ou false
     */
    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof Cliente) {
            
            if (this.cpfCnpj.equals(((Cliente) obj).getCpfCnpj())) {
                return true;
            }
        }
        return false;
    }
    
}