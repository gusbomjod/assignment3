package assignment3;
public class EmployeeEmptyException extends RuntimeException{
    public EmployeeEmptyException(String message){super(message);}
    public EmployeeEmptyException(){super("No employees registered yet.");}
}
