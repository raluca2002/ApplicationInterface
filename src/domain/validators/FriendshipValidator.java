package domain.validators;

import domain.Friendship;
import exceptions.ValidationException;

public class FriendshipValidator implements Validator<Friendship> {
    @Override
    public void validate(Friendship friendship) throws ValidationException {
        if(friendship.getId()==null || friendship.getId1()==null || friendship.getId2()==null){
            throw new ValidationException("The id cannot be null!");
        }
        if(friendship.getId1()== friendship.getId2()){
            throw new ValidationException("The friendship relation can't be between an user and himself!\n");
        }
    }
}
