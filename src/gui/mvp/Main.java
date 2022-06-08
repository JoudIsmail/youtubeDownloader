package gui.mvp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Presenter presenter = new Presenter();
        View view = new View();
        Model model = new Model();
        
        presenter.setView(view);
        presenter.setModel(model);
        view.setPresenter(presenter);
        view.initView();
        
        Scene scene = new Scene(presenter.getUI());
        primaryStage.setScene(scene);
        primaryStage.setTitle("YOUTUBE Downloader");
        primaryStage.show();
    }

}
