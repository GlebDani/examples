package com.example.baseline.servises;

import com.example.baseline.Model.Post;
import com.example.baseline.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }


    public Page<Post> getAll(int offset, int pageSize, String field){
        return postRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,field));
    }
    public void deleteById(Post post){
        postRepository.delete(post);
    }
    public Post save(Post post){
        if(post.getId()==null){
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }


}
