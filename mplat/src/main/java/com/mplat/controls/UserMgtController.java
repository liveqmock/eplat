/**
 * obullxl@gmail.com
 */
package com.mplat.controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.PopupWindowBuilder;

/**
 * @author obullxl@gmail.com
 */
public class UserMgtController implements Initializable {

    @FXML
    private TableView<Person> tabUsers;
    @FXML
    private TableColumn colUserID;
    @FXML
    private TableColumn colUserName;
    private JavaFX application;
    private int idex = 1;
    private final ObservableList<Person> data = FXCollections.observableArrayList(
            new Person(1L, "Smith"),
            new Person(2L, "测试"),
            new Person(3L, "老牛啊"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colUserID.setStyle("-fx-text-align:right;-fx-color:red");

        this.colUserID.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        this.colUserName.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

        this.tabUsers.setItems(data);

        this.tabUsers.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getButton() == MouseButton.PRIMARY) {
                    Person person = tabUsers.getSelectionModel().getSelectedItem();
                    if (person != null) {
                        System.out.println("左:点击了用户：" + person.getUserName());
                    }
                    data.add(new Person(1L, "随机用户" + idex++));
                } else if (evt.getButton() == MouseButton.SECONDARY) {
                    System.out.println("右:点击了用户。");
                    data.remove(0);
                }
            }
        });
    }

    public void setApp(JavaFX application) {
        this.application = application;
    }

    public static class Person {

        private final SimpleLongProperty id;
        private final SimpleStringProperty userName;

        private Person(long id, String userName) {
            this.id = new SimpleLongProperty(id);
            this.userName = new SimpleStringProperty(userName);
        }

        public long getId() {
            return id.get();
        }

        public void setId(long id) {
            this.id.set(id);
        }

        public String getUserName() {
            return userName.get();
        }

        public void setUserName(String userName) {
            this.userName.set(userName);
        }
    }
}
