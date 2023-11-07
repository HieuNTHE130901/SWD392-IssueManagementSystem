/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ClassDAO;
import java.util.List;
import model.Class;

/**
 *
 * @author ADMIN
 */
public class ClassService {
    private ClassDAO classDAO;

    public List<Class> getAllClassForCurrentTeacher(int userId) {
      return classDAO.getClassForTeacher(userId);
    }
    
}
