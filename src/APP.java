import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class APP extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 1500, 750);
        stage.setScene(scene);
        stage.show();

        mainView.draw();

    }

    public static void main(String[] args){
        launch();
    }


}
