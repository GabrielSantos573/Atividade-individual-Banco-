import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco();
        Object monitor = new Object();

        Loja loja1 = new Loja("Loja 1", banco, monitor);
        Loja loja2 = new Loja("Loja 2", banco, monitor);

        List<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente("Cliente " + i, banco, loja1, loja2, monitor);
            clientes.add(cliente);
        }

        List<Funcionario> funcionarios = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Funcionario funcionario = new Funcionario("Funcionário " + i, loja1, monitor);
            funcionarios.add(funcionario);
            if (i % 2 == 0) {
                funcionario.setLoja(loja2);
            }
        }
        // Atribuição das lojas ao banco
        banco.setLoja(List.of(loja1, loja2));

        // Inicialização das threads
        for (Cliente cliente : clientes) {
            cliente.start();
        }

        // Aguarda o término das threads
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Funcionario funcionario : funcionarios) {
            funcionario.start();
        }

        for (Funcionario funcionario : funcionarios) {
            try {
                funcionario.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        banco.SaldoFinalLoja();
    }
}
