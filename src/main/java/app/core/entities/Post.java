package app.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String body;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Post(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public Post(String title, String body, User user) {
		this.title = title;
		this.body = body;
		this.user = user;
	}

}
