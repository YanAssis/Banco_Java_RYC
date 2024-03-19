package banco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class Banco {
    static Object[] pessoas = { "Cliente físico", "Cliente jurídico" };
    static Object selectedPerson;

    static ArrayList<PessoaFisica> contasF = new ArrayList<>();
    static ArrayList<PessoaJuridica> contasJ = new ArrayList<>();
    static double valor_saque;
    static double valor_depositado;
    static double valor_troca;
    static int indice_CLIENTE;
    static int indice_pagamento;
    static int continuar = 0;
    static int atendimentos = 0;
    static boolean atendimento = true;

    public static void login_Conta() {
        indice_CLIENTE = -1;

        @SuppressWarnings("rawtypes")
        ArrayList contasLogin;
        if (selectedPerson == pessoas[0])
            contasLogin = contasF; // PF
        else
            contasLogin = contasJ; // PJ

        int login = JOptionPane.showConfirmDialog(null, "Já possui conta?", "Selecione uma opção:",
                JOptionPane.YES_NO_OPTION);
        if (login == 0) {
            String clienteNovoVelhoNome = JOptionPane.showInputDialog("Qual o nome? ");
            String clienteNovoVelhoSenha = JOptionPane.showInputDialog("Qual a senha? ");
            for (int k = 0; k < contasLogin.size(); k++) {
                if ((((Conta) contasLogin.get(k)).getNome().equals(clienteNovoVelhoNome)) &&
                        (((Conta) contasLogin.get(k)).getSenha().equals(clienteNovoVelhoSenha))) {
                    JOptionPane.showMessageDialog(null, "Conta confirmada!");
                    indice_CLIENTE = k;
                }
            }
            if (indice_CLIENTE == -1)
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
        } else {
            newconta();
            indice_CLIENTE = contasLogin.size() - 1;
        }
    }

    public static void newconta() {
        String clienteNome;
        String clienteSenha;
        String cpf_cnpj;

        do {
            clienteNome = JOptionPane.showInputDialog("Qual é o nome? ");
            if ("".equals(clienteNome)) {
                JOptionPane.showMessageDialog(null, "O nome não pode estar vazio!");
            }
        } while ("".equals(clienteNome));

        if (clienteNome == null) {
            JOptionPane.showMessageDialog(null, " Criação cancelada");
        } else {

            if (selectedPerson == pessoas[0]) {
                do {
                    cpf_cnpj = JOptionPane.showInputDialog("Qual é o CPF? ");
                    if ("".equals(cpf_cnpj)) {
                        JOptionPane.showMessageDialog(null, "O CPF não pode estar vazio!");
                    }
                } while ("".equals(cpf_cnpj));
            } else {
                do {
                    cpf_cnpj = JOptionPane.showInputDialog("Qual é o CNPJ? ");
                    if ("".equals(cpf_cnpj)) {
                        JOptionPane.showMessageDialog(null, "O CNPJ não pode estar vazio!");
                    }
                } while ("".equals(cpf_cnpj));
            }

            if (cpf_cnpj == null) {
                JOptionPane.showMessageDialog(null, " Criação cancelada");
            } else {
                do {
                    clienteSenha = JOptionPane.showInputDialog("Qual é a senha? ");
                    if ("".equals(clienteSenha)) {
                        JOptionPane.showMessageDialog(null, "A senha não pode estar vazia!");
                    }
                } while ("".equals(clienteSenha));
                if (clienteSenha == null) {
                    JOptionPane.showMessageDialog(null, " Criação cancelada");
                } else {

                    if (selectedPerson == pessoas[0])
                        contasF.add(new PessoaFisica(cpf_cnpj, clienteSenha, clienteNome, "", "", 0.0, 0.0));
                    else
                        contasJ.add(new PessoaJuridica(cpf_cnpj, clienteSenha, clienteNome, "", "", 0.0, 0.0));

                    JOptionPane.showMessageDialog(null, "Seja bem vindo " + clienteNome + " !");
                }
            }
        }
    }

    public static void Saque() {

        double valor_saldo;
        if (selectedPerson == pessoas[0])
            valor_saldo = contasF.get(indice_CLIENTE).getSaldo(); // PF
        else
            valor_saldo = contasJ.get(indice_CLIENTE).getSaldo(); // PJ

        valor_saque = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do saque?"));
        if (valor_saldo > valor_saque) {
            valor_saldo -= valor_saque;

            if (selectedPerson == pessoas[0]) {
                contasF.get(indice_CLIENTE).setSaldo(valor_saldo); // PF

                Transacoes transação = new Transacoes("Saque", valor_saque,
                        contasF.get(indice_CLIENTE).getNome(),
                        contasF.get(indice_CLIENTE).getNome(),
                        contasF.get(indice_CLIENTE).getSaldo());
                contasF.get(indice_CLIENTE).setTransacoes(transação);
            } else {
                contasJ.get(indice_CLIENTE).setSaldo(valor_saldo); // PJ

                Transacoes transação = new Transacoes("Saque", valor_saque,
                        contasJ.get(indice_CLIENTE).getNome(),
                        contasJ.get(indice_CLIENTE).getNome(),
                        contasJ.get(indice_CLIENTE).getSaldo());
                contasJ.get(indice_CLIENTE).setTransacoes(transação);
            }

            JOptionPane.showMessageDialog(null, "Saque realizado.");
        } else {
            JOptionPane.showMessageDialog(null, "O saque não pode ser realizado. Saldo insuficiente.");
            int Saque_inesperado = JOptionPane.showConfirmDialog(null, "Deseja trocar o valor do saque?",
                    "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
            if (Saque_inesperado == -1)
                Saque();
        }
    }

    public static double Deposito() {
        double valor_saldo;
        if (selectedPerson == pessoas[0])
            valor_saldo = contasF.get(indice_CLIENTE).getSaldo(); // PF
        else
            valor_saldo = contasJ.get(indice_CLIENTE).getSaldo(); // PJ

        valor_depositado = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do depósito?"));
        valor_saldo += valor_depositado;

        if (selectedPerson == pessoas[0]) {
            contasF.get(indice_CLIENTE).setSaldo(valor_saldo); // PF

            Transacoes transação = new Transacoes("Depósito", valor_depositado,
                    contasF.get(indice_CLIENTE).getNome(),
                    contasF.get(indice_CLIENTE).getNome(),
                    contasF.get(indice_CLIENTE).getSaldo());
            contasF.get(indice_CLIENTE).setTransacoes(transação);
        } else {
            contasJ.get(indice_CLIENTE).setSaldo(valor_saldo);// PJ
            Transacoes transação = new Transacoes("Depósito", valor_depositado,
                    contasJ.get(indice_CLIENTE).getNome(),
                    contasJ.get(indice_CLIENTE).getNome(),
                    contasJ.get(indice_CLIENTE).getSaldo());
            contasJ.get(indice_CLIENTE).setTransacoes(transação);
        }
        JOptionPane.showMessageDialog(null, "Depósito realizado.");
        return valor_saldo;
    }

    public static void Transferencias() {
        indice_pagamento = -1;
        Object[] transferencias = { "Transferência para Cliente Físico", "Transferência para Cliente Jurídico" };
        Object selectedTransfers = JOptionPane.showInputDialog(null, "Que tipo de transferência deseja realizar?",
                "transferência", JOptionPane.INFORMATION_MESSAGE, null, transferencias, transferencias[0]);

        if (selectedTransfers == transferencias[0]) {
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinatário? ");
            String clientePagoCPF = JOptionPane.showInputDialog("Qual o CPF do destinatário? ");
            for (int m = 0; m < contasF.size(); m++) {
                if ((contasF.get(m).getNome().equals(clientePagoNome)) &&
                        (contasF.get(m).getCPF().equals(clientePagoCPF))) {
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCF(contasF.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1) {
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        } else {
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinatário? ");
            String clientePagoCNPJ = JOptionPane.showInputDialog("Qual o CNPJ do destinatário? ");
            for (int m = 0; m < contasJ.size(); m++) {
                if ((contasJ.get(m).getNome().equals(clientePagoNome)) &&
                        (contasJ.get(m).getCNPJ().equals(clientePagoCNPJ))) {
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCJ(contasJ.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1) {
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        }
    }

    public static void TransferenciaCF(PessoaFisica cliente_ganha) {

        Object cliente_perde;
        if (selectedPerson == pessoas[0])
            cliente_perde = contasF.get(indice_CLIENTE); // PF
        else
            cliente_perde = contasJ.get(indice_CLIENTE); // PJ

        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do pagamento?"));

        double limite = ((Conta) cliente_perde).getLimite();
        ArrayList<Transacoes> listaT = ((Conta) cliente_perde).getTransacoes();
        for (int k = 0; k < listaT.size(); k++) {
            limite -= listaT.get(k).valor;
        }
        
        if (limite > valor_troca) {
            JOptionPane.showMessageDialog(null, "Não é possível fazer transferência. Limite insuficiente");
        } else {
            ((Conta) cliente_perde).setSaldo(((Conta) cliente_perde).getSaldo() - valor_troca);
            cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
            ((Conta) cliente_perde)
                    .setTransacoes(new Transacoes("Transferência", valor_troca, ((Conta) cliente_perde).getNome(),
                            cliente_ganha.getNome(), ((Conta) cliente_perde).getSaldo()));
            cliente_ganha.setTransacoes(new Transacoes("Transferência",
                    valor_troca, ((Conta) cliente_perde).getNome(), cliente_ganha.getNome(), cliente_ganha.getSaldo()));
            JOptionPane.showMessageDialog(null, "Transferência realizada.");
        }
    }

    public static void TransferenciaCJ(PessoaJuridica cliente_ganha) {

        Object cliente_perde;
        if (selectedPerson == pessoas[0])
            cliente_perde = contasF.get(indice_CLIENTE); // PF
        else
            cliente_perde = contasJ.get(indice_CLIENTE); // PJ

        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do pagamento?"));

        double limite = ((Conta) cliente_perde).getLimite();
        ArrayList<Transacoes> listaT = ((Conta) cliente_perde).getTransacoes();
        for (int k = 0; k < listaT.size(); k++) {
            limite -= listaT.get(k).valor;
        }
        
        if (limite > valor_troca) {
            JOptionPane.showMessageDialog(null, "Não é possível fazer transferência. Limite insuficiente");
        } else {

            ((Conta) cliente_perde).setSaldo(((Conta) cliente_perde).getSaldo() - valor_troca);
            cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
            ((Conta) cliente_perde).setTransacoes(
                    new Transacoes("Transferência", valor_troca, ((Conta) cliente_perde).getNome(),
                            cliente_ganha.getNome(), ((Conta) cliente_perde).getSaldo()));
            cliente_ganha.setTransacoes(
                    new Transacoes("Transferência", valor_troca, ((Conta) cliente_perde).getNome(),
                            cliente_ganha.getNome(),
                            cliente_ganha.getSaldo()));
            JOptionPane.showMessageDialog(null, "Transferência realizada.");
        }
    }

    public static void Limites() {

        double valor_limite;
        if (selectedPerson == pessoas[0])
            valor_limite = contasF.get(indice_CLIENTE).getLimite(); // PF
        else
            valor_limite = contasJ.get(indice_CLIENTE).getLimite(); // PJ

        int altLimite = JOptionPane.showConfirmDialog(null, "Seu limite é: " + valor_limite + "Deseja alterar?",
                "Selecione uma opção:",
                JOptionPane.YES_NO_OPTION);

        if (altLimite == 0) {
            String novoLimite;
            Double novoLimiteDouble;
            boolean IsDouble = false;

            do {
                novoLimite = JOptionPane.showInputDialog("Insira o limite desejado:");
                try {
                    novoLimiteDouble = Double.parseDouble(novoLimite);

                    if (selectedPerson == pessoas[0])
                        contasF.get(indice_CLIENTE).setLimite(novoLimiteDouble); // PF
                    else
                        contasJ.get(indice_CLIENTE).setLimite(novoLimiteDouble); // PJ

                    IsDouble = true;
                } catch (NumberFormatException ignore) {
                    JOptionPane.showMessageDialog(null, "Valor inválido");
                    IsDouble = false;
                }
            } while (!IsDouble);

        }

    }

    public static void Extrato_Transacoes() throws FileNotFoundException {
        ArrayList<Transacoes> Extrato;
        if (selectedPerson == pessoas[0]) {
            Extrato = contasF.get(indice_CLIENTE).getTransacoes();
            contasF.get(indice_CLIENTE).exportarOperacoes(); // PF
        } else {
            Extrato = contasJ.get(indice_CLIENTE).getTransacoes();
            contasJ.get(indice_CLIENTE).exportarOperacoes(); // PJ
        }

        System.out.println(Extrato);
    }

    public static void Operacoe() {
        Object[] operations = { "Saque", "Depósito", "Transferência", "Limites", "Extrato" };
        Object selectedOperation = JOptionPane.showInputDialog(null, "Que operação deseja realizar?",
                "operação", JOptionPane.INFORMATION_MESSAGE, null, operations, operations[0]);

        if (selectedOperation == operations[0])
            Saque();
        else if (selectedOperation == operations[1])
            Deposito();
        else if (selectedOperation == operations[2])
            Transferencias();
        else if (selectedOperation == operations[3])
            Limites();
        else
            try {
                Extrato_Transacoes();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

    public static void inicializarContas() {

        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            String userDir = System.getProperty("user.dir");

            BufferedReader br = new BufferedReader(new FileReader(userDir + "\\ListaClientes.csv"));
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] conta = line.split(splitBy); // use comma as separator

                String senha = conta[0];
                String nome = conta[1];
                String numConta = conta[2];
                String agencia = conta[3];
                Double saldo = Double.parseDouble(conta[4]);
                Double limite = Double.parseDouble(conta[5]);
                String cpf = conta[6];
                String cnpj = conta[7];

                System.out.println("Conta [senha=" + conta[0] + ", nome =" + conta[1]
                        + ", numero conta =" + conta[2] + ", agencia=" + conta[3] + ", saldo= " + conta[4]
                        + ", limite= " + conta[5] + ", cpf= " + conta[6] + ", cnpj= " + conta[7] + "]");

                if (cpf == "-")
                    contasJ.add(new PessoaJuridica(cnpj, senha, nome, numConta, agencia, saldo, limite));
                else
                    contasF.add(new PessoaFisica(cpf, senha, nome, numConta, agencia, saldo, limite));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        inicializarContas();

        selectedPerson = JOptionPane.showInputDialog(null,
                "Você é qual tipo de cliente ?", "pessoa", JOptionPane.INFORMATION_MESSAGE,
                null, pessoas, pessoas[0]);

        if (selectedPerson != null) {
            login_Conta();

            if (indice_CLIENTE != -1) {
                do {
                    Operacoe();

                    continuar = JOptionPane.showConfirmDialog(null, "Deseja realizar mais alguma operação?",
                            "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
                } while (continuar == 0);
                JOptionPane.showMessageDialog(null, "Obrigado, tenha um bom dia ");
            }
        }
    }
}