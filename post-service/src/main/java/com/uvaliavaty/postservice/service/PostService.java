package com.uvaliavaty.postservice.service;

import com.uvaliavaty.postservice.client.UserServiceClient;
import com.uvaliavaty.postservice.exception.PostNotFoundException;
import com.uvaliavaty.postservice.model.Post;
import com.uvaliavaty.postservice.repository.PostRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserServiceClient userServiceClient;

    public PostService(PostRepository postRepository, UserServiceClient userServiceClient) {
        this.postRepository = postRepository;
        this.userServiceClient = userServiceClient;
    }

    public Post createPost(Post post) {
        post.setPostedAt(new Date());
        Post createdPost = postRepository.save(post);

        updateUserService(createdPost);

        return createdPost;
    }

    public Post findPostById(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
                postRepository.delete(postOptional.get());
                updateUserService(postOptional.get());
            } else {
            throw new PostNotFoundException(id, "Post with provided id is not found.");
        }
    }

    public Post updatePost(long id, Post post) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post existingPost = postOptional.get();
            existingPost.setText(post.getText());
            existingPost.setTopic(post.getTopic());
            existingPost.setPostedAt(new Date());

            return postRepository.save(post);
        } else {
            throw new PostNotFoundException(id, "Post with provided id is not found.");
        }
    }

    private void updateUserService(Post createdPost) {
        List<Post> usersPosts = postRepository.findByAuthorId(createdPost.getAuthorId());
        userServiceClient.updateUser(createdPost.getAuthorId(), usersPosts.size());
    }
}
