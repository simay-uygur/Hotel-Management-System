package view;

import core.Helper;

public class GUIController {
    AdminPage adminPage;
    EmployeePage employeePage;


    public void loadAdminPage(){
        adminPage = new AdminPage();
    }

    public void loadEmployeePage(){
        employeePage = new EmployeePage();
    }

    public void loadNotSuccessfullEntrancePage(){
        Helper.showMessage("The information you entered does not match with any user. Please try again.");
    }
}
