package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <O> void exportToXml(O object, String path) throws JAXBException;

    <O> O importFromXml(Class<O> klass, String path) throws JAXBException;

}
