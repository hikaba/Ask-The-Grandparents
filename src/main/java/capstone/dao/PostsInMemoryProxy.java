package capstone.dao;

import java.util.Map;
import java.util.UUID;

import capstone.model.Post;

public class PostsInMemoryProxy {

	PostsInMemory proxy = new PostsInMemory();
	
    public Map<UUID, Post> readPosts() {
        return proxy.readPosts();
    }

    public Post readPost(String id) {
        return proxy.readPost(id);
    }

    public void createPost(Post post) {
    	proxy.createPost(post);;
    }

    public void updatePost(Post post) {
    	proxy.updatePost(post);
    }

    public void deletePost(String id) {
    	proxy.deletePost(id);
    }

    public void createOrUpdatePost(Post post) {
    	proxy.createOrUpdatePost(post);
    }
}
