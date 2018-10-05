package ca.ualberta.yangyi1_feelsbook;

public class CommentTooLongException extends Exception {
    // Creates an exception for comments over 100 chars

    CommentTooLongException(){
        super("This message is too long! Keep your comments to within 100 characters");
    }
}
