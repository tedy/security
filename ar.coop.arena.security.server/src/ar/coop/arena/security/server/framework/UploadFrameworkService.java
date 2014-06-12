package ar.coop.arena.security.server.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.server.framework.beans.Framework;
import ar.coop.arena.security.shared.framework.IUploadFrameworkService;
import ar.coop.arena.security.shared.framework.UploadFrameworkFormData;

public class UploadFrameworkService extends AbstractService implements IUploadFrameworkService {

  @Override
  public UploadFrameworkFormData prepareCreate(UploadFrameworkFormData formData) throws ProcessingException {
    return formData;
  }

  @Override
  public UploadFrameworkFormData create(UploadFrameworkFormData formData) throws ProcessingException {
    try {
      // get content from remote file
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      formData.getRemoteFile().writeData(bos);
      bos.flush();

      Framework fwk = parseXML(bos.toByteArray());

      Object[][] result = SQL.select("" +
          "SELECT NAME, AUTHOR, INFO " +
          "FROM   FRAMEWORK " +
          "WHERE  FRAMEWORKID = " + fwk.getFrameworkId()// :frameworkId "
          + " AND VERSION = '" + fwk.getVersion() + "'"// :version"
          //+ "INTO   :risk, :port, :name"
      , formData);

      if (result == null) {

      }
      /*int updated = SQL.update("" +
          " UPDATE FILES " +
          "    SET CONTENT = :content " +
          "  WHERE FILENAME = :filename "
          , new NVPair("filename", formData.getRemoteFile().getName())
          , new NVPair("content", content)
          );

      if (updated == 0) {
        SQL.insert("" +
            " INSERT INTO FILES " +
            "    (FILENAME, CONTENT) " +
            "   VALUES " +
            "    (:filename, :content) "
            , new NVPair("filename", formData.getRemoteFile().getName())
            , new NVPair("content", content)
            );
      }*/
    }
    catch (IOException e) {
      throw new ProcessingException("Could not persist remote file.", e);
    }
    return formData;
  }

  private Framework parseXML(byte[] content) throws ProcessingException {
    ByteArrayInputStream bis = new ByteArrayInputStream(content);

    Framework fwk;
    try {
      JAXBContext jc = JAXBContext.newInstance(Framework.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      StreamSource source = new StreamSource(bis);
      fwk = (Framework) unmarshaller.unmarshal(source);
    }
    catch (JAXBException e) {
      throw new ProcessingException(e.getMessage(), e);
    }
    return fwk;
  }

  @Override
  public UploadFrameworkFormData load(UploadFrameworkFormData formData) throws ProcessingException {
    return formData;
  }

  @Override
  public UploadFrameworkFormData store(UploadFrameworkFormData formData) throws ProcessingException {
    return formData;
  }
}
