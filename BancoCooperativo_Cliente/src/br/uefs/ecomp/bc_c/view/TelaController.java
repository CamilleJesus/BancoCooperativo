/*
 * Autora: Camille Jesus
 * Componente Curricular: TEC502 - MI Concorrência e Conectividade
 * Data: 20/4/17
 */
package br.uefs.ecomp.bc_c.view;

import br.uefs.ecomp.bc_c.connection.Comunicacao;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import javax.swing.JOptionPane;


/**
 * Classe (controladora) TelaController, responsável por seu respectivo arquivo
 FXML. Apresenta os atributos e métodos da sua classe principal correspondente.
 *
 * @author Camille Jesus
 */
public class TelaController {
    
    private Comunicacao comunicacao = new Comunicacao();
    
    
    
    //Tela Login:
    @FXML
    private TitledPane telaLogin;
    @FXML
    private Pane paneLogin;    
    @FXML
    private Label labelContaLogin;    
    @FXML
    private TextField fieldContaLogin;    
    @FXML
    private Label labelSenhaLogin;    
    @FXML
    private PasswordField fieldSenhaLogin;    
    @FXML
    private Hyperlink cadastreseLogin;    
    @FXML
    private Button entrarLogin;    
        
    /** Método que fecha a tela atual e retorna à tela de login.
     * 
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarCadastrarTelaLogin(ActionEvent event) throws Exception {
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.start(new Stage());
        ((Stage) cadastreseLogin.getScene().getWindow()).close();   //Fecha a tela atual
    }
    
    /** Método que dispara o evento do botão, gerando a tela de acesso da conta,
     * se todos os campos de dados estiverem preenchidos.
     * 
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarEntrarTelaLogin(ActionEvent event) throws Exception {
        String numeroConta = fieldContaLogin.getText();
        String senha = fieldSenhaLogin.getText();
        if ((!numeroConta.equals("")) && (!senha.equals(""))) {
            String logou = comunicacao.fazerLogin(numeroConta, senha);
            
            if (logou.equals("1")) {
                String contaAtual = numeroConta;
                TelaAcesso telaAcesso = new TelaAcesso();
                TelaAcesso.setNumeroConta(contaAtual);
                telaAcesso.start(new Stage());
                ((Stage) entrarLogin.getScene().getWindow()).close();   //Fecha a tela atual
            } else {
                JOptionPane.showMessageDialog(null, "Informações inválidas!", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    
    //Tela Cadastro:
    @FXML
    private TitledPane telaCadastro;
    @FXML
    private SplitPane paneCadastro;
    @FXML
    private AnchorPane pane1Cadastro;
    @FXML
    private AnchorPane pane2Cadastro;
    @FXML
    private Label labelPessoaCadastro;
    @FXML
    private RadioButton buttonFisicaCadastro;
    @FXML
    private RadioButton buttonJuridicaCadastro;
    @FXML
    private Label labelNomeCadastro;
    @FXML
    private TextField fieldNomeCadastro;
    @FXML
    private Label labelCpfCadastro;
    @FXML
    private Label labelCnpjCadastro;
    @FXML
    private TextField fieldCpfCnpjCadastro;
    @FXML
    private Label labelContaCadastro;
    @FXML
    private RadioButton buttonPoupancaCadastro;
    @FXML
    private RadioButton buttonCorrenteCadastro;
    @FXML
    private Label labelSenhaCadastro;
    @FXML
    private PasswordField fieldSenhaCadastro;
    @FXML
    private Hyperlink voltarCadastro;
    @FXML
    private Button cadastrarCadastro;
    
    /** Método que dispara o evento de um dos dois botões, exibindo o campo de cadastro
     * correspondente ao tipo de cliente (CPF - Pessoa Física ou CNPJ - Pessoa Jurídica).
     * @param event
     */
    @FXML
    public void clicarPessoaTelaCadastro(ActionEvent event) {
        String tipoPessoa = event.getSource().toString();
        
        if (tipoPessoa.equals(buttonFisicaCadastro.toString())) {
            buttonJuridicaCadastro.setSelected(false);
            labelCpfCadastro.setVisible(true);
            labelCnpjCadastro.setVisible(false);
        } else if (tipoPessoa.equals(buttonJuridicaCadastro.toString())) {
            buttonFisicaCadastro.setSelected(false);
            labelCpfCadastro.setVisible(false);
            labelCnpjCadastro.setVisible(true);
        }
    }

