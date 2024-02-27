package com.example.baseline.config;

import com.example.baseline.Model.Account;
import com.example.baseline.Model.Authority;
import com.example.baseline.Model.Post;
import com.example.baseline.servises.AccountService;
import com.example.baseline.servises.AuthorityService;
import com.example.baseline.servises.PostService;
import com.example.baseline.util.constants.Privillages;
import com.example.baseline.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeeData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;


    @Override
    public void run(String... args) throws Exception {

        for(Privillages auth: Privillages.values()){
            Authority authority = new Authority();
            authority.setId((auth.getId()));
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        Account account4 = new Account();
        account2.setEmail("admin@mail.com");
        account2.setFirstName("Ad");
        account2.setLastName("Min");
        account2.setPassword("password");
        account2.setRole(Roles.ADMIN.getRole());
        account2.setAge(25);
        account2.setDateOfBirth(LocalDate.parse("1990-01-01"));
        account2.setGender("Male");

        account1.setEmail("klenandg@gmail.com");
        account1.setFirstName("Us");
        account1.setLastName("er");
        account1.setPassword("password");
        account1.setAge(25);
        account1.setDateOfBirth(LocalDate.parse("1990-01-11"));
        account1.setGender("Male");

        account3.setEmail("editor@mail.com");
        account3.setFirstName("edi");
        account3.setLastName("tor");
        account3.setPassword("password");
        account3.setRole(Roles.EDITOR.getRole());
        account3.setAge(55);
        account3.setDateOfBirth(LocalDate.parse("1970-01-01"));
        account3.setGender("Female");

        account4.setEmail("super_editor@mail.com");
        account4.setFirstName("super");
        account4.setLastName("editor");
        account4.setPassword("password");
        account4.setRole(Roles.EDITOR.getRole());
        account4.setAge(20);
        account4.setDateOfBirth(LocalDate.parse("2000-11-10"));
        account4.setGender("Male");

        Set<Authority> authoritySet = new HashSet<>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authoritySet::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authoritySet::add);
        account4.setAuthoritySet(authoritySet);

        accountService.save(account1);
        accountService.save(account2);
        accountService.save(account3);
        accountService.save(account4);
        List<Post> posts = postService.getAll();
        if (posts.size() == 0){
            Post post01 = new Post();
            post01.setTitle("Post 01");
            post01.setBody("Post 01 body...");
            post01.setAccount(account1);

            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post 02");
            post02.setBody("Post 02 body...");
            post02.setAccount(account2);

            postService.save(post02);
        }
    }
}
