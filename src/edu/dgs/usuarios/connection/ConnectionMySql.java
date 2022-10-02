package edu.dgs.usuarios.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {

	private static ConnectionMySql instance;
	private static Connection connection;

	private ConnectionMySql() {
		String driverJDBC = "com.mysql.jdbc.Driver";
		String hostname = "localhost";
		String port = "3306";
		String database = "login";
		String username = "root";
		String password = "root";

		// URL de la base de datos
		String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
		try {
			Class.forName(driverJDBC);
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
	}

	public static ConnectionMySql getInstance() {
		if (instance == null) {
			instance = new ConnectionMySql();
		} else {
			System.out.println("No se puede crear el objeto porque ya existe otro objeto de la clase ConnectionMySql");
		}
		return instance;
	}

	public static Connection getConnection() {
		if (instance == null) {
			instance = new ConnectionMySql();
		}
		return connection;
	}
}
