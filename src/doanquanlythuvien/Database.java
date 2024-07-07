/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package doanquanlythuvien;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author truongthanhnam
 */
public class Database {
    
    public static Connection connectDB(){
        try{
        Class.forName("com.mysql.jdbc.Driver");

        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/DataThuVien", "root", "namnam123");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        
        return null;
    }
    
}
