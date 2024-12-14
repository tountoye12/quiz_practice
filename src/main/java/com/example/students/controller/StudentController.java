package com.example.students.controller;


import com.example.students.model.Student;
import com.example.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String getAllStudents(Model model) {
        var students = studentRepository.findAll();
        model.addAttribute("students",students);
        return "home";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }


    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, Model model){
        studentRepository.save(student);
        System.out.println(student);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        //System.out.println(student);
        studentRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Student has been deleted");
        return "redirect:/students";
    }
}
