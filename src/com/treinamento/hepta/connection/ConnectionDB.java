package com.treinamento.hepta.connection;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class ConnectionDB {

		// usuário mysql
		private static final String USERNAME = "root";

		// senha banco
		private static final String PASSWORD = "";

		// caminho - porta - nome banco
		private static final String DATABASE_URL = "jdbc:mysql://localhost/hepta_db";

		// conexão banco de dados

		public static Connection ConnectionToMySQL() throws Exception {
			// Registra o driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			// Cria a conexão com o banco de dados
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			return connection;
		}

		public static void main(String[] args) throws Exception {

			// verificação da conexão

			try {
				// Recupera uma conexão com o banco de dados
				Connection con = ConnectionToMySQL();

				if (con != null) {
					System.out.println("Conectado");
				}
			} catch (SQLException ex) {
				System.out.println("Falha na conexão");
				ex.printStackTrace();
				
			}

		}

}
