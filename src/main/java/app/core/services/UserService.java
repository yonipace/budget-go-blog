package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.User;
import app.core.exceptions.BlogException;
import app.core.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) throws BlogException {

		// user id must be unique
		if (userRepository.existsById(user.getId())) {
			throw new BlogException("Failed to add user - user id: " + user.getId() + " already exists");
		}
		// save user to DB
		return userRepository.save(user);
	}

	public User updateUser(User user) throws BlogException {

		if (!userRepository.existsById(user.getId())) {
			throw new BlogException("Failed to update user - user id: " + user.getId() + " does not exist");
		}
		return userRepository.save(user);

	}

	public void deleteUser(long id) throws BlogException {

		if (!userRepository.existsById(id)) {
			throw new BlogException("Failed to delete user - user id: " + id + " does not exist");
		}
		userRepository.deleteById(id);

	}

	public User getOne(long id) throws BlogException {

		return userRepository.findById(id)
				.orElseThrow(() -> new BlogException("Failed to get user - user id: " + id + " does not exist"));
	}

	public List<User> getAll() throws BlogException {

		return userRepository.findAll();
	}

}
