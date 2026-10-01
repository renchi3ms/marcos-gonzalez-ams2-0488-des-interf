package com.project;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ControllerMobile {

    @FXML
    private AnchorPane colorPanel;

    @FXML
    private HBox paneChar;

    @FXML
    private HBox paneConsoles;

    @FXML
    private HBox paneGames;

    private JSONArray jsonInfo;

    @FXML
    void changesView(MouseEvent event) throws URISyntaxException, IOException {

        String jsonFile;
        String title;

        HBox source = (HBox) event.getSource();

        if (source == paneChar) {
            jsonFile = "/assets/characters.json";
            title = "Personatges";
        } else if (source == paneGames) {
            jsonFile = "/assets/games.json";
            title = "Jocs";
        } else {
            jsonFile = "/assets/consoles.json";
            title = "Consoles";
        }

        URL jsonFileURL = getClass().getResource(jsonFile);
        Path path = Paths.get(jsonFileURL.toURI());
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        jsonInfo = new JSONArray(content);

        ControllerMobileItem itemController = (ControllerMobileItem) UtilsViews.getController("MobileItem");
        itemController.populateList(jsonInfo, title);
        UtilsViews.setViewAnimating("MobileItem");
    }

}
