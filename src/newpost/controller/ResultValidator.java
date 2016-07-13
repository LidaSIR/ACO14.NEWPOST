package newpost.controller;

/**
 * Created by Agryzkov on 13.07.2016.
 */
public class ResultValidator {

    private boolean err;
    private String textErr;


    public boolean getErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public String getTextErr (){
        return textErr;
    }

    public void setTextErr(String textErr){
        this.textErr = textErr;
    }
}
