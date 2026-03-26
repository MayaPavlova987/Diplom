package ru.netology.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static final String DB_URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/app");
    private static final String DB_USER = System.getProperty("db.user", "app");
    private static final String DB_PASSWORD = System.getProperty("db.password", "pass");
    private static final QueryRunner runner = new QueryRunner();

    public static Connection getConnection() throws SQLException {
        System.out.println(">>> Подключение к БД: " + DB_URL);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void cleanDatabase() {
        try (Connection conn = getConnection()) {
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM payment_entity");
            runner.update(conn, "DELETE FROM credit_request_entity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===== ДЛЯ PAYMENT_ENTITY =====

    public static String getPaymentStatus() throws SQLException {
        String sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    public static String getPaymentId() throws SQLException {
        String sql = "SELECT id FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    // ===== ДЛЯ CREDIT_REQUEST_ENTITY =====

    public static String getCreditStatus() throws SQLException {
        String sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    public static String getCreditId() throws SQLException {
        String sql = "SELECT id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    // ===== ДЛЯ ORDER_ENTITY =====

    public static String getOrderPaymentId() throws SQLException {
        String sql = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    public static String getOrderCreditId() throws SQLException {
        String sql = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }

    // ===== УНИВЕРСАЛЬНЫЙ МЕТОД ДЛЯ ПРОВЕРКИ СВЯЗИ =====

    public static String getOrderId() throws SQLException {
        String sql = "SELECT id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection()) {
            return runner.query(conn, sql, new ScalarHandler<>());
        }
    }
}