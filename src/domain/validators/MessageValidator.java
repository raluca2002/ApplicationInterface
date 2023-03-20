package domain.validators;

import domain.Message;
import exceptions.ValidationException;

public class MessageValidator implements  Validator<Message>{


    @Override
    public void validate(Message entity) throws ValidationException {
        if(entity.getId() == null) throw new ValidationException("The ID shouldn't be null");

        if(entity.getTo() == null) throw new ValidationException("To: shouldn't be null");

        if(entity.getFrom() == null) throw new ValidationException("From: shouldn't be null");
    }
}
