package subject_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;*/



public class subjectDBUtil {
	private String jdbcURL = "jdbc:mysql://localhost:3306/school?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "200068402548";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	

	private static final String INSERT_SUBJECT_SQL = "INSERT INTO subject" + "  (name, credit) VALUES "
			+ " (?, ?);";

	private static final String SELECT_SUBJECT_BY_SUBID = "select subID,  name, credit from subject where subID =?";
	private static final String SELECT_ALL_SUBJECT = "select * from subject";
	private static final String DELETE_SUBJECT_SQL = "delete from subject where subID=?;";
	private static final String UPDATE_SUBJECT_SQL = "update subject set name =?,credit=? where subID = ?;";
	

	public subjectDBUtil() {
	}
 
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
//insert subject
	public void insertsubject(subject subject) throws SQLException {
		System.out.println(INSERT_SUBJECT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT_SQL)) {
			preparedStatement.setString(1, subject.getName());
			preparedStatement.setString(2, subject.getCredit());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
//select subject
	public subject selectsubject(int subID) {
		subject subject = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_SUBID);) {
			preparedStatement.setInt(1, subID);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String credit = rs.getString("credit");
				
				subject = new subject(subID, name,credit);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subject;
	}
//select all subject
	public List<subject> selectAllsubjects() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<subject> subject = new ArrayList<>();
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int subID = rs.getInt("subID");
				String name = rs.getString("name");
				String credit = rs.getString("credit");
				
				subject.add(new subject(subID, name, credit));
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subject;
	}
//update subject
	public boolean updatesubject(subject subject) throws SQLException {
		boolean rowUpdated=false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SQL);) {
			
			statement.setString(1, subject.getName());
			statement.setString(2, subject.getCredit());
		     statement.setInt(3, subject.getSubID());
		     System.out.println("updated Subject:"+statement);
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("status "+ rowUpdated);
		
		}
		catch (SQLException e) {
			printSQLException(e);
		}
		return rowUpdated;
	}
//delete subject
	public boolean deletesubject(int subID) throws SQLException {
		boolean rowDeleted=false;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_SQL);) {
			statement.setInt(1, subID);
			rowDeleted = statement.executeUpdate ()>0;
			System.out.println("status delete "+ rowDeleted);
		}
		catch (SQLException e) {
			printSQLException(e);
		
		}
		return rowDeleted;
	}


	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}