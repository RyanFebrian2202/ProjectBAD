package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void main(String[] args) {
		try {
			//Load Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection
			String username = "root"; //standard mysql
			String password = null;
			String server = "localhost:3306";
			String databaseName = "bad_project";
			String connectionString = String.format("jdbc:mysql://%s/%s", server,databaseName);
			
			Connection con = DriverManager.getConnection(connectionString, username, password);
			
			//execute query --> hanya  untuk select data kl mau insert dkk pake executeUpdate
			Statement st = con.createStatement();
			
			//insert data
			//st.executeUpdate("INSERT INTO Mahasiswa VALUES('2540118753','Ezekhiel Ethan','3')");
			// ini gbs disimpan tp yg bs disimpan itu adalah berapa nilai yg berubah
			// int totalAffectedRow = st.executeUpdate("INSERT INTO Mahasiswa VALUES('2540118750','Ezekhiel Ethan','3')");
			// gbs pake resultset krn ini kan gk ngasilin table cuma update table doang. gk ada hasil.
			
			//get data
			ResultSet rs = st.executeQuery("SELECT * FROM Mahasiswa");
			
			//get value from result set
			while (rs.next()) {
//				String NIM = rs.getString("NIM");
//				String nama = rs.getString("Nama");
//				int semester = rs.getInt("Semester");
//				
//				System.out.println(NIM + " " + nama + " " + semester);
			};
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
