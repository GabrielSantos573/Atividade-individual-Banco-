public class Conta {
    private Banco banco;
    private double saldo;

    public Conta(Banco banco) {
        this.banco = banco;
        this.saldo = 0.0;
    }

    public Conta(Banco banco, double saldo) {
        this.banco = banco;
        this.saldo = saldo;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
    }

    public synchronized void saque(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        }
    }

    public Banco getbanco() {
        return banco;
    }
}
