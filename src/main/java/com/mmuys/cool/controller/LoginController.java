package com.mmuys.cool.controller;

import com.mmuys.cool.global.GlobalData;
import com.mmuys.cool.model.Role;
import com.mmuys.cool.model.User;
import com.mmuys.cool.repository.RoleRepository;
import com.mmuys.cool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;



    @GetMapping("/login")
    public String showLoginForm(Model model) {
        GlobalData.cart.clear();;
        model.addAttribute("error", false);
        return "login";
    }

    // Handler for POST requests to process the login form submission
    @PostMapping("/login")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {

        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent() ) {
            if(BCrypt.checkpw(password, (user.get().getPassword()))){
                return "redirect:/shop";
            }
            else{
                return "login";
            }
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; //
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/shop";
    }

}
