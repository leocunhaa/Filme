package filmes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RecomendacaoFilmes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo! Qual é o seu nome?");
        String nome = scanner.nextLine();

        System.out.println("Olá, " + nome + "! Que tipo de filme você gosta?");
        System.out.println("Digite 1 para ação, 2 para romance ou 3 para comédia:");

        int escolha = scanner.nextInt();
        String genero = "";

        switch (escolha) {
            case 1:
                genero = "Ação";
                break;
            case 2:
                genero = "Romance";
                break;
            case 3:
                genero = "Comédia";
                break;
            default:
                System.out.println("Opção inválida.");
        }

        if (!genero.isEmpty()) {
            DatabaseManager dbManager = new DatabaseManager();
            List<String> filmesDoGenero = dbManager.getMoviesByGenre(genero);
            
            if (!filmesDoGenero.isEmpty()) {
                System.out.println("Filmes do gênero " + genero + ":");
                filmesDoGenero.forEach(System.out::println);
                salvarRecomendacaoEmCSV(nome, filmesDoGenero.get(0)); // Salvando o primeiro filme da lista como recomendação
            } else {
                System.out.println("Não foram encontrados filmes do gênero " + genero + ".");
            }
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
