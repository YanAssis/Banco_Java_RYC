package banco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public abstract class Conta {
    private String senha;
    private String nome;
    private String numConta;
    private String numAgencia;
    private double saldo = 0;
    private double limite;
    private ArrayList<Transacoes> listaT = new ArrayList<>();

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transacoes> getTransacoes() {
        return listaT;
    }

    public void setTransacoes(Transacoes transação) {

        this.listaT.add(transação);

    }

    public void exportarOperacoes() throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        String header = "Operação, Valor, Remetente, Destinatário, Saldo_Final";
        sb.append(header);

        for (int i = 0; i < this.listaT.size(); i++) {
            sb.append("\r\n");

            String operacao = this.listaT.get(i).operacao;
            String valor = Double.toString(this.listaT.get(i).valor);
            String remetente = this.listaT.get(i).remetente;
            String destinatario = this.listaT.get(i).destinatario;
            String saldo_final = Double.toString(this.listaT.get(i).saldo_final);

            String line = operacao + ", " + valor + ", " + remetente + ", " + destinatario + ", " + saldo_final;
            sb.append(line);
        }

        PrintWriter pw = new PrintWriter(new File(System.getProperty("user.dir")+ "\\Extrato_"+this.nome +".csv"));
        pw.write(sb.toString());
        pw.close();
    }

    public Conta(String senha, String nome, String conta, String agencia, Double saldo, Double limite) {
        Random rand = new Random();

        this.senha = senha;
        this.nome = nome;

        if (limite == 0)
            this.limite = 5000;
        else
            this.limite = limite;

        this.saldo = saldo;

        if (conta.isBlank()) {

            this.numConta = String.format("%04d", rand.nextInt(10000));
        } else
            this.numConta = conta;

        if (agencia.isBlank()) {
                String numAgen = String.format("%05d", rand.nextInt(100000));
                String codAgen = String.format("%01d", rand.nextInt(10));
                this.numAgencia = numAgen + "-" + codAgen;
        } else
            this.numAgencia = agencia;
    }
}