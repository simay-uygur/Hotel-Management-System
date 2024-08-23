package view;

public class GUIController {
    AdminPage adminPage;
    EmployeePage employeePage;


    public void loadAdminPage(){
        adminPage = new AdminPage();
    }

    public void loadEmployeePage(){
        employeePage = new EmployeePage();
    }
}
