package domain.validators;

import domain.User;
import exceptions.ValidationException;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String err = "";
        //id
        if(entity.getId()==null){
            err+="The id cannot be null!\n";
        }

        //firstName
        String firstName = entity.getFirstName();
        if(!firstName.matches("^[A-Z][a-z]{2,}(\\s?-?[A-Z][a-z]{2,})*$")){
            err+="Every word from first name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n";
        }

        //lastName
        String lastName = entity.getLastName();
        if(!lastName.matches("^[A-Z][a-z]{2,}(\\s?-?[A-Z][a-z]{2,})*$")){
            err+="Every word from last name must start with capital letter, have between 3 and 50 characters and if there are more words, they can be separated by one space or minus sign!\n";
        }

        if(err.length()>0){
            throw new ValidationException(err);
        }
    }
}
