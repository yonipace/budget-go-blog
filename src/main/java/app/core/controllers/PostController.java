package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Post;
import app.core.exceptions.BlogException;
import app.core.services.BlogService;

@RestController
@RequestMapping("/blog")
public class PostController {

	@Autowired
	BlogService blogService;

	public Post addPost(Post post, long userId) {

		try {
			return blogService.addPost(post, userId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	public Post updatePost(Post post, long userId) throws BlogException {

		try {
			return blogService.updatePost(post, userId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deletePost(long id) throws BlogException {

		try {
			blogService.deletePost(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	public Post getOne(long id) throws BlogException {

		try {
			return blogService.getOne(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public List<Post> getAll() throws BlogException {
		try {
			return blogService.getAll();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
