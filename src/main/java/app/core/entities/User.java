package app.core.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String email, String password, String firstName, String lastName, Set<Post> posts) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.posts = posts;
	}

	public void addPost(Post post) {

		if (posts == null) {
			posts = new HashSet<Post>();
		}

		posts.add(post);
	}

}
