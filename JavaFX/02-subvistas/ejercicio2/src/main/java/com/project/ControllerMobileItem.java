package com.project;

import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerMobileItem {

    @FXML
    private Button buttonBack;

    @FXML
    private AnchorPane colorPanel;

    @FXML
    private Label labelTitle;

    @FXML
    private VBox paneItem;

    @FXML
    private VBox viewList;

    @FXML
    void viewBack(ActionEvent event) {
        UtilsViews.setViewAnimating("Mobile");
    }

    public void populateList(JSONArray jsonInfo, String title) {
        viewList.getChildren().clear();
        labelTitle.setText(title);

        try {
            URL resource = getClass().getResource("/assets/layoutMobileItemRow.fxml");

            for (int i = 0; i < jsonInfo.length(); i++) {
                JSONObject jsonData = jsonInfo.getJSONObject(i);

                String name = jsonData.getString("name");
                String imageFile = jsonData.getString("image");

                if (!imageFile.toLowerCase().endsWith(".png")) {
                    continue;
                }

                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerMobileItemRow rowController = loader.getController();

                rowController.setParentController(this);
                rowController.setJsonData(jsonData);
                rowController.setLabelTitle(name);
                rowController.setImgItem("/assets/images/" + imageFile);

                viewList.getChildren().add(itemTemplate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showItemDetails(JSONObject jsonData) {
        ControllerMobileItemDetails detailController =
            (ControllerMobileItemDetails) UtilsViews.getController("MobileItemDetail");

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

        UtilsViews.setViewAnimating("MobileItemDetail");
    }

}
