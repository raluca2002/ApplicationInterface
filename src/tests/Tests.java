package tests;

import domain.Friendship;
import domain.User;
import domain.validators.FriendshipValidator;
import domain.validators.UserValidator;
import exceptions.ValidationException;

/*
public class Tests {
    public static void run() throws Exception {
        validationTest();
    }

    private static void validationTest() {
        UserValidator userValidator = new UserValidator();
        User u1 = new User("", "");
        u1.setId(13245L);
        try {
            userValidator.validate(u1);
            assert true;
        } catch (ValidationException e) {
            System.out.println( e.getMessage().equals("Every word from first name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\nEvery word from last name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n"));
        }

        User u2 = new User("", "Dan");
        u2.setId(45467L);
        try {
            userValidator.validate(u2);
            assert false;
        } catch (ValidationException e) {
            System.out.println(e.getMessage().equals("Every word from first name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n"));
        }

        User u3 = new User("Andrei", "");
        u3.setId(3456L);
        try {
            userValidator.validate(u3);
            assert false;
        } catch (ValidationException e) {
            System.out.println( e.getMessage().equals("Every word from last name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n"));
        }

        User u4 = new User("123", "Dan");
        u4.setId(32456L);
        try {
            userValidator.validate(u4);
            assert false;
        } catch (ValidationException e) {
            System.out.println( e.getMessage().equals("Every word from first name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n"));
        }

        User u5 = new User("Daniel", "     ");
        u5.setId(8765L);
        try {
            userValidator.validate(u5);
            assert true;
        } catch (ValidationException e) {
            System.out.println( e.getMessage().equals("Every word from last name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n"));
        }

        User u6 = new User("Andra", "Pop");
        try{
            userValidator.validate(u6);
            assert true;
        }catch (ValidationException e){
            System.out.println(e.getMessage().equals("The id cannot be null!\n"));
        }

        FriendshipValidator friendshipValidator = new FriendshipValidator();
        Friendship f1 = new Friendship(u1.getId(), u2.getId());
        try{
            friendshipValidator.validate(f1);
            assert false;
        } catch (ValidationException e){
            System.out.println(e.getMessage().equals("The id cannot be null!"));
        }

        Friendship f2 = new Friendship(u1.getId(), u6.getId());
        f2.setId(4356L);
        try{
            friendshipValidator.validate(f2);
            assert false;
        } catch (ValidationException e){
            System.out.println(e.getMessage().equals("The id cannot be null!"));
        }

        Friendship f3 = new Friendship(u6.getId(), u2.getId());
        f3.setId(6543L);
        try{
            friendshipValidator.validate(f3);
            assert false;
        } catch (ValidationException e){
            System.out.println(e.getMessage().equals("The id cannot be null!"));
        }
    }
}
*/