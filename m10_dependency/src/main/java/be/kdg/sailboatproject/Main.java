package be.kdg.sailboatproject;

import be.kdg.sailboatproject.database.SailboatDbDao;
import be.kdg.sailboatproject.service.SailboatsServiceImpl;
import be.kdg.sailboatproject.view.SailboatsPresenter;
import be.kdg.sailboatproject.view.SailboatsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Main extends Application {
    private static final Logger L = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        L.info("Starting Sailboat Management System");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        L.info("Running start method on thread: " + Thread.currentThread().getName());
        SailboatsView sailboatsView = new SailboatsView();
        new SailboatsPresenter(sailboatsView,
                new SailboatsServiceImpl(SailboatDbDao.getInstance()));
        stage.setScene(new Scene(sailboatsView));
        stage.show();
    }
}
