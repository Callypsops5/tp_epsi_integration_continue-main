package com.epsi.tp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Idéalement, ces informations proviennent d'un fichier de configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public boolean login(String username, String password) {

        System.out.println("Tentative de connexion de l'utilisateur : " + username);

        // Exemple simple (à remplacer par une vérification en base)
        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("Administrateur connecté avec succès.");
            return true;
        }

        System.out.println("Identifiants invalides.");
        return false;
    }

    public void getUserDetails(String username) {

        String query = "SELECT username FROM users WHERE username = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    System.out.println(
                            "Utilisateur trouvé : "
                                    + rs.getString("username"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur : "
                    + e.getMessage());
        }
    }

    public void complexMethod(int a, int b, int c) {

        if (a <= 0) {
            System.out.println("A est négatif");
            return;
        }

        if (b <= 0 && c <= 0) {
            System.out.println("B et C sont négatifs");
            return;
        }

        if (b <= 0) {
            System.out.println("B est négatif");
            return;
        }

        if (c <= 0) {
            System.out.println("C est négatif");
            return;
        }

        System.out.println("Tous positifs");
    }
}