    /** Método que dispara o evento de um dos dois botões correspondente ao tipo
     * de conta (Poupança ou Corrente).
     * @param event
     */
    @FXML
    public void clicarContaTelaCadastro(ActionEvent event) {
        String tipoConta = event.getSource().toString();
        
        if (tipoConta.equals(buttonPoupancaCadastro.toString())) {
            buttonCorrenteCadastro.setSelected(false);
        } else if (tipoConta.equals(buttonCorrenteCadastro.toString())) {
            buttonPoupancaCadastro.setSelected(false);
        }
    }

    /** Método que dispara o evento do botão, gerando a tela login, se todos os 
     * campos de dados estiverem preenchidos.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarCadastrarTelaCadastro(ActionEvent event) throws Exception {
        String nomeC = fieldNomeCadastro.getText();
        String tipoCliente = "";
        
        //Verifica o tipo de cliente:
        if (buttonFisicaCadastro.isSelected()) {
            tipoCliente = "1";
        } else if (buttonJuridicaCadastro.isSelected()) {
            tipoCliente = "2";
        }
        String cpfCnpj = fieldCpfCnpjCadastro.getText();        
        String tipoConta = "";
        
        //Verifica o tipo de conta:
        if (buttonPoupancaCadastro.isSelected()) {
            tipoConta = "1";
        } else if (buttonCorrenteCadastro.isSelected()) {
            tipoConta = "2";
        }
        String senha = fieldSenhaCadastro.getText();
        
        //Verifica os campos:
        if ((!nomeC.equals("")) && (!tipoCliente.equals("")) && (!cpfCnpj.equals(""))
                && (!tipoConta.equals("")) && (!senha.equals(""))) {
            comunicacao.cadastrarCliente(nomeC, tipoCliente, cpfCnpj);
            String contaAtual = comunicacao.cadastrarConta(tipoConta, senha);
            comunicacao.adicionarTitular(cpfCnpj, contaAtual);
            JOptionPane.showMessageDialog(null, "Conta criada!\nNúmero: " + contaAtual + ".", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
            this.telaLoginTelaCadastro();   //Chama a tela de login
        } else {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Método que dispara o evento do botão, voltando à tela de login.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarVoltarTelaCadastro(ActionEvent event) throws Exception {
        this.telaLoginTelaCadastro();
    }
    
    /** Método que fecha a tela atual e retorna à tela de login.
     * @throws java.lang.Exception
     */
    public void telaLoginTelaCadastro() throws Exception {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(new Stage());
        ((Stage) cadastrarCadastro.getScene().getWindow()).close();   //Fecha a tela atual
    }    
    
    
    
    //Tela Acesso:
    @FXML
    private TitledPane telaAcesso;
    @FXML
    private SplitPane paneAcesso;
    @FXML
    private AnchorPane pane1Acesso;
    @FXML
    private AnchorPane pane2Acesso;
    @FXML
    private Label bemvindoAcesso;
    @FXML
    private Button buttonVerACesso;
    @FXML
    private Label labelTipoAcesso;
    @FXML
    private Label tipoContaAcesso;
    @FXML
    private Label tipoClientesAcesso;
    @FXML
    private Label labelNumeroContaAcesso;
    @FXML
    private Label numeroContaAcesso;
    @FXML
    private Label labelTitularesAcesso;
    @FXML
    private TextArea titularesAcesso;
    @FXML
    private Label labelSaldoAcesso;
    @FXML
    private Label saldoAcesso;
    @FXML
    private Button buttonDepositoAcesso;
    @FXML
    private Button buttonTransferenciaAcesso;
    @FXML
    private Button buttonTitularAcesso;
    @FXML
    private Hyperlink linkSairAcesso;
    private String contaAtual;
    
