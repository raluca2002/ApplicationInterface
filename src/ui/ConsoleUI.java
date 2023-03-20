package ui;

import domain.Friendship;
import domain.User;
import exceptions.NoIdException;
import exceptions.ValidationException;
import service.ServiceFriendship;
import service.ServiceUser;
import repository.database.UserDBRepository;
import repository.database.FriendshipDBRepository;
import utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    static ServiceUser srvUser;
    ServiceFriendship srvFriendship;

    // the scanner class is used to get user input
    Scanner scanner = new Scanner(System.in);

    public ConsoleUI(ServiceUser srvUser, ServiceFriendship srvFriendship) {
        this.srvUser = srvUser;
        this.srvFriendship = srvFriendship;}

    public void menu() throws Exception {
        //addData();
        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            String ans = scanner.nextLine();
            if (ans.equals("1")) {
                addUser();
            } else if (ans.equals("2")) {
                deleteUser();
            } else if (ans.equals("3")) {
                showUsers();
            } else if (ans.equals("4")) {
                addFriend();
            } else if (ans.equals("5")) {
                deleteFriend();
            } else if (ans.equals("6")) {
                showFriendships();
            } else if (ans.equals("9")) {
                return;
            }
        }
    }

    public void printMenu(){
        System.out.println("Menu:");
        System.out.println("1.Add user");
        System.out.println("2.Remove user");
        System.out.println("3.Show users");
        System.out.println("4.Add friend");
        System.out.println("5.Remove friend");
        System.out.println("6.Show friendships");
        System.out.println("9.Exit");
        System.out.println("Your choice:");
    }

    public void addUser(){
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        try {
            srvUser.addUser(firstName, lastName, username, password);
        }catch(ValidationException e){
            System.out.println(e.getMessage());
        }
    }


    public void deleteUser() throws Exception {
        System.out.println("Give the id for the user you want to remove:");
        Long id = scanner.nextLong();
        try {
            srvUser.deleteUser(id);
        }catch(NoIdException e){
            System.out.println(e.getMessage());
        }
    }

    public void showUsers(){
        System.out.println("All users:");
        Iterable<User> users = srvUser.getAllUsers();
        String str = "";
        for (User user : users) {
            str += "Id:" + user.getId() + "; First Name:" + user.getFirstName() + "; Last Name:" + user.getLastName() +
                    "; Username:" + user.getUsername() + "; Password:" +user.getPassword()+"\n";
        }
        System.out.println(str);
    }

    public void addFriend(){
        System.out.println("The id of the user who want to add a friend:");
        Long id1 = scanner.nextLong();
        System.out.println("The id of the friend to add:");
        Long id2 = scanner.nextLong();
        //srvFriendship.addFriendship(id1,id2);
        try {
            srvFriendship.addFriendship(id1,id2,Constants.CURRENT_TIME );
        }catch(ValidationException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteFriend(){
        System.out.println("The id of the friendship you want to delete:");
        Long id = scanner.nextLong();
        try {
            srvFriendship.deleteFriendship(id);
        }
        catch(NoIdException e){
            System.out.println(e.getMessage());
        }
    }

    public void showFriendships(){
        System.out.println("All friendship relations:");
        Iterable<Friendship> friendships = srvFriendship.getAllFriendships();
        String str = "";
        for (Friendship friendship : friendships) {
            str += "IdF:" + friendship.getId() + "; IdU1:" + friendship.getId1() + "; IdU2:" + friendship.getId2()
                    +"; Date:" + Constants.CURRENT_TIME+ "\n";
        }
        System.out.println(str);
    }

    public void addData() throws Exception {
        srvUser.addUser("Diana", "Marcu","dianamarcu","12334a");
        srvUser.addUser("Andrei", "Pop","andreipop","www12");
        srvUser.addUser("Daniel", "Suciu","danielsuciu","absc");
        srvUser.addUser("Marta", "Lazar","martalazar","qqw2");
        /*
        srvUser.addUser("Andreea", "Dan");
        srvUser.addUser("Cezar", "Danciu");
        srvUser.addUser("Maria", "Cazacu");
        srvUser.addUser("Adrian", "Groza");
        srvUser.addUser("Antonia", "Stefan");
        srvUser.addUser("Darius", "Man");
        srvUser.addUser("Andrada", "Oltean");
        srvUser.addUser("Rares", "Moldovan");

        srvFriendship.addFriendship((long)1,(long)2);
        srvFriendship.addFriendship((long)2,(long)3);
        srvFriendship.addFriendship((long)2,(long)4);
        srvFriendship.addFriendship((long)2,(long)5);
        srvFriendship.addFriendship((long)2,(long)10);
        srvFriendship.addFriendship((long)6,(long)7);
        srvFriendship.addFriendship((long)7,(long)8);
        srvFriendship.addFriendship((long)8,(long)9);*/
        //srvFriendship.addFriendship((long)10,(long)11);
    }
}
