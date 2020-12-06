package softuni.exam.instagraphlite.util;

import javax.validation.ConstraintViolation;


// ToDo Implement interface 
public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
