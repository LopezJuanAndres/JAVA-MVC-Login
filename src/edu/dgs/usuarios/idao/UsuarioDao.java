package edu.dgs.usuarios.idao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.dgs.usuarios.connection.ConnectionMySql;
import edu.dgs.usuarios.model.Usuario;

public class UsuarioDao implements IUsuarioDao {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public int insertUsuario(Usuario usuario) {
		int filas = 0;
		String query;
		boolean autoCommit;

		// Sentencia SQL
		query = "INSERT INTO usuario " + "(username, email, password,admin) " + "VALUES(?,?,?,?)";

		try {

			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPassword());
			preparedStatement.setBoolean(4, usuario.getAdmin());

			// Ejecutamos la sentencia y recupera las filas afectadas
			filas = preparedStatement.executeUpdate();
			if (filas > 0) {
				connection.commit();
			} else {
				connection.rollback();
				System.out.println("Error al intentar insertar un nuevo usuario");
			}
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return filas;
	}

	@Override
	public int updateUsuario(Usuario usuario) {
		int filas = 0;
		String query;
		boolean autoCommit;

		// Sentencia SQL
		query = "UPDATE usuario SET " + 
						"username = ?, " +
						"email = ?, " +
						"password = ?, " +
						"admin = ? " +
						"WHERE id = ?";
		try {

			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPassword());
			preparedStatement.setBoolean(4, usuario.getAdmin());
			preparedStatement.setInt(5, usuario.getId());

			// Ejecutamos la sentencia y recupera las filas afectadas
			filas = preparedStatement.executeUpdate();
			if (filas > 0) {
				connection.commit();
			} else {
				connection.rollback();
				System.out.println("Error al intentar modificar un nuevo usuario");
			}
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return filas;
	}

	@Override
	public int deleteUsuario(Usuario usuario) {
		int filas = 0;
		String query;
		boolean autoCommit;

		// Sentencia SQL
		query = "DELETE FROM usuario WHERE id = ?";
		try {

			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setInt(1, usuario.getId());

			// Ejecutamos la sentencia y recupera las filas afectadas
			filas = preparedStatement.executeUpdate();
			if (filas > 0) {
				connection.commit();
			} else {
				connection.rollback();
				System.out.println("Error al intentar borrar el usuario");
			}
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return filas;
	}

	@Override
	public Usuario getUsuarioLogin(Usuario usuario) {
		
		Usuario usuarioLogin = null;
		String query;
		boolean autoCommit;
		
		// Crea la sentencia
		query = "SELECT * FROM usuario " + 
				"WHERE username = ? AND "+
				"password = ?";

		// Crea el Sstatement
		try {
			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());

			// Ejecutamos la sentencia y recupertamos las filas afectadas
			resultSet = preparedStatement.executeQuery();

			// Recupera los datos del usuario
			while (resultSet.next()){
				usuarioLogin =new Usuario();
				usuarioLogin.setId(resultSet.getInt("id"));
				usuarioLogin.setUsername(resultSet.getString("username"));
				usuarioLogin.setEmail(resultSet.getString("email"));
				usuarioLogin.setPassword(resultSet.getString("password"));
				usuarioLogin.setAdmin(resultSet.getBoolean("admin"));
				usuarioLogin.setFecha(resultSet.getTimestamp("fecha"));
			}
			resultSet.close();
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);;
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return usuarioLogin;
	}
	
	@Override
	public Usuario getUsuarioById(Usuario usuario) {
		Usuario usuarioById = null;
		String query;
		boolean autoCommit;
		
		// Crea la sentencia
		query = "SELECT * FROM usuario WHERE id = ?";

		// Crea el Sstatement
		try {
			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setInt(1, usuario.getId());

			// Ejecutamos la sentencia y recupertamos las filas afectadas
			resultSet = preparedStatement.executeQuery();

			// Recupera los datos del usuario
			while (resultSet.next()){
				usuarioById =new Usuario();
				usuarioById.setId(resultSet.getInt("id"));
				usuarioById.setUsername(resultSet.getString("username"));
				usuarioById.setEmail(resultSet.getString("email"));
				usuarioById.setPassword(resultSet.getString("password"));
				usuarioById.setAdmin(resultSet.getBoolean("admin"));
				usuarioById.setFecha(resultSet.getTimestamp("fecha"));
			}
			resultSet.close();
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);;
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return usuarioById;
	}

	@Override
	public Usuario getUsuarioByUsername(Usuario usuario) {
		Usuario usuarioByUsername = null;
		String query;
		boolean autoCommit;
		
		// Crea la sentencia
		query = "SELECT * FROM usuario " + 
				"WHERE username = ?";

		// Crea el Sstatement
		try {
			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setString(1, usuario.getUsername());

			// Ejecutamos la sentencia y recupertamos las filas afectadas
			resultSet = preparedStatement.executeQuery();

			// Recupera los datos del usuario
			while (resultSet.next()){
				usuarioByUsername =new Usuario();
				usuarioByUsername.setId(resultSet.getInt("id"));
				usuarioByUsername.setUsername(resultSet.getString("username"));
				usuarioByUsername.setEmail(resultSet.getString("email"));
				usuarioByUsername.setPassword(resultSet.getString("password"));
				usuarioByUsername.setAdmin(resultSet.getBoolean("admin"));
				usuarioByUsername.setFecha(resultSet.getTimestamp("fecha"));
			}
			resultSet.close();
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);;
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return usuarioByUsername;
	}


	@Override
	public ArrayList<Usuario> getUsuarioValidate(Usuario usuario) {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuarioValidate = null;
		String query;
		boolean autoCommit;
		
		// Crea la sentencia
		query = "SELECT * FROM usuario " + 
				"WHERE username = ? OR "+
				"email = ?";

		// Crea el Sstatement
		try {
			// Obtiene Connection
			connection = ConnectionMySql.getConnection();
			autoCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// Crea el PreparedStatement
			preparedStatement = connection.prepareStatement(query);

			// Deposita los valores en el PreparedStatement
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getEmail());

			// Ejecutamos la sentencia y recupertamos las filas afectadas
			resultSet = preparedStatement.executeQuery();

			// Recupera los datos del usuario
			while (resultSet.next()){
				usuarioValidate =new Usuario();
				usuarioValidate.setId(resultSet.getInt("id"));
				usuarioValidate.setUsername(resultSet.getString("username"));
				usuarioValidate.setEmail(resultSet.getString("email"));
				usuarioValidate.setPassword(resultSet.getString("password"));
				usuarioValidate.setAdmin(resultSet.getBoolean("admin"));
				usuarioValidate.setFecha(resultSet.getTimestamp("fecha"));
				usuarios.add(usuarioValidate);
			}
			resultSet.close();
			preparedStatement.close();
			connection.setAutoCommit(autoCommit);;
			//connection.close();
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		}
		return usuarios;
	}
	
	@Override
	public Integer getUsuarioMaxId() {
		Integer id = null;

		String query = "SELECT MAX(id) AS id FROM usuario";

		try {
			connection = ConnectionMySql.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			statement.close();
			resultSet.close();
			//connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
