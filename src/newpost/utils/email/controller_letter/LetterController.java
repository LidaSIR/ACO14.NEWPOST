package newpost.utils.email.controller_letter;

import newpost.utils.email.model_letter.Letter;

/**
 * Created by macaque on 24.07.2016.
 */
public class LetterController implements ILetterController{

    @Override
    public Letter[] showNewLetters() {
        return new Letter[0];
    }
}