    /** Método que dispara o evento do botão, gerando a tela de depósito.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarDepositoTelaAcesso(ActionEvent event) throws Exception {
        TelaDeposito telaDeposito = new TelaDeposito();
        telaDeposito.start(new Stage());
    }

    /** Método que dispara o evento do botão, gerando a tela de transferência.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarTransferenciaTelaAcesso(ActionEvent event) throws Exception {
        TelaTransferencia telaTransferencia = new TelaTransferencia();
        TelaTransferencia.setNumeroConta(contaAtual);   //Informa à tela a conta logada
        telaTransferencia.start(new Stage());
    }

    /** Método que dispara o evento do botão, gerando a tela de adição de titular.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarTitularTelaAcesso(ActionEvent event) throws Exception {
        TelaTitular telaTitular = new TelaTitular();
        TelaTitular.setNumeroConta(contaAtual);   //Informa à tela a conta logada
        telaTitular.start(new Stage());
    }

    /** Método que dispara o evento do botão, voltando a tela inicial de login.
     * @param event
     * @throws java.lang.Exception
     */
    @FXML
    public void clicarSairTelaAcesso(ActionEvent event) throws Exception {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.start(new Stage());
        ((Stage) linkSairAcesso.getScene().getWindow()).close();   //Fecha a tela atual
        comunicacao.sair();
    }

    /** Método que prepara a janela, exibindo as informações da conta.
     */
    public void initialize(ActionEvent event) {
        titularesAcesso.setEditable(false);
        
        TelaAcesso telaAcesso = new TelaAcesso();
        contaAtual = telaAcesso.getNumeroConta();
        String infoConta = comunicacao.exibirInfoGeral(contaAtual);
        
        String parte[] = infoConta.split(":");
        String titularesS = parte[2].replace("-", "  ");
        
        tipoContaAcesso.setText("Conta " + parte[0]);
        tipoClientesAcesso.setText("Pessoa " + parte[1]);
        numeroContaAcesso.setText(contaAtual);
        titularesAcesso.setText(titularesS);
        String saldoAtual = comunicacao.verificarSaldo(contaAtual);
        saldoAcesso.setText("R$ "+ saldoAtual);
    }
    
    
    
    //Tela Depósito:
    @FXML
    private TitledPane telaDeposito;
    @FXML
    private AnchorPane paneDeposito;
    @FXML
    private TextField fieldNumeroContaDeposito;
    @FXML
    private TextField fieldValorDeposito;
    @FXML
    private Button buttonOkDeposito;
    @FXML
    private Hyperlink linkSairDeposito;
    
