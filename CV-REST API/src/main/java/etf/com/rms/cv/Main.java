/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etf.com.rms.cv;

import data.Company;
import data.Contact;
import data.Education;
//import data.Employment;
import data.User;
import exception.MyException;
import java.util.ArrayList;
import java.util.List;
//import service.EmploymentService;
import service.UserService;

/**
 *
 * @author predr
 */
/*public class Main {
    private static final UserService userService=UserService.getInstance();
    private static final EmploymentService employmentService=EmploymentService.getInstance();
    public static void main(String[] args) throws Exception{
        
          //addTestUsers();
          //addCompany();
         //addEducation();
         // addEmployments();
    //deleteTestUsers(); 
   
 //companies(4);
 //companies();
// findEmployment();
    
    }
    private static void deleteTestUsers() throws MyException {//RADI
        userService.deleteUser(2);
        
    }
    private static void deleteContact()throws MyException{
        userService.deleteContact(2);
    }
    
    private static void addTestUsers()throws MyException{//RADI
        Education education = new Education("ETF", "ETF", "/","English,Russian","XIII beogradska");
        Contact contact = new Contact("0631915842", "predrag.ivancevic@gmail.com","peleLinkedin","peleSkype");
        User user = new User("Predrag", "Ivancevic","electrical engineer student", education, contact);

        userService.addNewUser(user);

    }
    private static void addEmployments()throws MyException{//RADI
       Education education = new Education("Matf", "ETF", "Oxford","English,Spanish","XIII beogradska");
        Contact contact = new Contact("0631555", "draganm@gmail.com","gagiLinkedin","gagiSkype");
       
        User user1=new User("Dragan","Subotic","mechanical engineer",education,contact);
        
        Company company1=new Company("Linkedin","C++ developer","2018-19");
        Employment employment=new Employment(user1,company1);
        
        
        employmentService.addNewEmployment(employment);
    }
    
   private static void findEmployment()throws MyException{
       System.out.println(employmentService.findEmployment(2));
   }
    private static void companies()throws MyException{
        System.out.println(employmentService.findEmployments(3));
    }
    
}
*/
