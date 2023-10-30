/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author trung
 */
import dao.UserDAO;
import java.sql.SQLException;
import model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User login(String emailOrMobile, String password) {
        User user = null;
        if (emailOrMobile.contains("@")) {
            // Authenticate by email
            user = userDAO.loginWithEmail(emailOrMobile, password);
        } else if (emailOrMobile.matches("\\d{10}")) {
            // Authenticate by mobile
            user = userDAO.loginWithMobile(emailOrMobile, password);
        }
        return user;
    }

    public boolean registerUser(String fullName, String emailOrMobile, String password, String confirmPassword) {
        if (isValidEmail(emailOrMobile)) {
            // It's an email
            User newUser = new User();
            newUser.setFullName(fullName);
            newUser.setEmail(emailOrMobile);
            newUser.setPassword(password);

            if (password.equals(confirmPassword)) {
                return userDAO.registerByEmail(newUser);
            }
        } else if (isValidMobile(emailOrMobile)) {
            // It's a mobile number
            User newUser = new User();
            newUser.setFullName(fullName);
            newUser.setMobile(emailOrMobile);
            newUser.setPassword(password);

            if (password.equals(confirmPassword)) {
                return userDAO.registerByMobile(newUser);
            }
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isValidMobile(String mobile) {
        String mobileRegex = "^[0-9]{10}$";
        return mobile.matches(mobileRegex);
    }

    public int getTeacherIdForUser(int assigneeId) throws SQLException {
        int teacherId = userDAO.getTeacherIdForUser(assigneeId);
        return teacherId;
    }

}
