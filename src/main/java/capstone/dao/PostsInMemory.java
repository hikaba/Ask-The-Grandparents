package capstone.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import capstone.model.Post;

public class PostsInMemory {

    private Map<UUID, Post> posts;

    public PostsInMemory() {
        this.posts = new LinkedHashMap<UUID, Post>();
    }

    public Map<UUID, Post> readPosts() {
        return posts;
    }

    public Post readPost(String id) {
        return posts.get(UUID.fromString(id));
    }

    public void createPost(Post post) {
        updatePost(post);
    }

    public void updatePost(Post post) {
        posts.put(post.getId(), post);
    }

    public void deletePost(String id) {
        posts.remove(UUID.fromString(id));
    }

    public void createOrUpdatePost(Post post) {
        Post localpost = readPost(post.getId().toString());
        if (localpost == null) {
            createPost(post);
        } else {
            updatePost(post);
        }
    }

}
