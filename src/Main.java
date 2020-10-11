import Model.MyModel;
import View.MyViewController;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        MyModel model = new MyModel();

        model.startServers();
        MyViewModel viewModel = new MyViewModel(model);
        model.addObserver(viewModel);

        primaryStage.setTitle("Let's Play");

        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.setMaximized(true);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View/MyView.fxml").openStream());
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        scene.getStylesheets().add(getClass().getResource("/View/MyViewStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        MyViewController view = fxmlLoader.getController();
//        primaryStage.setOnCloseRequest(e -> {
//            e.consume();
//            view.exitGame();
//            closeProgram(model, primaryStage);
//        });
        view.setPrimaryStageAndScene(primaryStage, scene);
        view.setResizeEvent(scene);
        view.setViewModel(viewModel);
        viewModel.addObserver(view);

        view.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram(MyModel model, Stage primaryStage) {
        model.stopServers();
        primaryStage.close();

    }
}


//    private void SetStageCloseEvent(Stage primaryStage) {
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            public void handle(WindowEvent windowEvent) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK){
//                    // ... user chose OK
//                    // Close program
//                } else {
//                    // ... user chose CANCEL or closed the dialog
//                    windowEvent.consume();
//                }
//            }
//        });




