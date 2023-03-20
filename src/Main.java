import repository.Repository;
import domain.User;
import repository.database.UserDBRepository;
import repository.database.FriendshipDBRepository;
import repository.memory.InMemoryRepository;
import domain.Friendship;
import domain.validators.FriendshipValidator;
import domain.validators.UserValidator;
import service.ServiceFriendship;
import service.ServiceUser;
import domain.validators.Validator;
import ui.ConsoleUI;
//import tests.Tests;

public class Main {
    public static void main(String[] args) throws Exception{
        /*
        InMemoryRepository<Long, User> repoUser = new InMemoryRepository<>( new UserValidator());
        InMemoryRepository<Long, Friendship> repoFriendship = new InMemoryRepository<>( new FriendshipValidator());
        */
        String urlDB = "jdbc:postgresql://localhost:5432/academic";
        UserDBRepository repoUser = new UserDBRepository(urlDB, "postgres", "postgres", new UserValidator());
        FriendshipDBRepository repoFriendship = new FriendshipDBRepository(urlDB, "postgres", "postgres",new FriendshipValidator());
       // Repository<Long,User> repository = new UserDBRepository("jdbc:postgresql://localhost:5432/academic", "postgres","postgres", new UserValidator());
       // repoUser.findAll().forEach(System.out::println);
       // repoFriendship.findAll().forEach(System.out::println);
        //Tests.run();
        ConsoleUI ui = new ConsoleUI(new ServiceUser(repoUser, repoFriendship), new ServiceFriendship(repoUser, repoFriendship));

        ui.menu();
    }}