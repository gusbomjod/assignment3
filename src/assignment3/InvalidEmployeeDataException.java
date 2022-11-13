package assignment3;
public class InvalidEmployeeDataException extends RuntimeException{
    public InvalidEmployeeDataException(String message){
        super(message);
    }
    public InvalidEmployeeDataException(){super("Invalid input");}
}

