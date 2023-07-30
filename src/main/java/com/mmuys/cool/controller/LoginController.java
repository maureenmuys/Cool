package com.mmuys.cool.controller;

import com.mmuys.cool.model.User;
import com.mmuys.cool.repository.RoleRepository;
import com.mmuys.cool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;



    // Handler for GET requests to display the login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", false); // Initialize error to false (no error shown initially)
        return "login"; // Return the login page
    }

    // Handler for POST requests to process the login form submission
    @PostMapping("/login")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {

        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Successful login, you can redirect to a home page or any other secured page
            return "redirect:/home"; // Replace "/home" with your desired page URL
        } else {
            // Invalid login, show an error message on the login page
            model.addAttribute("error", true);
            return "login"; // Return the login page so that the user can try again
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; //
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/";
    }

}
