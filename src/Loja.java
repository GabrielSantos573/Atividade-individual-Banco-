public class Loja {

    private String nome;
    private Conta conta;
    private Object monitor;

    public Loja(String nome, Banco banco, Object monitor) {
        this.nome = nome;
        this.conta = new Conta(banco);
        this.monitor = monitor;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }
}
