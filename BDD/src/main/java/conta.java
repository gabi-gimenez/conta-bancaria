
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Scanner;

public class conta {

    private String tipoCliente;
    private double saldo;

    @Given("Um cliente especial com pontos (\\d+) e saldo atual de (\\d+) reais")
    public void umClienteEspecialComPontosESaldo(int pontos, int saldo) {
        this.saldo = saldo;
        definirTipoCliente(pontos);
    }

    @When("for solicitado um saque no valor de (\\d+) reais")
    public void forSolicitadoUmSaqueNoValorDeReais(int valorSaque) {
        if (tipoCliente.equals("especial")) {
            if (saldo < 0) {
            	saldo -= valorSaque;
            	
            } else if (saldo >= valorSaque) {
                saldo -= valorSaque;
            }
        } else {
            if (tipoCliente.equals("comum") && saldo >= 0 && saldo >= valorSaque) {
                saldo -= valorSaque;
            } else {
                System.out.println("Saldo insuficiente.");
            }
        }
    }

    @Then("deve efetuar o saque e atualizar o saldo da conta para (\\d+) reais")
    public void deveEfetuarOSaqueEAtualizarOSaldoDaContaParaReais(int novoSaldo) {
        assert saldo == novoSaldo : "O saldo não foi atualizado corretamente após o saque.";
    }

    private void definirTipoCliente(int pontos) {
        if (pontos > 500) {
            tipoCliente = "especial";
        } else {
            tipoCliente = "comum";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a quantidade de pontos do cliente:");
        int pontosCliente = scanner.nextInt();

        System.out.println("Digite o saldo da conta:");
        int saldoInicial = scanner.nextInt();

        conta conta = new conta();
        conta.umClienteEspecialComPontosESaldo(pontosCliente, saldoInicial);

        System.out.println("Digite o valor do saque:");
        int valorSaque = scanner.nextInt();
        conta.forSolicitadoUmSaqueNoValorDeReais(valorSaque);

        System.out.println("Saldo da conta: " + conta.saldo +"reais");

        scanner.close();
    }
}


