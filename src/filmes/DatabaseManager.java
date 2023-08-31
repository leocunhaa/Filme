package filmes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<String> getMoviesByGenre(String genre) {
        List<String> movies = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT titulo FROM filmes WHERE genero = ?");
        ) {
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                movies.add(resultSet.getString("titulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        List<String> actionMovies = dbManager.getMoviesByGenre("Ação");
        System.out.println("Filmes de Ação:");
        actionMovies.forEach(System.out::println);

        List<String> comedyMovies = dbManager.getMoviesByGenre("Comédia");
        System.out.println("Filmes de Comédia:");
        comedyMovies.forEach(System.out::println);

        List<String> romanceMovies = dbManager.getMoviesByGenre("Romance");
        System.out.println("Filmes de Romance:");
        romanceMovies.forEach(System.out::println);
    }
}
