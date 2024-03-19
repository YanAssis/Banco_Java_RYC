package banco;

public class Transacoes {
    String operacao;
    double valor;
    String remetente;
    String destinatario;
    double saldo_final;

    public Transacoes(String operacao, double valor, String remetente, String destinatario, double saldo_final) {
        this.operacao = operacao;
        this.valor = valor;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.saldo_final = saldo_final;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(double saldo_final) {
        this.saldo_final = saldo_final;
    }

    @Override
    public String toString() {
        return this.operacao + "| valor: " + this.valor + "|remetente: " + this.remetente + "| destinatário: " +
                this.destinatario + "| saldo final: " + this.saldo_final + "\n";
    }
}