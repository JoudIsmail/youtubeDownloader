package gui.mvp;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends VBox{
    private Presenter presenter;
    private ToggleGroup toggleGroup;
    private TextField textField;
    private RadioButton high;
    private ProgressBar progressBar;
    ProgressIndicator pi;
    private SimpleIntegerProperty progress = new SimpleIntegerProperty();
    
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    public void setProg(int num) {
        progressBar.setProgress((float)(num/100f));
        pi.setProgress((float)(num/100f));
    }
    
    public void initView() {
        GridPane gridPane = new GridPane();
        Button button = new Button("Download");
        button.setOnAction(e -> {
            try {
                presenter.download();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        textField = new TextField();
        HBox hBox = new HBox(5);
        toggleGroup = new ToggleGroup();
        high = new RadioButton("Highest Resolution");
        RadioButton low = new RadioButton("Lowest Resolution");
        RadioButton audio = new RadioButton("Only Audio");
        HBox hBox2 = new HBox();
        progress.setValue(0);
        progressBar = new ProgressBar(0f);
        pi = new ProgressIndicator(0f);
        hBox2.getChildren().addAll(progressBar, pi);
        high.setToggleGroup(toggleGroup);
        low.setToggleGroup(toggleGroup);
        audio.setToggleGroup(toggleGroup);
        high.setUserData("a");
        low.setUserData("b");
        audio.setUserData("c");
        high.setSelected(true);
        hBox.getChildren().addAll(high, low, audio);
        textField.setPromptText("Enter Video Url To Download");
        gridPane.add(textField, 0, 1);
        gridPane.add(button, 2, 1);
        gridPane.add(hBox, 0, 2);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));
        this.getChildren().addAll(gridPane, hBox2);
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
