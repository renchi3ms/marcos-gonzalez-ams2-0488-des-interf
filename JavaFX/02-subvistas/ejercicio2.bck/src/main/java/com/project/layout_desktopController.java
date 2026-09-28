package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class layout_desktopController implements Initializable{

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private HBox hBoxContent;

    @FXML
    private ImageView imageContent;

    @FXML
    private Label labeTitle;

    @FXML
    private Label labelDescriptionGame;

    @FXML
    private Label labelTittleGame;

    @FXML
    private VBox vboxList;

    @FXML
    private Pane paneTitle;

    @FXML
    private ScrollPane scrollContent;

    @FXML
    private VBox vBoxContent;

    @FXML
    private VBox vBoxGeneral;

    private JSONArray jsonInfo;

    void handleComboBoxAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/assets/layout_item.fxml");

            URL jsonFileURL = getClass().getResource("/home/renchi/Documentos/GIT-REPS/marcos-gonzalez-ams2-0488-des-interf/JavaFX/02-subvistas/ejercicio2/data/characters.json");
            Path path = Paths.get(jsonFileURL.toURI());
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            jsonInfo = new JSONArray(content);

            for (int i = 0; i < jsonInfo.length(); i++) {
                // Obtenir l'objecte JSON individual (animal)
                JSONObject game = jsonInfo.getJSONObject(i);

                // Extreure la informació necessària del JSON
                String name = game.getString("name");
                String plot = game.getString("plot");
                String img = game.getString("image");

                // Carregar el template de 'listItem.fxml'
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                Controller_Item itemController = loader.getController();

                // Assignar els valors als controls del template
                itemController.setLabelGame(name);
                itemController.setImgGame("/home/renchi/Documentos/GIT-REPS/marcos-gonzalez-ams2-0488-des-interf/JavaFX/02-subvistas/ejercicio2/data/images/" + name.toLowerCase() + ".png");

                // Afegir el nou element a 'yPane'
                vboxList.getChildren().add(itemTemplate);

                
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String selected = comboBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.equals("Jocs")) {
                
            } else if (selected.equals("Personatges")) {

            } else if (selected.equals("Consoles")) {

            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        comboBox.getItems().addAll("Jocs", "Personatges", "Consoles");
        comboBox.getSelectionModel().select(0);
        comboBox.setOnAction(this::handleComboBoxAction);
    }

}
