package app.core.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import app.core.entities.Post;
import app.core.entities.User;
import app.core.exceptions.BlogException;
import app.core.repositories.PostRepository;
import app.core.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BlogServiceUnitTest {

	@Mock
	UserRepository userRepository;
	@Mock
	PostRepository postRepository;
	@InjectMocks
	BlogService blogServiceUnderTest;

	@Test
	public void testAddPost() throws BlogException {

		User user = new User(1, "user@email.com", "password", "yoni", "pace", null);
		Post post = new Post("Post Title", "some text", user);

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(postRepository.save(post)).thenReturn(post);

		Post newPost = blogServiceUnderTest.addPost(post, 1);

		// make sure user is set
		assertThat(newPost.getUser()).isEqualTo(user);

		// make sure the method returns the original post
		assertThat(newPost.equals(post));
	}

	@Test
	public void testUpdatePost() throws BlogException {

		User user = new User(1, "user@email.com", "password", "yoni", "pace", null);
		Post post = new Post(1, "Post Title", "some text", user);
		Post postToUpdate = new Post(1, "updated post", "some text", user);

//		when(postRepository.existsById(1L)).thenReturn(true);
		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		when(postRepository.save(postToUpdate)).thenReturn(postToUpdate);

		Post updatedPost = blogServiceUnderTest.updatePost(postToUpdate, 1);

		assertThat(updatedPost.getId()).isEqualTo(post.getId());

		assertThat(post.getTitle()).isNotEqualTo(updatedPost.getTitle());

	}

	@Test
	public void testDeletePost() throws BlogException {

		when(postRepository.existsById(1L)).thenReturn(true);

		blogServiceUnderTest.deletePost(1L);

	}

	@Test
	public void testGetOne() throws BlogException {

		User user = new User(1, "user@email.com", "password", "yoni", "pace", null);
		Post post = new Post(1, "Post Title", "some text", user);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));

		Post postFromService = blogServiceUnderTest.getOne(1L);

		assertThat(postFromService).isEqualTo(post);

	}

	@Test
	public void testGetAll() throws BlogException {

		User user = new User(1, "user@email.com", "password", "yoni", "pace", null);
		Post post = new Post(1, "Post Title", "some text", user);
		Post post2 = new Post(2, "Post Title", "some text", user);
		Post post3 = new Post(3, "Post Title", "some text", user);
		Post post4 = new Post(4, "Post Title", "some text", user);

		when(postRepository.findAll()).thenReturn(List.of(post, post2, post3, post4));

		List<Post> list = blogServiceUnderTest.getAll();

		assertThat(list).contains(post, post2, post3, post4);

	}

}
