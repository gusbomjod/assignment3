package assignment3;

public class RepeatedIDException extends RuntimeException{
    public RepeatedIDException(String message){
        super(message);
    }
    public RepeatedIDException(){super("Cannot register. The ID is already registered.");}
}
