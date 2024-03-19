package banco;

public class PessoaFisica extends Conta {
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public PessoaFisica(String CPF, String senha, String nome, String conta, String agencia, Double saldo, Double limite) {
        super(senha, nome, conta, agencia, saldo, limite);
        this.CPF = CPF;
    }
}