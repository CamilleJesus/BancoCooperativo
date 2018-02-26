/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_s.controller;

import br.uefs.ecomp.bc_s.data.Dados;
import br.uefs.ecomp.bc_s.model.Cliente;
import br.uefs.ecomp.bc_s.model.Conta;

import java.util.ArrayList;


/**
 * A classe ControllerServidor, como o nome sugere, é o controlador, ele é responsável
 por intermediar a comunicação entre os componentes do sistema, a interação entre
 o model (Cliente e Conta) e a parte de conexão.
 * 
 * @author Camille Jesus
 */
public class ControllerServidor {
    
    //Atributos estáticos para que todas as instâncias possam acessar:
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Conta> contas;
    //Atributo para manipulação dos dados do sistema em arquivo:
    private Dados dados = new Dados();
    
    /**Construtor da classe, recebe como parâmetro as informações para inicializar
     * os atributos e instanciar um novo objeto.
     */
    public ControllerServidor() {
        clientes = new ArrayList<>();
        contas = new ArrayList<>();
    }
    
    /** Método para cadastrar um cliente, ele recebe todas as suas informações, 
     * sua referência é salva ao final de uma lista.
     * 
     * @param nomeCompleto
     * @param tipoCliente 
     * @param cpfCnpj 
     */
    public void cadastrarCliente(String nomeCompleto, String tipoCliente, String cpfCnpj) {
        boolean juridica;
        
        if (tipoCliente.equals("1")) {
            juridica = false;
        } else {
            juridica = true;
        }
        clientes.add(new Cliente(nomeCompleto, juridica, cpfCnpj));
        this.salvarClientesSistema();   //A cada adição de cliente salva os dados em arquivo.
    }
    
