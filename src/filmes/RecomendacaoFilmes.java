package filmes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class RecomendacaoFilmes {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo! Qual é o seu nome?");
        String nome = scanner.nextLine();

        System.out.println("Olá, " + nome + "! Que tipo de filme você gosta?");
        System.out.println("Digite 1 para ação, 2 para romance ou 3 para comédia:");

        int escolha = scanner.nextInt();
        String filmeRecomendado = "";

        switch (escolha) {
            case 1:
                filmeRecomendado = "Velozes e Furiosos";
                break;
            case 2:
                filmeRecomendado = "O date perfeito";
                break;
            case 3:
                filmeRecomendado = "Esposa de Mentirinha";
                break;
            default:
                System.out.println("Opção inválida.");
        }

        if (!filmeRecomendado.isEmpty()) { // Aqui indica se está vazia ou não, no caso botei o "!" para indicar ao contrário. 
            System.out.println("Recomendação para você: " + filmeRecomendado);
            salvarRecomendacaoEmCSV(nome, filmeRecomendado);
        }

        scanner.close();
    }

    public static void salvarRecomendacaoEmCSV(String nome, String filmeRecomendado) {
        try (FileWriter writer = new FileWriter("recomendacoes.csv", true)) {
            writer.append(nome + "," + filmeRecomendado + "\n");
            System.out.println("Recomendação salva em recomendacoes.csv");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar a recomendação.");
            e.printStackTrace();
        }
    }

}
