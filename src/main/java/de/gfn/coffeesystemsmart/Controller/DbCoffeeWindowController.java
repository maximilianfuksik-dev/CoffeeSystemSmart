package de.gfn.coffeesystemsmart.Controller;

import de.gfn.coffeesystemsmart.Classes.CoffeeEntity;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

import static de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository.findAllCoffee;
import static de.gfn.coffeesystemsmart.Repository.SmartCoffeeRepository.getPricebyCoffeeID;

public class DbCoffeeWindowController {

    @FXML
    private TableView<CoffeeEntity> coffeeTable;

    @FXML
    private TableColumn<CoffeeEntity, Integer> coffeeTypeID;

    @FXML
    private TableColumn<CoffeeEntity, String> coffeeName;

    @FXML
    private TableColumn<CoffeeEntity, Double> coffeePrice;

    @FXML
    private TableColumn<CoffeeEntity, Integer> coffeeConLiter;

    public void initialize() throws SQLException {
        coffeeTypeID.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        coffeeName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        coffeePrice.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        coffeeConLiter.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMLiter()));
        try {
            coffeeTable.getItems().setAll(findAllCoffee());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
