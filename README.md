# 350GroupProjectSimple startup (for local testing)
Download the OLS_GP_jar folder (which contains the OLS_GP.jar and run.bat files)
Double click on run.bat.
Wait for sserver to start
Enter the local address http://127.0.0.1:8081/ in your browser
Successfully enter the page
Two files in the same directory as the folder OLS_GP
NormalVERTestStarter.jar unzipped and run the launcher to open the Server
WithProxyVersion.jar unzipped and run the launcher to test
The two files can not be opened at the same time, the address will be a conflict!

#
Users for testing:
Staff1:
ID: 683474
password：abc123

Staff2:
ID：729231
password: abc123

Staff3:
ID：10890
password：abc123

Student：
ST1：
ID：294222
password：200755

ST2：
ID：740517
password：123123
ST3：
ID：792409
password：111111

//
When insert a new Student, Update information is required, link can be found in login page.
//

Technology been used:
Thymeleaf-2.7.18 (Apache)
Springboot-2.7.18
Mongodb
//System operating environment 
Requirements:
AdoptOpenJDK 1.8 https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk&version=8

IntelliJ IDEA 2023.2.5(only if we need to change the source file)

Network Connection:
For mongoDB connection, all data in the server will storing in mongodb.

Additional Server:
(a single server is enough without them:)
Nginx for proxy to convert dynamic page to static page. (Because Haproxy only allow static server.)

Haproxy to balance multiple Nginx server when require large amount of server.

 
Libraries:
springframework.boot.spring.starter.web
springframework.boot.spring.starter.thymeleaf
springframework.boot.spring.starter.data.mongodb
springframework.boot.spring.devtools

Pramater for users:
Admin(String id, String name, String email, String password, String role);
Staff(String id, String name, String passportNumber, String status, String email, String password);
Student(String id, String name, String passportNumber, LocalDate dateOfBirth, String password, Boolean checked);
Subject(String id, String name);

Controller.Dashboard:
Dashboard
StaffList
StudentList
SubjectList
SearchSystem
First(Time)Login
StudentUpdateInfo(For student who login the system first time, student can update their info.)

Controller.LoginController:
Login
Logout
Redirect

Service.DashboardService:
Provided Service used by Controller.Dashboard.

Service.LoginService:
Provided Service used by Controller.LoginController.

Other Controller.*:
Provided API for RESTful API
Other Service.*:
Provided API for Controller.*

Detail of Dashboard Page and LoginPage:
Login:
1.By using Thymeleaf, when user login the system, springboot will check the session id, if the session id is empty, user will be redirect to /login page.

2.when the user login the systems as a student, Controller.Dashboard will generate a HttpSession session and response to the client, the session will include with student ID, user type(like Student, Staff or Admin), this session will be used in the dashboard, id can be used to get user information from database, and user type can help define which page the user can view.

3.If the user login the system again, like 127.0.0.1:8081/, the action GET “/” page will use session.getAttribute("Cookie"), if the cookie is not empty, it will redirect user to the index.


Logout:
Clean the session id in client by using session.invalidate();
Then redirect to login page.

FirstTimeLogin:
1.This method can be used to update the information for a first-time login system (meet the functional requirement 1),  first-time login student can login the system by clicking the link in the login page, then they need to provide their id and name, the updateinfopage only redirect when the user and id is correct.
2.When the infoupdate page is show up, user needs to fill all the label, the update button will update the info for this student only when they fill all the labels.
3.If the public checked in the student is true then they will not able to login the first time login system again.  
Dashboard:
By using the Thymeleaf for the frontend server, it can help generate more flexibility page for dashboard, Student had info (dateofbirth) but a staff never store this information into the system, but a staff have info (status) and student dose not, Thymeleaf will th:if="${user_type=='Student'}" will help decide what information will be generated for client.

When getting information from a object in Thymeleaf, we only need to parse the object into the html page:
Controller.Dashboard:
Student ss = DashService.getStudentById(cookieid);
model.addAttribute("user",ss);

Then the Thymeleaf can get the info by using:
Resources.Templates.Index.html:
${user.getName()}

By using thymeleaf, the system will be more flexible when insert a new page.

Student/Staff/Subject List Page:
In Controller.Dashboard:
Create a new array List<Student> studentList , getAllStudent() then store in studentList, finally passes to frontend html page, model.addAttribute("studentList",studentList).

Resources.Templates.StudentList.html:
th:each will automatically generate the table for all students.

Search:
	When user click the search button in the search page, it will post the id and name to Controller.Dashboard.Search, 
because the id of each object is unique, so using getStudentById(id) to return the object to the Controller.
then controller will return StudentList same as “List all Student page.”


Here is the api address After running the local server successfully, you can use the following api to realise some functions (including some admin functions).
A convenient way is to use https://www.postman.com/ which is to run following API

//RESTful API
//Use Query Params when testing the RESTful API in Postman.


//STUDENTS
//search by email
GET http://127.0.0.1:8081/api/students/search_email?email=123
//search by username
GET http://127.0.0.1:8081/api/students/search?username=newuser
//login
POST http://127.0.0.1:8081/api/users/login?id=123123&password=password123
//update info for a student when first time login the system.
PUT http://127.0.0.1:8081/api/students/update/188045?passportNumber=188045&dateOfBirth=2000-01-01&email=123@mail.com&password=123
PUT /api/students/update/id?passportNumber=xxx&dateOfBirth=xxxx-xx-xx&email=xxx@xxx&password=xxx


//STAFF
//update subject for a staff (multiple subject, String [])
PUT http://127.0.0.1:8081/api/staff/update/354586?Subject=123&Subject=321


//ADMIN
Since the admin interface is not finished at the moment, but according to the API provided , it can be done in the future.

//insert a student（require the registered name value）
POST http://127.0.0.1:8081/api/students/register?name=xixi
//insert a staff（require the registered name, passportnum, email, status and password）
POST http://127.0.0.1:8081/api/staff/register?name=AuYeung&passportNumber=A00001K&email=auyeung@mymail.edu.sh&status=Professor&password=abc123
//insert a new subject (require the subject name)
POST http://127.0.0.1:8081/api/subject/add?name=123
//update subject for a Student(Subjects name needs to be updated before and after） 
PUT http://127.0.0.1:8081/api/students/updateSub/354586?Subject=123&Subject=321
//insert a new admin（require registered name, email, role and password)
http://127.0.0.1:8081/api/admin/register?name=xixi&email=123@mail.com&role=admin&password=123



