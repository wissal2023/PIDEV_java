package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.MyConnection;

public class AfficherDashboardController implements Initializable {

    @FXML
    private ImageView imgDoc;
    @FXML
    private TextField Txt_nom;
    @FXML
    private TextField Txt_special;
    @FXML
    private TextField Txt_adress;
    @FXML
    private TextField Txt_mail;
    @FXML
    private TextField Txt_tel;
    
    @FXML
    private BarChart<String, Number> barChart;    
    @FXML
    private TextField nb_Med;
    @FXML
    private TextField nb_Tot_Consult;   
    @FXML
    private TextField Rev;
    
    private final Connection cnx;
    private PreparedStatement pste;
    
    public AfficherDashboardController() {
        MyConnection bd = MyConnection.getInstance();
        cnx=bd.getCnx();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Image image = new Image(getClass().getResourceAsStream("imgDoc.jpg"));
        //imgDoc.setImage(image);       
        //nb_Med.setText("0");
        String query = "SELECT u.nom, u.specialite, u.adresse, u.email, u.num_tel FROM User u WHERE u.roles LIKE '[\"medcin\"]'";
        try {
            pste = cnx.prepareStatement(query);
            ResultSet rs = pste.executeQuery();
            if(rs.next()) {
                String nom = rs.getString("nom");
                String specialite = rs.getString("specialite");
                String adresse = rs.getString("adresse");
                String email = rs.getString("email");
                String num_tel = rs.getString("num_tel");
                
                Txt_nom.setText(nom);
                Txt_special.setText(specialite);
                Txt_adress.setText(adresse);
                Txt_mail.setText(email);
                Txt_tel.setText(num_tel);
            }
        } catch(SQLException ex) {
            System.out.println("Error while fetching doctor information: " + ex.getMessage());
        }
        try {
            String queryStat = "SELECT COUNT(*) AS nbConsultations, SUM(prix) AS totalPrix FROM consultation";
            pste = cnx.prepareStatement(queryStat);
            ResultSet rs = pste.executeQuery();
            if (rs.next()) {
                nb_Tot_Consult.setText(Integer.toString(rs.getInt("nbConsultations")));
                Rev.setText(Double.toString(rs.getDouble("totalPrix")));
            }
            pste.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       try {
            String queryChartData = "SELECT date, COUNT(*) AS nbConsultations FROM rendez_vous GROUP BY date";
            pste = cnx.prepareStatement(queryChartData);
            ResultSet rs = pste.executeQuery();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Consultations par jour");
            while (rs.next()) {
                String date = rs.getString("date");
                int nbConsultations = rs.getInt("nbConsultations");
                series.getData().add(new XYChart.Data<>(date, nbConsultations));
            }
            barChart.getData().add(series);
            pste.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    // ************************ BUTTON CONSULTATION*********************************
    @FXML
    private void afficherConsultation(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherConsultation.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}

