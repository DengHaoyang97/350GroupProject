package com.myproject.onlinelearningsystem.controller;
import com.myproject.onlinelearningsystem.model.*;
import com.myproject.onlinelearningsystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
    public class Dashboard {

        @Autowired
        private DashboardService DashService;

        @GetMapping("/index")
        public String index(HttpSession session, Model model) {
            //get user info
            String cookieid = (String) session.getAttribute("Cookie");
            String user_type = (String) session.getAttribute("type");

            if(cookieid  == null){
                return "redirect:/login";
            }

            model.addAttribute("user_type",user_type);
            //define a user type

            List<Subject> checkedSubList = new ArrayList<>();
            switch (user_type) {
                case ("Student"):
                    Student ss = DashService.getStudentById(cookieid);
                    model.addAttribute("user",ss);

                    String [] subjectss = ss.getSubject();
                    if (subjectss != null && subjectss.length > 0) {
                        for (String subject : subjectss) {
                            checkedSubList.add(DashService.getSubjectById(subject));
                        }
                    }
                    model.addAttribute("checkedSubList",checkedSubList);
                    break;

                case ("Staff"):
                    Staff st = DashService.getStaffById(cookieid);
                    model.addAttribute("user",st);

                    String [] subjectst = st.getSubject();
                    if (subjectst != null && subjectst.length > 0) {
                        for (String subject : subjectst) {
                            checkedSubList.add(DashService.getSubjectById(subject));
                        }
                    }
                    model.addAttribute("checkedSubList",checkedSubList);
                    break;
                case ("Admin"):
                    Admin ad = DashService.getAdminById(cookieid);
                    model.addAttribute("user",ad);
                    break;
            }
            //list all info like subject, staff, student.
            List<Student> studentList = DashService.getAllStudent();
            List<Staff> staffList = DashService.getAllStaff();
            List<Admin> adminList = DashService.getAllAdmin();
            List<Subject> subjects = DashService.getAllSubject();
            //parse to frontend
            model.addAttribute("studentList",studentList);
            model.addAttribute("staffList",staffList);
            model.addAttribute("adminList",adminList);
            model.addAttribute("subjects",subjects);

            return "index";
        }


    @GetMapping("/StaffList")
    public String StaffList(HttpSession session, Model model) {
        //get user info
        String cookieid = (String) session.getAttribute("Cookie");
        String user_type = (String) session.getAttribute("type");

        if(cookieid  == null){
            return "redirect:/login";
        }

        List<Staff> staffList = DashService.getAllStaff();
        model.addAttribute("staffList",staffList);
        return "StaffList";
    }

    @GetMapping("/StudentList")
    public String StudentList(HttpSession session, Model model) {
        //get user info
        String cookieid = (String) session.getAttribute("Cookie");
        String user_type = (String) session.getAttribute("type");

        if(cookieid  == null){
            return "redirect:/login";
        }

        List<Student> studentList = DashService.getAllStudent();
        model.addAttribute("studentList",studentList);
        return "StudentList";
    }
    @GetMapping("/SubjectList")
    public String SubjectList(HttpSession session, Model model) {

        //get user info
        String cookieid = (String) session.getAttribute("Cookie");
        String user_type = (String) session.getAttribute("type");

        if(cookieid  == null){
            return "redirect:/login";
        }

        List<Subject> subjectList = DashService.getAllSubject();
        model.addAttribute("subjectList",subjectList);
        return "SubjectList";
    }
    @GetMapping("/Admin")
    public String AdminPage(HttpSession session, Model model) {
        List<Student> studentList = DashService.getAllStudent();
        List<Staff> staffList = DashService.getAllStaff();
        List<Admin> adminList = DashService.getAllAdmin();
        List<Subject> subjects = DashService.getAllSubject();

        model.addAttribute("staffList",staffList);
        String cookieid = (String) session.getAttribute("Cookie");
        String user_type = (String) session.getAttribute("type");
        model.addAttribute("user_type",user_type);

    if(user_type != "Admin"){
    return "404";
    }
    else{
        return "AdminPage";
        }
    }

    @GetMapping("/Search")
    public String Search(HttpSession session) {
        String cookieid = (String) session.getAttribute("Cookie");
        if(cookieid  == null){
            return "redirect:/login";
        }
        return "Search";
    }

    @PostMapping("/Search")
    public String processLogin(String id, String search_name, String search_type, Model model) {
        System.out.println("Searching " + search_type);
        System.out.println("Searching ID " + id);
        System.out.println("Searching Name " + search_name);
        switch (search_type) {
            case ("Student"):
                if(!id.isEmpty()){
                    //every id is unique.
                    Student studentList = DashService.getStudentById(id);
                    model.addAttribute("studentList",studentList);
                    return "StudentList";
                }
                if(!search_name.isEmpty()){
                    List<Student> studentList = DashService.findStudentByName(search_name);
                    System.out.println("debug "+ studentList);
                    model.addAttribute("studentList",studentList);
                    return "StudentList";
                }
                break;
            case ("Staff"):
                if(!id.isEmpty()) {
                    Staff staffList = DashService.getStaffById(id);
                    model.addAttribute("staffList", staffList);
                    return "StaffList";
                }
                if(!search_name.isEmpty()){
                    List<Staff> staffList = DashService.findStaffByName(search_name);
                    model.addAttribute("staffList",staffList);
                    return "StaffList";
                }
                break;
            case ("Subject"):
                if(!id.isEmpty()) {
                    Subject subjectList = DashService.getSubjectById(id);
                    model.addAttribute("subjectList", subjectList);
                    return "SubjectList";
                }
                if(!search_name.isEmpty()){
                    List<Subject> subjectList = DashService.findSubjectByName(search_name);
                    model.addAttribute("subjectList",subjectList);
                    return "SubjectList";
                }
                break;
        }
        //return an empty page while cannot find any result;
        return "StudentList";
    }

    @GetMapping("/FirstLogin")
    public String FirstLogin(HttpSession session, Model model) {
            model.addAttribute("message","Plz input your info: ");
            return "FirstLogin";
    }

    @PostMapping("/FirstLogin")
    public String FirstLogin(HttpSession session, String id, String name, Model model) {
        if(!id.isEmpty()&&!name.isEmpty()){
            Student ss = DashService.getStudentByIdAndName(id, name);
            if (ss != null&&!ss.checked){
                model.addAttribute("studentinfo",ss);
                model.addAttribute("message","");
                return "StudentUpdateInfo";
            }
        }
        model.addAttribute("message","Wrong input, plz input correctly: ");
        return "FirstLogin";
    }

    @PostMapping("/StudentUpdateInfo")
    public String StudentUpdateInfo(String id, String passportNumber,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                    String email, String password, Model model) {

            Student checkemail = DashService.findStudentByEmail(email);
        boolean containsSpace = password.contains(" ");
        if (containsSpace){
            model.addAttribute("message","Update Fail: Space cannot be used in password");
            return "FirstLogin";
        }

        if (checkemail != null){
            model.addAttribute("message","Update Fail: same email address with someone in db");
            return "FirstLogin";
        }

        Student checkpassnumber = DashService.findStudentByPassportNumber(passportNumber);
        if (checkpassnumber != null){
            model.addAttribute("message","Update Fail: same passportNumber with someone in db");
            return "FirstLogin";
        }

        Student ss = DashService.updateStudentInfo(id, passportNumber, dateOfBirth, email, password);
        model.addAttribute("message","Update Successfully, plz go back and login");
        return "FirstLogin";
    }
    @GetMapping("/StudentUpdateInfo")
    public String RedirectToFirst(HttpSession session, Model model) {
        model.addAttribute("message","Plz input your info: ");
        return "FirstLogin";
    }

    @GetMapping("/InsertStudent")
    public String InsertStudent(HttpSession session, Model model) {
        return "InsertStudent";
    }

    @PostMapping("/register")
    public String RegisterStudent(String name, Model model) {
        Student student = DashService.createStudent(name);
        model.addAttribute("studentList",student);
        return "StudentList";
    }

}

