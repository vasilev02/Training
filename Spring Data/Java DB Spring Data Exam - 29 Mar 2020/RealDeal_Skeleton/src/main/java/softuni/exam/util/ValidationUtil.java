package softuni.exam.util;

import javax.validation.ConstraintViolation;
// ToDo
public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
