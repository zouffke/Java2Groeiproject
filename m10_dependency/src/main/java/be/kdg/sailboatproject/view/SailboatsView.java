package be.kdg.sailboatproject.view;

import be.kdg.sailboatproject.model.Sailboat;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.logging.Logger;


public class SailboatsView extends BorderPane {
    private static final Logger L = Logger.getLogger(SailboatsView.class.getName());
    private TableView tableView;
    private TextField tfName;
    private TextField tfLength;
    private DatePicker dpBuild;
    private Button savebtn;

    @SuppressWarnings("unchecked")
    public SailboatsView() {
        L.info("Constructing SailboatsView");
        tableView = new TableView<>();
        tfName = new TextField();
        tfName.setPromptText("Name");
        tfLength = new TextField();
        tfLength.setPromptText("Length");
        dpBuild = new DatePicker();
        dpBuild.setPromptText("Build year");
        savebtn = new Button("Save");
        savebtn.setMinWidth(Button.USE_PREF_SIZE);
        super.setCenter(tableView);
        BorderPane.setMargin(tableView, new Insets(10));
        TableColumn<String, Sailboat> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<LocalDate, Sailboat> column2 = new TableColumn<>("Build Year");
        column2.setCellValueFactory(new PropertyValueFactory<>("buildYear"));
        TableColumn<String, Sailboat> column3 = new TableColumn<>("Length");
        column3.setCellValueFactory(new PropertyValueFactory<>("length"));
        tableView.getColumns().addAll(column1, column2, column3);
        HBox hbBottom = new HBox(tableView, dpBuild, tfLength, tfName, savebtn);
        hbBottom.setSpacing(10);
        super.setBottom(hbBottom);
        BorderPane.setMargin(hbBottom, new Insets(10));
    }

    TableView<Sailboat> getTableView() {
        return this.tableView;
    }

    TextField getTfName() {
        return this.tfName;
    }

    TextField getTfLength() {
        return this.tfLength;
    }

    Button getSavebtn() {
        return this.savebtn;
    }

    DatePicker getDpBuild() {
        return this.dpBuild;
    }
}
