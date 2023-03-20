package service;

import domain.Friendship;
import domain.User;
import exceptions.NoIdException;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceUser {
    private Repository<Long, User> userRepository;
    private Repository<Long, Friendship> friendshipRepository;

    public ServiceUser(Repository<Long, User> userRepository, Repository<Long, Friendship> friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    /**
     * Add a new user in repository, setting his id by increment the last id found
     *
     * @param firstName string with the first name of the user to add
     * @param lastName  string with the last name of the user to add
     */
    public void addUser(String firstName, String lastName, String username, String password) {
        User u = new User(firstName, lastName, username, password);
        Long maxId = 0L;
        for (User user : userRepository.findAll()) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        u.setId(maxId + 1);
        userRepository.save(u);
    }

    /**
     * Delete the user with the given id from the repository
     *
     * @param id the given id of the user to delete
     * @throws NoIdException if there is no user with the given id
     */
    public void deleteUser(Long id) throws Exception {
        User deleted = userRepository.findOne(id);
        Collection<Long> friendshipsToDelete = new ArrayList<>();
        if (deleted != null) {
            userRepository.delete(id);
            friendshipRepository.findAll().forEach(friendship -> {
                if (friendship.getId1().equals(deleted.getId())) {
                    friendshipsToDelete.add(friendship.getId());
                    User user = userRepository.findOne(friendship.getId2());
                    user.deleteFriend(deleted);
                } else if (friendship.getId2().equals(deleted.getId())) {
                    friendshipsToDelete.add(friendship.getId());
                    User user = userRepository.findOne(friendship.getId1());
                    user.deleteFriend(deleted);
                }
            });
            friendshipsToDelete.forEach(friendship -> {
                friendshipRepository.delete(friendship);
            });
            return;
        }
        throw new NoIdException("There is no user with this id!\n");
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
