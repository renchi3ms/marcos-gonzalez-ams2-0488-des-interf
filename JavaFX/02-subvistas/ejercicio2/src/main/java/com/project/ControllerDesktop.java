package com.project;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerDesktop implements Initializable{

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label labeTitle;

    @FXML
    private Pane paneTitle;

    @FXML
    private VBox vPaneSelectItem;

    @FXML
    private VBox vboxList;

    private JSONArray jsonInfo;

    private JSONObject currentSelectedJson;

    public void showItemDetails(JSONObject jsonData) {
        vPaneSelectItem.getChildren().clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/layoutItemDetail.fxml"));
            Parent detailView = loader.load();

            ControllerItemDetail detailController = loader.getController();

            String name = jsonData.getString("name");
            String plot = "";
            
            if (jsonData.has("plot")) {
                plot = jsonData.getString("plot");
            } else if (jsonData.has("game")) {
                plot = jsonData.getString("game");
            } else if (jsonData.has("procesador")) {
                plot = jsonData.getString("procesador");
            }
            
            String imageFile = jsonData.getString("image");

            detailController.setName(name);
            detailController.setPlot(plot);
            detailController.setImage(imageFile);

            if (jsonData.has("color")) {
                String color = jsonData.getString("color");
                detailController.setColorCircle(color, true);
            } else {
                detailController.setColorCircle(null, false);
            }

            vPaneSelectItem.getChildren().add(detailView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleComboBoxAction(ActionEvent event) {
        String selected = comboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.equals("Jocs")) {
                
                vboxList.getChildren().clear();

                try {
                    URL resource = this.getClass().getResource("/assets/layoutItem.fxml");

                    URL jsonFileURL = getClass().getResource("/assets/games.json");
                    Path path = Paths.get(jsonFileURL.toURI());
                    String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                    jsonInfo = new JSONArray(content);

                    setPaneList(jsonInfo, resource);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (selected.equals("Personatges")) {

                vboxList.getChildren().clear();

                try {
                    URL resource = this.getClass().getResource("/assets/layoutItem.fxml");

                    URL jsonFileURL = getClass().getResource("/assets/characters.json");
                    Path path = Paths.get(jsonFileURL.toURI());
                    String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                    jsonInfo = new JSONArray(content);

                    setPaneList(jsonInfo, resource);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (selected.equals("Consoles")) {

                vboxList.getChildren().clear();
                
                try {
                    URL resource = this.getClass().getResource("/assets/layoutItem.fxml");

                    URL jsonFileURL = getClass().getResource("/assets/consoles.json");
                    Path path = Paths.get(jsonFileURL.toURI());
                    String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                    jsonInfo = new JSONArray(content);

                    setPaneList(jsonInfo, resource);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        comboBox.getItems().addAll("Jocs", "Personatges", "Consoles");
        comboBox.getSelectionModel().select(0);
        comboBox.setOnAction(this::handleComboBoxAction);
        handleComboBoxAction(null);
    }

    private void setPaneList(JSONArray jsonInfo, URL resource) throws IOException {
        for (int i = 0; i < jsonInfo.length(); i++) {

            JSONObject jsonData = jsonInfo.getJSONObject(i);

            String name = jsonData.getString("name");
            String imageFile = jsonData.getString("image");

            if (!imageFile.toLowerCase().endsWith(".png")) {
                continue;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerItem itemController = loader.getController();

            itemController.setParentController(this);
            itemController.setJsonData(jsonData);
            itemController.setLabelTitle(name);
            itemController.setImgItem("/assets/images/" + imageFile);

            vboxList.getChildren().add(itemTemplate);

        }
    }

}
