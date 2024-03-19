package banco;

public class PessoaJuridica extends Conta {
    private String CNPJ;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public PessoaJuridica(String CNPJ, String senha, String nome, String conta, String agencia, Double saldo, Double limite) {
        super(senha, nome, conta, agencia, saldo, limite);
        this.CNPJ = CNPJ;
    }
}