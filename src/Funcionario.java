public class Funcionario extends Thread{

    private String nome;
    private Loja loja;
    private Conta contaSalario;
    private Conta ContaInvestimento;
    private Object monitor;

    public Funcionario(String nome, Loja loja, Object monitor) {
        this.nome = nome;
        this.loja = loja;
        this.contaSalario = new Conta(loja.getConta().getbanco());
        this.ContaInvestimento = new Conta(loja.getConta().getbanco());
        this.monitor = monitor;
    }

    public void run() {
        double remuneracao = 1400.0;
        synchronized (loja.getConta().getbanco()) {
            if (loja.getConta().getSaldo() >= remuneracao) {
                loja.getConta().saque(remuneracao);
                getContaSalario().depositar(remuneracao);
                // Cálculo do valor do investimento (20% do salário)
                double valorInvestimento = remuneracao * 0.2;
                getContaInvestimento().depositar(valorInvestimento);
                double saldoSalarioFinal = getContaSalario().getSaldo() - valorInvestimento;
                System.out.println("---");
                System.out.println(nome + " recebeu R$" + remuneracao + " da " + loja.getNome());
                System.out.println(nome + " investiu R$" + valorInvestimento);
                System.out.println("saldo do " + nome + " após investimento: R$" + saldoSalarioFinal);
            }
        }
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public Conta getContaInvestimento() {
        return ContaInvestimento;
    }
}
