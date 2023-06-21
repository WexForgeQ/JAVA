package db;


import com.mysql.jdbc.Driver;
import entities.users;
import entities.nation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    static final Logger logger = LogManager.getLogger(DBConnection.class);
    private Connection connection;
    private Statement statement;

    public Connection GetConnection() throws SQLException {
        try {
            DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/laba13java",
                                                                    "root", "root");
            System.out.println("Connection successful!");
        }catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }

        return connection;
    }

    public ArrayList<users> GetAllUsers() throws SQLException {
        String sqlQuery="select * from users;";
        statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<users> users = new ArrayList<entities.users>();
        while(resultSet.next()){
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String role = resultSet.getString(3);

            entities.users user = new users(username,password,role);
            users.add(user);
        }
        return users;
    }

    public boolean AddUser(users user) throws SQLException {

        //проверка на существующее имя пользователя
        String select="insert into usertable(username,password,role) values('"+user.getUsername() +"','"+user.getPassword()+ "','user');";
        statement = GetConnection().createStatement();
        logger.info("Добавление пользователя");
        statement.executeUpdate(select);

        return true;
    }

    public users GetByUsername(String Username) throws SQLException {
        String select ="select username,password,role from usertable where username = '"+Username+"';";
        statement = GetConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(select);
        users user=null;
        if(resultSet != null){
            while(resultSet.next()){
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String role = resultSet.getString(3);
                user = new users(username,password,role);
            }
        }
        return  user;
    }
    public nation GetByname(String names) throws SQLException {
        String select ="select * from nation where name = '"+names+"';";
        statement = GetConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(select);
        nation nations=null;
        if(resultSet != null){
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String count = resultSet.getString(2);
                nations = new nation(name,count);
            }
        }
        return  nations;
    }
    public ArrayList<nation> GetAllNations() throws SQLException {
        String sqlQuery="select * from nation";
        statement = GetConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<nation> nations = new ArrayList<nation>();
        while(resultSet.next()){
            String name = resultSet.getString(1);
            String count = resultSet.getString(2);
            nation nation = new nation(name,count);
            nations.add(nation);
        }
        return nations;
    }
    public boolean AddNations(nation nat) throws SQLException {
        logger.info("Добавление народа");
        String select="insert into nation(name,number) values('"+nat.getName()+"','"+nat.getNumber()+"')";
        statement = GetConnection().createStatement();
        statement.executeUpdate(select);
        return true;
    }
    public boolean Remove(String names) throws SQLException {
        logger.info("Удаление народа");
        String select="DELETE FROM nation WHERE name ='"+names+"'";
        statement = GetConnection().createStatement();

        statement.executeUpdate(select);

        return true;
    }
    public boolean Update(String names,String number) throws SQLException {
        logger.info("Изменение народа");
        String select="UPDATE nation SET number ='"+number+"' WHERE name ='"+names+"';";
        statement = GetConnection().createStatement();
        statement.executeUpdate(select);

        return true;
    }
}