package com.example.baseline.Controller;

import com.example.baseline.Model.Account;
import com.example.baseline.Model.Post;
import com.example.baseline.servises.AccountService;
import com.example.baseline.servises.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal){
        Optional<Post> optionalPost = postService.getById(id);
        String authUser = "email";

        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);

            if(principal != null){
                authUser = principal.getName();
            }
            if (authUser.equals(post.getAccount().getEmail()))
                model.addAttribute("isOwner",true );
            else model.addAttribute("isOwner",false );

            return "post_view/post";
        }
        else
            return "post_view/404";

    }

    @GetMapping("/post/add")
    @PreAuthorize("isAuthenticated()")
    public String addPost(Model model,Principal principal){
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post",post);
            return "post_view/post_add";
        }
        else
            return "redirect:/";
    }

    @PostMapping("/post/add")
    @PreAuthorize("isAuthenticated()")
    public String addPostHandler(@Valid @ModelAttribute Post post, BindingResult result, Principal principal){
        if(result.hasErrors())
            return "post_view/post_add";
        String authUser = "email";
        if(principal != null){
            authUser = principal.getName();
        }
        if(post.getAccount().getEmail().compareToIgnoreCase(authUser) < 0){
            return "redirect:/?error";
        }
        postService.save(post);

        return "redirect:/post/"+post.getId();
    }

    @GetMapping("/post/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostEdited(@PathVariable Long id, Model model){
        Optional<Post> optionalPost= postService.getById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_view/post_edit";

        }
        else
            return "post_view/404";

    }

    @PostMapping("/post/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@Valid @ModelAttribute Post post, BindingResult result, @PathVariable Long id){
        if(result.hasErrors())
            return "post_view/post_edit";
        Optional<Post> optionalPost= postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }
        return "redirect:/post/"+post.getId();
    }

    @GetMapping("/post/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id){
        Optional<Post> optionalPost= postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            postService.deleteById(existingPost);
        }
        return "redirect:/";
    }

}
