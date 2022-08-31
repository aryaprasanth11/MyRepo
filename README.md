# Springboot-securitylogin-user

An Employee Custom login page with security user using Spring boot security,jsp and JDBC Authentication.Perform certain Create,Update and Retrive operation 
on user records contain information such as empid and name

Reference:https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

File hierarchy:
•	**src/main/java**

 ** model**
 
   o	**Employee.java**: the model that maps to 'employee' details
 
   o **UserRegistration**.java: The model that for a new registration fields

 **	dao**
 
   o	**EmployeeDAO.java**: handles basic database operations -- create new user, update user , retrieve all users

 	**controller**
 
   o	****EmployeeController.java**: handles requests from jsp pages. Dispatches database operations specified in EmployeeDao, or redirect to pages depends on the request      received
 
 o	**UserController.java**: Handles the request from jsp pages foe registration of new user and save it to the database 

 	**Config**
 
   o  **AppConfig.java**: Establish the database connection
 
   o	**EmployeeSecurityConfig.java**: Provides the security configurations to give authorizations to do operations.

•**src/main/resources**

   o	**application.properties** : Contains the database connection information

•**src/main/webapp**
 
   o	**addEmployee.jsp**: For creating a new Employee
 
   o	**getEmployee.jsp**: a view layer for display all the employee details

   o	**login.jsp**: for customized login page
 
   o	**menu.jsp**: view layer to display after logged in page
 
   o	**registration**.jsp: view layer for registration form
 
   o	**welcome.jsp**: to display the welcome message in logged in page
