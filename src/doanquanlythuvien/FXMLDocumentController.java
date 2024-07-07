/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package doanquanlythuvien;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField studentNumber;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_Btn;

    @FXML
    private Button close;

    @FXML
    private Button minimize;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    public void login(){
        
        String sql = "SELECT * FROM student WHERE studentNumber = ? and password = ?";
        
        connect = Database.connectDB();
        
        try{
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentNumber.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(studentNumber.getText().isEmpty() || password.getText().isEmpty()){
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin.");
                alert.showAndWait();
            }else{
                if(result.next()){
                    
                    getData.studentNumber = studentNumber.getText();
   
                    getData.path = result.getString("image");
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công!");
                    alert.showAndWait();
                    
                    login_Btn.getScene().getWindow().hide();
                    

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    
                    Stage stage = new Stage();
                    
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        
                        x = event.getSceneX();
                        y = event.getSceneY();
                        
                    });
                    
                    root.setOnMouseDragged((MouseEvent event) ->{
                       
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                        
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin tài khoản hoặc mật khẩu sai.");
                    alert.showAndWait();
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    

    public void numbersOnly(KeyEvent event){
        
        if(event.getCharacter().matches("[^\\e\t\r\\d+$]")){
            event.consume();
            
            studentNumber.setStyle("-fx-border-color:#e04040");
        }else{
            studentNumber.setStyle("-fx-border-color:#fff");
        }
        
    }
    
    
    @FXML
    public void minimize(){
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    public void exit(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
