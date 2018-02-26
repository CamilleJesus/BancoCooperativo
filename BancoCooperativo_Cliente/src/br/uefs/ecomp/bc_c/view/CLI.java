/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_c.view;

import br.uefs.ecomp.bc_c.connection.Comunicacao;

import java.io.IOException;
import java.util.Scanner;


/**
 * Classe CLI (Command-Line Interface), ou Interface por Linha de Comando, tem
 * como característica principal a interação com o usuário, o menu é mostrado e
 * o usuário entra com os dados necessários para o correto funcionamento do programa.
 * 
 * @author Camille Jesus
 */
public class CLI {
    
    private static Comunicacao comunicacao = new Comunicacao();
    
    /** O metódo main é o ponto de partida do programa. O menu é apresentado e a
     * leitura dos dados pelo teclado é feita a partir dos métodos do Scanner.
     * 
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
                
        System.out.println("::.  Banco Cooperativo  .::");
        
        /* Menu descritivo:
         */
        do {
            System.out.println("\n [1] - Cadastrar Pessoa Física\n [2] - Cadastrar Pessoa Jurídica\n "
                    + "[3] - Fazer Login\n [0] - Sair");
            int escolha = scanner.nextInt();
            String nomeC;
            String cpfCnpj;
            String senha;
            String contaAtual;
            String logou;
            String valor;
            
            switch (escolha) {
                case 0:   //Opção Sair
                    System.exit(0);
                    break;
                case 1:   //Opção Cadastro de Pessoa Física
                    scanner.nextLine();
                    System.out.println("\nNome Completo: ");
                    nomeC = scanner.nextLine();
                    System.out.println("CPF: ");
                    cpfCnpj = scanner.nextLine();
                    
                    comunicacao.cadastrarCliente(nomeC, "1", cpfCnpj);
                    
                    System.out.println("\n [1] - Cadastrar Conta Poupança\n [2] - Cadastrar Conta Corrente");
                    int escolha2 = scanner.nextInt();
                    
                    switch (escolha2) {
                        case 1:   //Opção Cadastro de Pessoa Física + Cadastro de Conta Poupança
                            scanner.nextLine();
                            System.out.println("\nSenha: ");
                            senha = scanner.nextLine();
                            
                            contaAtual = comunicacao.cadastrarConta("1", senha);
                            break;
                        case 2:   //Opção Cadastro de Pessoa Física + Cadastro de Conta Corrente
                            scanner.nextLine();
                            System.out.println("\nSenha: ");
                            senha = scanner.nextLine();
                            
                            contaAtual = comunicacao.cadastrarConta("2", senha);
                            break;
                        default:   //Qualquer outra opção
                            contaAtual = null;
                            System.out.println("Opção Inválida.");
                            break;
                    }
                    //Estabelece relação entre o titular e a conta:
                    comunicacao.adicionarTitular(cpfCnpj, contaAtual);
                    System.out.println("\nNúmero da Conta: " + contaAtual);
                    break;                    
                case 2:   //Opção Cadastro de Pessoa Jurídica
                    scanner.nextLine();
                    System.out.println("\nNome Completo: ");
                    nomeC = scanner.nextLine();
                    System.out.println("CNPJ: ");
                    cpfCnpj = scanner.nextLine();
                    
                    comunicacao.cadastrarCliente(nomeC, "2", cpfCnpj);
                    
                    System.out.println("\n [1] - Cadastrar Conta Poupança\n [2] - Cadastrar Conta Corrente");
                    int escolha3 = scanner.nextInt();
                    
                    switch (escolha3) {
                        case 1:   //Opção Cadastro de Pessoa Jurídica + Cadastro de Conta Poupança
                            scanner.nextLine();
                            System.out.println("\nSenha: ");
                            senha = scanner.nextLine();
                            
                            contaAtual = comunicacao.cadastrarConta("1", senha);
                            break;                            
                        case 2:   //Opção Cadastro de Pessoa Jurídica + Cadastro de Conta Corrente
                            scanner.nextLine();
                            System.out.println("\nSenha: ");
                            senha = scanner.nextLine();
                            
                            contaAtual = comunicacao.cadastrarConta("2", senha);
                            break;                            
                        default:   //Qualquer outra opção
                            contaAtual = null;
                            System.out.println("Opção Inválida.");
                            break;
                    }
                    //Estabelece relação entre o titular e a conta:
                    comunicacao.adicionarTitular(cpfCnpj, contaAtual);
                    System.out.println("\nNúmero da Conta: " + contaAtual);
                    break;
                
                case 3:   //Opção login na conta
                    scanner.nextLine();
                    System.out.println("\nNúmero da conta: ");
                    String numeroConta = scanner.nextLine();
                    System.out.println("Senha: ");
                    senha = scanner.nextLine();
                    logou = comunicacao.fazerLogin(numeroConta, senha);
                    
                    if (logou.equals("1")) {   //Fez login
                        contaAtual = numeroConta;
                        int escolha4;
                        
                        do {
                            System.out.println("\n [1] - Depósito\n [2] - Transferência\n [3] - Adicionar Titular\n [4] - Verificar Saldo\n [0] - Sair");
                            escolha4 = scanner.nextInt();

                            switch (escolha4) {
                                case 0:   //Logout
                                    break;                        
                                case 1:   //Opção de Depósito
                                    scanner.nextLine();
                                    System.out.println("\nNúmero da conta: ");
                                    numeroConta = scanner.nextLine();
                                    System.out.println("Valor: ");
                                    valor = scanner.nextLine();

                                    comunicacao.depositar(numeroConta, valor);
                                    break;                        
                                case 2:   //Opção de Tranferência
                                    scanner.nextLine();
                                    System.out.println("\nNúmero da conta: ");
                                    numeroConta = scanner.nextLine();
                                    System.out.println("Valor: ");
                                    valor = scanner.nextLine();

                                    comunicacao.transferir(contaAtual, numeroConta, valor);
                                    break;    
                                case 3:   //Opção Adição de Titular
                                    scanner.nextLine();
                                    System.out.println("\nNome completo: ");
                                    nomeC = scanner.nextLine();
                                    System.out.println("CPF: ");
                                    cpfCnpj = scanner.nextLine();

                                    comunicacao.adicionarTitular(nomeC, cpfCnpj, contaAtual);
                                    break;
                                case 4:   //Opção de Verificação de Saldo
                                    System.out.println("Saldo: " + comunicacao.verificarSaldo(contaAtual));
                                    break;
                                default:   //Qualquer outra opção
                                    System.out.println("Opção Inválida.");
                                    break;
                            }
                        } while (escolha4 != 0);
                    } else {   //Falha no login
                        System.out.println("Número da conta ou senha inválidos.");
                    }
            }
        } while(true);    
    }
    
}