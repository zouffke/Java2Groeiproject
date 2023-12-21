package be.kdg.sailboatproject.view;

import be.kdg.sailboatproject.exceptions.SailboatException;
import be.kdg.sailboatproject.model.Sailboat;
import be.kdg.sailboatproject.service.SailboatsService;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.logging.Logger;

public class SailboatsPresenter {

    private static Logger L = Logger.getLogger(SailboatsPresenter.class.getName());
    private SailboatsView sailboatsView;
    private SailboatsService sailboatsService;

    public SailboatsPresenter(SailboatsView sailboatsView, SailboatsService sailboatsService) {
        this.sailboatsView = sailboatsView;
        this.sailboatsService = sailboatsService;
        loadSailboats();

        sailboatsView.getSavebtn().setOnAction(event -> {
            Sailboat sailboat = new Sailboat(
                    sailboatsView.getTfName().getText(),
                    Integer.parseInt(sailboatsView.getTfLength().getText()),
                    sailboatsView.getDpBuild().getValue()
            );
            sailboatsService.addSailboat(sailboat);
            loadSailboats();
        });
    }

    private void loadSailboats() {
        L.info("Loading list of sailboats");
        try {
            List<Sailboat> myList = sailboatsService.getAllSailboats();
            sailboatsView.getTableView().setItems(FXCollections.observableList(myList));
        } catch (SailboatException sailboatException) {
            L.warning("Unable to load sailboats: " + sailboatException);
            new Alert(Alert.AlertType.ERROR, "Unable to load sailboats:\n" + sailboatException.getMessage()).showAndWait();
        }
    }
}
