package hiberspring.service;

import java.io.FileNotFoundException;
import java.io.IOException;

//TODO
public interface EmployeeCardService {

    Boolean employeeCardsAreImported();

    String readEmployeeCardsJsonFile() throws IOException;

    String importEmployeeCards(String employeeCardsFileContent) throws FileNotFoundException;

}
