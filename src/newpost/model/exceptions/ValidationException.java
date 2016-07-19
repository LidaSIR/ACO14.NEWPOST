package newpost.model.exceptions;

/**
 * Created by Vladislav on 16.07.2016.
 */
public class ValidationException extends Throwable {

    public ValidationException(String message){
        super(message);
    }
}
