

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author Lei
 *
 */

public class QuestionDao {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public QuestionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     //connect with DB;
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	//load DB driver, and each DB has its own driver;
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            //setup connection with DB;
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     //disconnect with the DB;
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     //prepareStatement allows to issue SQL query to DB
    public boolean postQuestion(Question que) throws SQLException {
    	System.out.println("Call postQuestion");
        String sql = "INSERT INTO wsudiy.questions (Question, Username, Askdate) VALUES (?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, que.getQuestion());
        statement.setString(2, que.getUsername());
        statement.setDate(3, que.getAskDate());
        System.out.println("Sql script: "+sql);
        System.out.println("Question: "+que.getQuestion());
        System.out.println("Username: "+ que.getUsername());
        System.out.println("AskDate: " +que.getAskDate());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        System.out.println("Inserted:"+rowInserted);
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Question> listAllQuestions() throws SQLException {
        List<Question> listQuestions = new ArrayList<>();
         
        String sql = "SELECT * FROM questions";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int qid = resultSet.getInt("Qid");
            String question = resultSet.getString("Question");
            String username = resultSet.getString("Username");
            Date askDate = resultSet.getDate("Askdate");
             
            Question que1 = new Question(qid,question,username,askDate);
            listQuestions.add(que1);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listQuestions;
    }
    
    
    
}
