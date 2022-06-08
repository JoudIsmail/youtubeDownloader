package gui.mvp;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View extends GridPane{
    private Presenter presenter;
    private ToggleGroup toggleGroup;
    private TextField textField;
    private RadioButton high;
    
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    public void initView() {
        Button button = new Button("Download");
        button.setOnAction(e -> presenter.download());
        textField = new TextField();
        HBox hBox = new HBox(5);
        toggleGroup = new ToggleGroup();
        high = new RadioButton("Highest Reselution");
        RadioButton low = new RadioButton("Lowest Reselution");
        RadioButton audio = new RadioButton("Only Audio");
        high.setToggleGroup(toggleGroup);
        low.setToggleGroup(toggleGroup);
        audio.setToggleGroup(toggleGroup);
        high.setUserData("a");
        low.setUserData("b");
        audio.setUserData("c");
        high.setSelected(true);
        hBox.getChildren().addAll(high, low, audio);
        textField.setPromptText("Enter Video Url To Download");
        this.add(textField, 0, 1);
        this.add(button, 2, 1);
        this.add(hBox, 0, 2);
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(10));
    }
    
    public void alert() {
        Alert alert = new Alert(AlertType.NONE,
                        "Download Completed!",ButtonType.OK);
        alert.show();
    }
    
    public void reset() {
        textField.clear();
        high.setSelected(true);
    }
    
    public String getSelectedOption() {
        RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (radioButton != null) {
            return radioButton.getUserData().toString();
        }
        return "";
    }
    
    public String getTextField() {
        return textField.getText();
    }
}
