package ar.coop.arena.security.server.common;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.scout.commons.exception.ProcessingException;

public class ParserXML<T> {

  final Class<T> typeParameterClass;

  public ParserXML(Class<T> typeParameterClass) {
    this.typeParameterClass = typeParameterClass;
//    this.typeParameterClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  @SuppressWarnings("unchecked")
  public T unmarshall(byte[] content) throws ProcessingException {
    ByteArrayInputStream bis = new ByteArrayInputStream(content);

    T obj;
    try {
      JAXBContext jc = JAXBContext.newInstance(typeParameterClass);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      StreamSource source = new StreamSource(bis);
      obj = (T) unmarshaller.unmarshal(source);
    }
    catch (JAXBException e) {
      throw new ProcessingException(e.getMessage(), e);
    }
    return obj;
  }
}
