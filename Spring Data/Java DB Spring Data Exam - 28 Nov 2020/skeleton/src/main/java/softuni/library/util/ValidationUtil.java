package softuni.library.util;

import javax.validation.ConstraintViolation;


// ToDo Implement interface 
public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
