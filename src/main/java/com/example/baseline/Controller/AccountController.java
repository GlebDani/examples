package com.example.baseline.Controller;

import com.example.baseline.Model.Account;
import com.example.baseline.servises.AccountService;
import com.example.baseline.servises.EmailService;
import com.example.baseline.util.constants.AppUtil;
import com.example.baseline.util.constants.email.EmailDetails;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Value("/images/uploads/**")
    private String photo_prefix;

    @Value("${password.token.reset.timeout.minutes}")
    private int password_token_timeout;

    @Value("${site.domain}")
    private String site_domain;

    @GetMapping("/register")
    public String register(Model model){
        Account account = new Account();
        model.addAttribute("account",account);
        return "account_view/register";
    }

    @PostMapping("/register")
    public String register_user(@Valid @ModelAttribute Account account, BindingResult result){
        if(result.hasErrors())
            return "account_view/register";
        accountService.save(account);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "account_view/login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model, Principal principal){
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        Optional<Account> accountOptional = accountService.findOneByEmail(authUser);
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            model.addAttribute("account", account);
            model.addAttribute("photo", account.getPhoto());
            System.out.println(account.getPhoto());
            return "account_view/profile";
        }
        else
            return "redirect:/?error";
    }

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String postProfile(@Valid @ModelAttribute Account account, BindingResult result, Principal principal){
        if(result.hasErrors()) return "account_view/profile";
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        Optional<Account> accountOptional = accountService.findOneByEmail(authUser);
        if(accountOptional.isPresent()){
            Account account_by_id = accountService.findOneById(account.getId()).get();
            account_by_id.setAge(account.getAge());
            account_by_id.setDateOfBirth(account.getDateOfBirth());
            account_by_id.setFirstName(account.getFirstName());
            account_by_id.setLastName(account.getLastName());
            account_by_id.setGender(account.getGender());
            account_by_id.setPassword(account.getPassword());

            accountService.save(account_by_id);
            SecurityContextHolder.clearContext();
            return "redirect:/logout";
        }
        else
            return "redirect:/?error";
    }

    @PostMapping("/update_photo")
    @PreAuthorize("isAuthenticated()")
    public String updatePhoto(@RequestParam("file") MultipartFile file,
                              RedirectAttributes attributes, Principal principal, Model model){
        if(file.isEmpty()){
            attributes.addFlashAttribute("error","No file uploaded");

            return "redirect:/profile";
        }else{
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try{
                int length = 10;
                boolean useLetters = true;
                boolean useNumbers = true;
                String generatedString = RandomStringUtils.random(length,useLetters,useNumbers);
                String finalPhotoName= generatedString + fileName;
                String fileLocation = AppUtil.get_upload_path(finalPhotoName);

                Path path = Paths.get(fileLocation);
                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                  attributes.addFlashAttribute("message","Uploaded successfully");


                String authUser = "email";
                if(principal != null){
                    authUser = principal.getName();
                }
                Optional<Account> accountOptional = accountService.findOneByEmail(authUser);
                if(accountOptional.isPresent()) {
                    Account account = accountOptional.get();
//                    Account account_by_id = accountService.findOneById(account.getId()).get();
                    String photoFilePath =  photo_prefix.replace("**", finalPhotoName);
//                    account_by_id.setPhoto(photoFilePath);
                    account.setPhoto(photoFilePath);
                    System.out.println(photoFilePath);
//                    accountService.save(account_by_id);
                    accountService.save(account);
                }
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                return "redirect:/profile";
            }
            catch (Exception e){
                e.getMessage();
                return "redirect:/?error";

            }
        }
//        return "redirect:/";
    }

    @GetMapping("/forgot-password")
    public String forgot_password(Model model){
        return "account_view/forgot_password";
    }

    @PostMapping("/reset-password")
    public String reset_password(@RequestParam("email") String _email, RedirectAttributes attributes, Model model){
        Optional<Account> optionalAccount = accountService.findOneByEmail(_email);
        System.out.println(optionalAccount.isPresent());
        if(optionalAccount.isPresent()){
            Account account = accountService.findOneById(optionalAccount.get().getId()).get();
            String reset_token = UUID.randomUUID().toString();
            account.setToken(reset_token);
            account.setPassword_reset_token_expity(LocalDateTime.now().plusMinutes(password_token_timeout));
            accountService.save(account);
            String reset_message = "this is reset password link:" + site_domain +"change-password?token="+reset_token;
            EmailDetails emailDetails = new EmailDetails(account.getEmail(), reset_message, "Reset password DEMO");
            if(!emailService.sendSimpleEmail(emailDetails)) {
                attributes.addFlashAttribute("error", "Error while sending email");
                return "redirect:/forgot-password";
            }
            attributes.addFlashAttribute("message","Password reset email send");
            return "redirect:/login";

        }
        else {
            attributes.addFlashAttribute("error","No user found");
            return "redirect:/forgot-password";
        }
    }


    @GetMapping("/change-password")
    public String changePassword(@RequestParam("token") String  token, RedirectAttributes attributes, Model model){
        if(!Objects.equals(token, "")){
            attributes.addFlashAttribute("error","Invalid token");
            return "redirect:/forgot-password";
        }

        Optional<Account> accountOptional = accountService.findByToken(token);
        if(accountOptional.isPresent()){
            Account account = accountService.findOneById(accountOptional.get().getId()).get();
            LocalDateTime now = LocalDateTime.now();
            if(now.isAfter(accountOptional.get().getPassword_reset_token_expity())){
                attributes.addFlashAttribute("error","Token expired");
                return "redirect:/forgot-password";
            }
            model.addAttribute("account", account);
            return "account_view/change_password";
        }
        else {
            attributes.addFlashAttribute("error","Invalid token");
            return "redirect:/forgot-password";
        }
    }

    @PostMapping("/change-password")
    public String handleChangePassword(@ModelAttribute Account account, @RequestParam("password") String  password, @RequestParam("password_confirm") String  conPassword, RedirectAttributes attributes, Model model) {
        System.out.println(account.getId());
        Account account_by_id = accountService.findOneById(account.getId()).get();

        account_by_id.setPassword(account.getPassword());
        account_by_id.setToken("");

        accountService.save(account_by_id);

        attributes.addFlashAttribute("message","password updated");
        return "redirect:/login";
//        if(password.equals(conPassword)){
//            attributes.addFlashAttribute("message","password updated");
//            return "redirect:/login";
//        }
//        else{
//            System.out.println(account_by_id.getToken());
//            attributes.addFlashAttribute("error","dismaching password");
//            return "redirect:/change-password?token="+account_by_id.getToken();
//        }
    }
    @GetMapping("account_view/test")
    public String test(Model model){
        return "account_view/test";
    }



}
