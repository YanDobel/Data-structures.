import java.util.Scanner;

// ==== Just an example of using the HashTable class ====
// ==== This main class is just for this example xD ====

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("- Digite o tamanho da tabela -");
        int size = sc.nextInt();
        HashTable<String, Double> table = new HashTable<>(size);

        while (true) {
            System.out.println("1 - Criar novo dado na tabela");
            System.out.println("2 - Buscar");
            System.out.println("3 - Mostrar tudo");
            System.out.println("4 - Sair do programa");
            int op = sc.nextInt();

            if (op == 1) {
                System.out.println("Digite a string");
                String nome = sc.next();
                System.out.println("Digite o valor");
                double valor = sc.nextDouble();

                table.insert(nome, valor);
            }
            if (op == 2) {

                System.out.println("Digite o primeiro dado");
                String nome2 = sc.next();
                System.out.println(table.search(nome2));
            }
            if (op == 3) {
                table.showAll();
            }
            if (op == 4) {
                System.out.println("Programa fechado");
                break;
            }

        }
        sc.close();
    }
}