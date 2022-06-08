package gui.mvp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Model {
    public void download(String args) {
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
                       }
                       
                       // read any errors from the attempted command
                       System.out.println("Here is the standard error of the command (if any):\n");
                       while ((s = stdError.readLine()) != null) {
                           System.out.println(s);
                       }
                       
                       System.exit(0);
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
