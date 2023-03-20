package service;

import domain.Friendship;
import domain.User;
import exceptions.NoIdException;
import repository.Repository;
import repository.database.FriendshipDBRepository;

import java.time.LocalDateTime;
import java.util.Collection;

public class ServiceFriendship {
    private Repository<Long, User> userRepository;
    private Repository<Long, Friendship> friendshipRepository;

    public ServiceFriendship(Repository<Long, User> userRepository, Repository<Long, Friendship> friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    /**
     * Add a new friendship relation to the repository between 2 users with given ids
     * @param id1 id of a user who will get a new friendship relation
     * @param id2 id of a user who will get a new friendship relation
     * @throws NoIdException if there is no user with one or both given ids
     * @throws Exception if id1=id2
     */
    public void addFriendship(Long id1, Long id2, LocalDateTime friendsFrom) throws Exception {

        if(userRepository.findOne(id1)==null || userRepository.findOne(id2)==null){
            throw new NoIdException("At least one id doesn't exist!\n");
        }
        if(id1.equals(id2)){
            throw new Exception("A user can't be friend with himself!");
        }


        //id-urile userilor care au o relatie de prietenie vor fi sortate
        if(id1 > id2){
            Long aux = id1;
            id1 = id2;
            id2 = aux;
        }
        Friendship friendship = new Friendship(id1, id2, friendsFrom);
        Long maxId = 0L;
        for(Friendship f : friendshipRepository.findAll()){
            if(f.getId() > maxId){
                maxId = f.getId();
            }
        }
        friendship.setId(maxId+1);
        friendshipRepository.save(friendship);
        updateFriendsListAdd(id1, id2, friendsFrom);
    }

    /**
     * Update the user's friend list when a new friendship relation is added
     * @param id1 id of a user who got a new friendship relation
     * @param id2 id of a user who got a new friendship relation
     */
    private void updateFriendsListAdd(Long id1, Long id2, LocalDateTime friendsFrom){
        User u1 = userRepository.findOne(id1);
        User u2 = userRepository.findOne(id2);
        u1.addFriend(u2);
        u2.addFriend(u1);
    }

    /**
     * Update the user's friend list when a friendship relation is deleted
     * @param id1 id of a user who lost a friendship relation
     * @param id2 id of a user who lost a friendship relation
     */
    private void updateFriendsListDelete(Long id1, Long id2) {
        User u1 = userRepository.findOne(id1);
        User u2 = userRepository.findOne(id2);
        u1.deleteFriend(u2);
        u2.deleteFriend(u1);
    }

    /**
     * Delete a friendship relation with a given id from repository
     * @param id the friendship id to delete
     * @throws NoIdException if the given id doesn't exist
     */
    public void deleteFriendship(Long id) throws NoIdException{
        Friendship friendship = friendshipRepository.delete(id);
        if(friendship==null){
            throw new NoIdException("There is no friendship with this id!");
        }
        updateFriendsListDelete(friendship.getId1(), friendship.getId2());
    }

    public Iterable<Friendship> getAllFriendships(){
        return friendshipRepository.findAll();
    }

    public Friendship findOne(Long id){
        return friendshipRepository.findOne(id);
    }


}
