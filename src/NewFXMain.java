
import controllers.MedecinDashbordController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("./gui/MedecinDashbord.fxml"));
            Scene scene = new Scene(root);
            //AfficherPlanningController controller = loader.getController();

            primaryStage.setScene(scene);
            primaryStage.setTitle("Medcare");
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}