    /** Método que dispara o evento do botão, realizando o depósito, se todos os 
     * campos de dados estiverem preenchidos.
     * 
     * @param event
     */
    @FXML
    public void clicarDepositarTelaDeposito(ActionEvent event) {
        String numeroContaS = fieldNumeroContaDeposito.getText();
        String valor = fieldValorDeposito.getText();
        
        if ((!numeroContaS.equals("")) && (!valor.equals(""))) {
            
            if (comunicacao.contaExiste(numeroContaS).equals("1")) {
                comunicacao.depositar(numeroContaS, valor);
                JOptionPane.showMessageDialog(null, "Depósito realizado!", null, JOptionPane.INFORMATION_MESSAGE);
                this.telaAcessoTelaDeposito();
            } else {
                JOptionPane.showMessageDialog(null, "Conta inexistente!", "Atenção!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Método que dispara o evento do botão, voltando à tela de acesso.
     * 
     * @param event
     */
    @FXML
    public void clicarSairTelaDeposito(ActionEvent event) {
        this.telaAcessoTelaDeposito();
    }
    
    /** Método que fecha a tela atual, exibindo a tela de acesso que estava em
     * segundo plano.
     */
    public void telaAcessoTelaDeposito() {
        ((Stage) linkSairDeposito.getScene().getWindow()).hide();
    }
    
    
    
    //Tela Transferência:
    @FXML
    private TitledPane telaTranferencia;
    @FXML
    private AnchorPane paneTranferencia;
    @FXML
    private TextField fieldNumeroContaTranferencia;
    @FXML
    private TextField fieldValorTranferencia;
    @FXML
    private Button buttonOkTranferencia;
    @FXML
    private Hyperlink linkSairTranferencia;
    
    /** Método que dispara o evento do botão, realizando a tranferência, se todos os 
     * campos de dados estiverem preenchidos.
     * 
     * @param event
     */
    @FXML
    public void clicarTransferirTelaTranferencia(ActionEvent event) {
        String numeroContaS = fieldNumeroContaTranferencia.getText();
        String valor = fieldValorTranferencia.getText();
        
        if ((!numeroContaS.equals("")) && (!valor.equals(""))) {
            
            if (comunicacao.contaExiste(numeroContaS).equals("1")) {
                
                if (Double.parseDouble(comunicacao.verificarSaldo(TelaAcesso.getNumeroConta())) >= Double.parseDouble(valor)) {
                    comunicacao.transferir(TelaAcesso.getNumeroConta(), numeroContaS, valor);
                    JOptionPane.showMessageDialog(null, "Tranferência realizada!", null, JOptionPane.INFORMATION_MESSAGE);
                    this.telaAcessoTelaTranferencia();
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "Atenção!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conta inexistente!", "Atenção!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Método que dispara o evento do botão, voltando à tela de acesso.
     * 
     * @param event
     */
    @FXML
    public void clicarSairTelaTranferencia(ActionEvent event) {
        this.telaAcessoTelaTranferencia();
    }
    
    /** Método que fecha a tela atual, exibindo a tela de acesso que estava em
     * segundo plano.
     */
    public void telaAcessoTelaTranferencia() {
        ((Stage) linkSairTranferencia.getScene().getWindow()).hide();
    }
    
    
    
    //Tela Titular:
    @FXML
    private TitledPane telaTitular;
    @FXML
    private AnchorPane paneTitular;
    @FXML
    private TextField fieldNomeTitular;
    @FXML
    private TextField fieldCpfTitular;
    @FXML
    private Button buttonOkTitular;
    @FXML
    private Hyperlink linkSairTitular;
    
    /** Método que dispara o evento do botão, realizando a adição de titular, se
     * todos os campos de dados estiverem preenchidos.
     * 
     * @param event
     */
    @FXML
    public void clicarAdicionarTelaTitular(ActionEvent event) {
        String nomeC = fieldNomeTitular.getText();
        String cpf = fieldCpfTitular.getText();
        
        if ((!nomeC.equals("")) && (!cpf.equals(""))) {
            
            if ((comunicacao.titularAdicionado(TelaAcesso.getNumeroConta(), cpf)).equals("0")) {
                comunicacao.adicionarTitular(nomeC, cpf, TelaAcesso.getNumeroConta());
                JOptionPane.showMessageDialog(null, "Titular adicionado!", null, JOptionPane.INFORMATION_MESSAGE);
                this.telaAcessoTelaTitular();
            } else {
                JOptionPane.showMessageDialog(null, "Titular já adicionado!", "Atenção!", JOptionPane.ERROR_MESSAGE);
            }            
        } else {
            JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Método que dispara o evento do botão, voltando à tela de acesso.
     * 
     * @param event
     */
    @FXML
    public void clicarSairTelaTitular(ActionEvent event) {
        this.telaAcessoTelaTitular();
    }
    
    /** Método que fecha a tela atual, exibindo a tela de acesso que estava em
     * segundo plano.
     */
    public void telaAcessoTelaTitular() {
        ((Stage) linkSairTitular.getScene().getWindow()).hide();
    }
    
}