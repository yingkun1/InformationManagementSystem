package online.luffyk.dao.impl;

import online.luffyk.dao.UserLoginDao;
import online.luffyk.domain.User;

import java.sql.*;

public class UserLoginDaoImpl implements UserLoginDao {
    @Override
    public User checkUserLoginDao(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://101.132.138.215:3306/servlet?characterEncoding=utf-8&useSSL=false", "root", "yingkun9257");
            String sql = "select * from t_user where username=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setUid(resultSet.getInt("uid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSex(resultSet.getString("sex"));
                user.setAge(resultSet.getInt("age"));
                user.setBirthday(resultSet.getString("birthday"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSet!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return user;
        }

    }
}
