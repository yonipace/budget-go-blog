package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Post;
import app.core.entities.User;
import app.core.exceptions.BlogException;
import app.core.repositories.PostRepository;
import app.core.repositories.UserRepository;

@Service
@Transactional
public class BlogService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;

	public Post addPost(Post post, long userId) throws BlogException {

		// post id must be unique
		if (postRepository.existsById(post.getId())) {
			throw new BlogException("Failed to add post - post id: " + post.getId() + " already exists");
		}
		// set post user to userId
		post.setUser(getUser(userId));
		// save post to DB
		return postRepository.save(post);
	}

	public Post updatePost(Post post, long userId) throws BlogException {

		Post postFromDB = postRepository.findById(post.getId())

				.orElseThrow(() -> new BlogException(
						"Failed to update post - post id: " + post.getId() + " does not exist"));

		if (postFromDB.getUser().getId() == userId) {
			return postRepository.save(post);
		}

		throw new BlogException("Failed to update post - post " + post.getId() + " does not belong to user " + userId);
	}

	public void deletePost(long id) throws BlogException {

		if (!postRepository.existsById(id)) {
			throw new BlogException("Failed to delete post - post id: " + id + " does not exist");
		}
		postRepository.deleteById(id);

	}

	public Post getOne(long id) throws BlogException {

		return postRepository.findById(id)
				.orElseThrow(() -> new BlogException("Failed to get post - post id: " + id + " does not exist"));
	}

	public List<Post> getAll() throws BlogException {

		return postRepository.findAll();
	}

	User getUser(long id) throws BlogException {

		return userRepository.findById(id).orElseThrow(() -> new BlogException("failed to get user with id: " + id));
	}

}
