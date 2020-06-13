package online.luffyk.dao.impl;

import online.luffyk.dao.UserDao;
import online.luffyk.domain.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Logger logger = Logger.getLogger(UserDaoImpl.class);
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

    @Override
    public Integer changeUserPwdDao(Integer uid, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://101.132.138.215:3306/servlet?characterEncoding=utf-8&useSSL=false", "root", "yingkun9257");
            String sql = "update t_user set password=? where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2,uid);
            index = preparedStatement.executeUpdate();
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
            return index;
        }

    }

    @Override
    public List<User> showAllUserDao() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://101.132.138.215/servlet?characterEncoding=utf-8&useSSL=false", "root", "yingkun9257");
            String sql = "select * from t_user ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);
            users = new ArrayList<>();
            while (resultSet.next()){
                User user = new User();
                user.setUid(resultSet.getInt("uid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSex(resultSet.getString("sex"));
                user.setAge(resultSet.getInt("age"));
                user.setBirthday(resultSet.getString("birthday"));
                users.add(user);
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
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return users;
        }
    }

    @Override
    public Integer userInfoInsertDao(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://101.132.138.215/servlet?characterEncoding=utf-8&useSSL=false", "root", "yingkun9257");
            String sql = "insert into t_user(username,password,sex,age,birthday) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setObject(4,user.getAge(),Types.INTEGER);
            preparedStatement.setObject(5,user.getBirthday(),Types.VARCHAR);
            index = preparedStatement.executeUpdate();
            logger.debug("插入语句的返回值："+index);
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
            return index;
        }
    }
}
