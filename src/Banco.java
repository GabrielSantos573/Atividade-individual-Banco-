import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Loja> lojas;

    public Banco() {
        this.lojas = new ArrayList<>();
    }

    public synchronized void transferir(Cliente cliente,String nome, double valor, Loja loja) {
        cliente.getConta().saque(valor);
        loja.getConta().depositar(valor);
        System.out.println("---");
        System.out.println(nome + " comprou na " + loja.getNome() + ": " + "R$" + valor);
    }

    public synchronized void SaldoFinalLoja() {
        for (Loja loja : lojas) {
            System.out.println("-----------------------------------");
            System.out.println("Saldo da loja "+ loja.getNome() +  " com desconto do salário do(s) funcionário(s)" + ": " + "R$" + loja.getConta().getSaldo());
        }
    }

    public void setLoja(List<Loja> lojas) {
        this.lojas = lojas;
    }
}
