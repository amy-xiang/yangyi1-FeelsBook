package ca.ualberta.yangyi1_feelsbook;

public class CommentTooLongException extends Exception {

    CommentTooLongException(){
        super("This message is too long! Keep your comments to within 100 characters");
    }
}
