package sample;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameOperations {
    private Statement statement = null;
    private  Statement statement1 = null;
    private  Statement statement2 = null;
    int isEmpty = 2;
    String estimation = null;
    String number1 = null;
    int empty = 0;
    int Control = 0;

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    private PreparedStatement preparedStatement = null;

    private Connection con  = null;

    public void sendValuesToDatabase(String username) throws SQLException {
        statement = con.createStatement();
        String query = "Insert Into user (Username) VALUES(" + "'" + username + "')";
        statement.executeUpdate(query);
    }


    public void isEmpty(int room_id) throws SQLException {
        statement = con.createStatement();
        statement1 = con.createStatement();
        String i = "Select * From room where roomNumb = " + room_id;
        ResultSet rs = statement.executeQuery(i);
        while (rs.next()){
            isEmpty = rs.getInt("isEmpty");
        }

        String query = "Update room Set isEmpty = " + empty + " where roomNumb = " + room_id;
        String query1 = "Update room Set user_fk = " + null + ", user_fk2 = " + null + ", user1Number = " + null + ", user2Number = " + null
                + ", user1EstimatedNumb = " + null + ", user2EstimatedNumb = " + null + " where roomNumb = " + room_id;
        statement1.executeUpdate(query1);
        statement.executeUpdate(query);
    }

    public void createRoom() throws SQLException {
        statement = con.createStatement();

        String q = "Insert Into room (user1EstimatedNumb) VALUES (" + "'" + 3945 + "')";
        statement.executeUpdate(q);
    }

    public void joinRoom(int room_id) throws SQLException {

        statement = con.createStatement();
        statement1 = con.createStatement();

        String q = "Select * From room where roomNumb = " + room_id;
        ResultSet rs = statement.executeQuery(q);

        while (rs.next()){
            isEmpty = rs.getInt("isEmpty");
        }
        String query5 = "";
        if( isEmpty == 0 ) {
            query5 = "Update room Set isEmpty = " + 1 + " where roomNumb = " + room_id  ;
        }
        else if( isEmpty == 1 ) {
            query5 = "Update room Set isEmpty = " + 2 + " where roomNumb = " + room_id  ;
        }
        statement1.executeUpdate(query5);
    }

    public void sendNumberToDatabase(String number, int room_id,String userName) throws SQLException {
        statement = con.createStatement();
        statement1 = con.createStatement();
        statement2 = con.createStatement();
        int user_id = 0;
        String q = "Select * From room where roomNumb = " + room_id;
        String userID = "Select * From user where Username = '" + userName + "'";
        ResultSet rs = statement.executeQuery(q);
        ResultSet rs1 = statement2.executeQuery(userID);

        while (rs1.next()){
            user_id = rs1.getInt("User_id");
        }
        while (rs.next()){
            isEmpty = rs.getInt("isEmpty");
        }
        String query4 = "";
        String query2 = "";

        if( isEmpty == 0 ) {
            query4 = "Update room Set user_fk = " + user_id  + " where roomNumb = " + room_id;
            query2 = "Update room Set user1Number = " + number + " where roomNumb = " + room_id;
        }

        else if( isEmpty == 1 ) {
            query4 = "Update room Set user_fk2 = " + user_id + " where roomNumb = " + room_id;
            query2 = "Update room Set user2Number = " + number + " where roomNumb = " + room_id;
        }

        statement.executeUpdate(query4);
        statement2.executeUpdate(query2);
    }

    public void bringNumber(int room_id) throws SQLException {
        statement = con.createStatement();
        statement1 = con.createStatement();
        statement2 = con.createStatement();

        int user_id = 0;
        int user = 0;
        int user1 = 0;

        String room = "Select * From room where roomNumb = " + room_id;

    }

    public void timer(int room_id){

        Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
    }

    public void userNumbers(int room_id) throws SQLException {
        statement = con.createStatement();
        String user1Numb = null;
        String user2Numb = null;
        String room = "Select * From room where roomNumb = " + room_id;
        ResultSet rs = statement.executeQuery(room);

        while (rs.next()){
            user1Numb = rs.getString("user1Number");
            user2Numb = rs.getString("user2Number");
        }
        if (user1Numb != null && user2Numb != null){
            Control = 1;
        }
    }

    public void guessNumber(int room_id,String number,String userName) throws SQLException {

        statement = con.createStatement();
        statement1 = con.createStatement();
        statement2 = con.createStatement();

        int user_id = 0;
        int user = 0;
        int user1 = 0;
        String user1Numb = null;
        String user2Numb = null;

        String room = "Select * From room where roomNumb = " + room_id;
        String userID = "Select * From user where Username = '" + userName + "'";

        ResultSet rs = statement.executeQuery(room);
        ResultSet rs1 = statement2.executeQuery(userID);

        while (rs.next()){
             user = rs.getInt("user_fk");
             user1  = rs.getInt("user_fk2");
             user1Numb = rs.getString("user1Number");
             user2Numb = rs.getString("user2Number");
        }
        while (rs1.next()){
            user_id = rs1.getInt("User_id");
        }
        while (rs.next()){
            isEmpty = rs.getInt("isEmpty");
        }

        String query1 = "";
        String query2 = "";


        if (user_id == user ){
            System.out.println(user2Numb + " bura  " + number);
            query1 = "Update room Set user1EstimatedNumb = " + number + " where roomNumb = " +room_id;
            number1 = user2Numb;
            estimation = number;

//            onlineGame1.guessOnline();

        } else if (user_id == user1){
            System.out.println(user1Numb + " bura  " + number);
            query1 = "Update room Set user2EstimatedNumb = " + number + " where roomNumb = " +room_id;
            number1 = user1Numb;
            estimation = number;

//            onlineGame1.guessOnline();

        }
        statement2.executeUpdate(query1);
    }

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Database.dbName,"root", "");
        return connection;
    }

    public GameOperations(){
        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.dbName;

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println("cannot find driver");
        }
        try {
            con = (Connection) DriverManager.getConnection(url,Database.userName,Database.password);
            System.out.println("connection is successful");
        } catch (SQLException e) {
            System.out.println("connection is unsuccessful");
        }
    }
}