    /** Método para busca de cliente, recebe como parâmetro seu identificador (CPF/CNPJ).
     * A lista de clientes é percorrida até que algum tenha o parâmetro desejado
     * ou ela chegue ao fim, ao ser encontrado, sua referência é retornada, caso
     * contrário retorna nulo.
     * 
     * @param cpfCnpj 
     * @return cliente
     */
    public Cliente buscarCliente(String cpfCnpj) {
        
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            
            if (cpfCnpj.equals(cliente.getCpfCnpj())) {
                return cliente;
            }
        }
        return null;
    }
    
    /** Método para cadastrar uma conta, ele recebe todas as suas informações, 
     * sua referência é salva ao final de uma lista.
     * 
     * @param tipoConta 
     * @param senha 
     * @return  numeroConta
     */
    public String cadastrarConta(String tipoConta, String senha) {         
        boolean corrente;
        
        if (tipoConta.equals("1")) {
            corrente = false;
        } else {
            corrente = true;
        }
        Conta conta = new Conta(corrente, senha);
        contas.add(conta);
        this.salvarContasSistema();   //A cada adição de conta salva os dados em arquivo.        
        return (Integer.toString(conta.getNumeroConta()));
    }
    
    /** Método para busca de conta, recebe como parâmetro seu número da conta.
     * A lista de contas é percorrida até que alguma tenha o parâmetro desejado
     * ou ela chegue ao fim, ao ser encontrada, sua referência é retornada, caso
     * contrário retorna nulo.
     * 
     * @param numeroConta 
     * @return conta
     */
    public Conta buscarConta(int numeroConta) {
        
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);
            
            if (numeroConta == conta.getNumeroConta()) {
                return conta;
            }
        }
        return null;  
    }
    
    /** Método que vincula um cliente a uma conta, tornando-o titular da mesma no
     * momento de cadastro.
     * 
     * @param cpfCnpj
     * @param numeroConta 
     */
    public void adicionarTitular(String cpfCnpj, String numeroConta) {
        this.buscarConta(Integer.parseInt(numeroConta)).addTitular(this.buscarCliente(cpfCnpj));
        this.salvarContasSistema();   //A cada vinculação entre cliente e conta salva os dados em arquivo.
    }
    
    /** Método que valida as informações de login de usuário para verificação de
     * existência no sistema.
     * 
     * @param numeroConta
     * @param senha
     * @return true (1)/false (0)
     */
    public String fazerLogin(String numeroConta, String senha) {
        
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = (Conta) contas.get(i);
            
            if ((Integer.parseInt(numeroConta) == conta.getNumeroConta()) && (senha.equals(conta.getSenha()))) {
                return "1";   //Caso exista
            }
        }
        return "0";   //Caso não exista
    }
    
    /** Método que adiciona valor ao saldo de uma conta, ou seja, permite a realização
     * de depósito.
     * 
     * @param numeroConta
     * @param valor 
     */
    public void depositar(String numeroConta, String valor) {
        Conta conta = this.buscarConta(Integer.parseInt(numeroConta));
        conta.setSaldo(conta.getSaldo() + Double.parseDouble(valor));
        this.salvarContasSistema();
    }
    
    /** Método que permite a troca de valor entre saldos de uma conta, ou seja,
     * permite a realização de transferência.
     * 
     * @param contaAtual
     * @param numeroConta
     * @param valor 
     */
    public void transferir(String contaAtual, String numeroConta, String valor) {
        Conta conta = this.buscarConta(Integer.parseInt(contaAtual));
        conta.setSaldo(conta.getSaldo() - Double.parseDouble(valor));
        Conta conta2 = this.buscarConta(Integer.parseInt(numeroConta));
        conta2.setSaldo(conta2.getSaldo() + Double.parseDouble(valor));
        this.salvarContasSistema();
    }
    
    /** Método que vincula um cliente a uma conta, tornando-o titular da mesma,
     * em tempo de acesso.
     * 
     * @param nomeC
     * @param cpfCnpj
     * @param numeroConta 
     */
    public void adicionarTitular(String nomeC, String cpfCnpj, String numeroConta) {
        Conta conta = this.buscarConta(Integer.parseInt(numeroConta));
        boolean juridica = conta.getTitulares().get(0).isJuridica();
        Cliente cliente = new Cliente(nomeC, juridica, cpfCnpj);
        conta.addTitular(cliente);
        this.salvarClientesSistema();
        this.salvarContasSistema();
    }
    
    /** Método que permite a visualização de saldo atual da conta.
     * 
     * @param numeroConta
     * @return saldo
     */
    public String verificarSaldo(String numeroConta) {
        return (this.buscarConta(Integer.parseInt(numeroConta)).getSaldo().toString());
    }
    
    /** Método que retorna as demais informações de uma conta/cliente.
     * 
     * @param numeroConta
     * @return tipo de conta, tipo de cliente e titulares
     */
    public String exibirInfoGeral(String numeroConta) {
        Conta conta = this.buscarConta(Integer.parseInt(numeroConta));
        boolean tipoConta = conta.isCorrente();
        String tipoConta2 = null;        
        Cliente cliente = conta.getTitulares().get(0);
        boolean tipoCliente = cliente.isJuridica();
        String tipoCliente2 = null;
        String titulares = "";
        
        if (tipoConta == false) {
            tipoConta2 = "Poupança";
        } else if (tipoConta == true) {
            tipoConta2 = "Corrente";
        }
        
        if (tipoCliente == false) {
            tipoCliente2 = "Física";
        } else if (tipoCliente == true) {
            tipoCliente2 = "Jurídica";
        }
        
        for (int i = 0; i < conta.getTitulares().size(); i++) {
            titulares += conta.getTitulares().get(i).getNomeCompleto() + "-";
        }
        
        return (tipoConta2 + ":" + tipoCliente2 + ":" + titulares);
    }
    
    /** Método que carrega os dados de clientes do arquivo para o sistema. 
     */
    public void carregarClientesSistema() {
        clientes = dados.carregarClientesSistema();
    }
    
    /** Método que salva os dados de clientes do sistema para o arquivo. 
     */
    public void salvarClientesSistema() {
        dados.salvarClientesSistema(clientes);
    }
    
    /** Método que carrega os dados de contas do arquivo para o sistema. 
     */
    public void carregarContasSistema() {
        contas = dados.carregarContasSistema();
    }
    
    /** Método que salva os dados de contas do sistema para o arquivo. 
     */
    public void salvarContasSistema() {
        dados.salvarContasSistema(contas);
    }
    
    /** Método que verifica a existência de uma conta.
     * 
     * @param numeroConta
     * @return true (1)/false (0)
     */
    public String contaExiste(String numeroConta) {
        
        if (this.buscarConta(Integer.parseInt(numeroConta)) == null) {
            return "0";  //Caso não exista
        } else {
            return"1";   //Caso exista
        }
    }
    
    /** Método que verifica se um titular já foi adicionado à conta.
     * 
     * @param contaAtual
     * @param cpfCnpj
     * @return true (1)/false (0)
     */
    public String titularAdicionado(String contaAtual, String cpfCnpj) {
        Conta conta = this.buscarConta(Integer.parseInt(contaAtual));        
        ArrayList<Cliente> titularesConta = conta.getTitulares();
        
        for (int i = 0; i < titularesConta.size(); i++) {
            Cliente cliente = titularesConta.get(i);
            
            if (cpfCnpj.equals(cliente.getCpfCnpj())) {
                return "1";   //Caso sim
            }
        }
        return "0";   //Caso não
    }
    
}