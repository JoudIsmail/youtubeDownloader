package gui.mvp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Model {
    private String status;
    private Presenter presenter;
    
    
    

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void download(String args) throws InterruptedException {
        String s = null;
        try {
            //"python script.py this is"
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader stdInput = new BufferedReader(new 
                            InputStreamReader(p.getInputStream()));

                       BufferedReader stdError = new BufferedReader(new 
                            InputStreamReader(p.getErrorStream()));

                       // read the output from the command
                       System.out.println("Here is the standard output of the command:\n");
                       
                       while ((s = stdInput.readLine()) != null) {
                           System.out.println(s);
                           progress(s);
                           callOnFinished(s);
                       }
                       
                       // read any errors from the attempted command
                       System.out.println("Here is the standard error of the command (if any):\n");
                       while ((s = stdError.readLine()) != null) {
                           System.out.println(s);
                       }
                       
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public void progress(String string) {
        if (string.matches("\\d+")) {
            int progress = Integer.valueOf(string);
            System.out.println("working");
            presenter.refresh(progress);
            }
    }
    
    public void callOnFinished(String string) {
        status = string;
    }
    
    public String getProgress() {
        return status;
    }
}
