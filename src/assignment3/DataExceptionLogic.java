package assignment3;

public class DataExceptionLogic {
    public void checkIfEmployeeCanBeCreated(String employeeID, String name, double grossSalary) throws Exception {
        checkIfIdIsBlank(employeeID);
        checkIfNameIsAllowed(name);
        checkIfSalaryIsAllowed(grossSalary);
    }
    public void checkIfIdIsBlank(String employeeID)throws Exception {
        if (employeeID.isEmpty() || employeeID.isBlank()) {
            throw new InvalidEmployeeDataException("ID cannot be blank.");
        }
    }

    public void checkIfNameIsAllowed(String name) throws Exception {
        if (name.isEmpty() || name.isBlank()) {
            throw new InvalidEmployeeDataException("Name cannot be blank.");
        }
    }
    public void checkIfSalaryIsAllowed(double grossSalary)throws Exception{
        if (grossSalary <= 0) {
            throw new InvalidEmployeeDataException("Salary must be greater than zero.");
        }
    }
    public void checkIfDegreeIsAllowed(String degree)throws Exception {
        switch (degree) {
            case "BSc":
                break;
            case "MSc":
                break;
            case "PhD":
                break;
            default:
                throw new InvalidEmployeeDataException("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }    public void checkIfDepartmentIsAllowed(String depart)throws Exception {
        switch (depart) {
            case "Business":
                break;
            case "Human Resources":
                break;
            case "Technical":
                break;
            default:
                throw new InvalidEmployeeDataException("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }
    public void checkIfGPAIsAllowed(int GPA){
        if(GPA >10 || GPA < 0){
            throw new InvalidEmployeeDataException( GPA + " outside range. Must be between 0-10.");

        }

    }
}
