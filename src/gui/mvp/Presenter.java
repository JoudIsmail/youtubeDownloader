package gui.mvp;

import javafx.scene.layout.GridPane;

public class Presenter {
    private View view;
    private Model model;
    
    public void setView(View view) {
        this.view = view;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    public GridPane getUI() {
        return this.view;
    }
    
    public void download() {
        String string = "python script.py ";
        String url = view.getTextField();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string + url);
        String downloadOption = view.getSelectedOption();
        stringBuilder.append(" ");
        stringBuilder.append(downloadOption);
        if (!downloadOption.isEmpty()) {
            model.download(stringBuilder.toString());
        }
    }
}