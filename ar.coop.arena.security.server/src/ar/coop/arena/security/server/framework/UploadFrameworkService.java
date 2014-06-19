package ar.coop.arena.security.server.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.BeanArrayHolder;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.server.framework.beans.Framework;
import ar.coop.arena.security.server.framework.beans.FrameworkItem;
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

      Framework fwkXML = parseXML(bos.toByteArray());

      Framework fwkInDB = getFramework(fwkXML);
      if (fwkInDB == null) {
        try {
          SQL.insert(
              //FRAMEWORKID,
              "INSERT INTO FRAMEWORK (VERSION, NAME, AUTHOR, INFO) " +
                  // :{fwk.frameworkId}, 
                  "VALUES (:{fwk.version}, :{fwk.name}, :{fwk.author}, :{fwk.info}) "
              , new NVPair("fwk", fwkXML, Framework.class));

          fwkInDB = getFramework(fwkXML);
          insertItems(fwkXML.getItems(), fwkInDB.getFrameworkId());
        }
        catch (ProcessingException e) {
          SQL.rollback();
          throw new ProcessingException("Could not persist remote file.", e);
        }
      }
    }
    catch (IOException e) {
      throw new ProcessingException("Could not process remote file.", e);
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

  private Framework getFramework(Framework fwk) {
    // Verify if Framework exist with (id and version) OR name
    BeanArrayHolder<Framework> beanHolder = new BeanArrayHolder<Framework>(Framework.class);
    try {
      SQL.selectInto("" +
          "SELECT FRAMEWORKID, VERSION, NAME, AUTHOR, INFO  " +
          "FROM   FRAMEWORK " +
          "WHERE  " +
          "(FRAMEWORKID = :{fwk.frameworkId} AND VERSION = :{fwk.version})" +
          " OR NAME = :{fwk.name}" +
          "INTO :{frameworkId}, :{version}, :{name}, :{author}, :{info}"
          , beanHolder, new NVPair("fwk", fwk, Framework.class)
          );
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(UploadFrameworkService.class).error("failed to load Framework data", e);
    }
    Framework[] array = beanHolder.getBeans();
    if (array.length < 1) return null;
    return array[0];
  }

  private FrameworkItem getFrameworkItem(FrameworkItem fwkItem) {
    // Verify if FrameworkItem exist with (id and version) OR name
    BeanArrayHolder<FrameworkItem> beanHolder = new BeanArrayHolder<FrameworkItem>(FrameworkItem.class);
    try {
      SQL.selectInto("" +
          "SELECT FRAMEWORKID, FRAMEWORKITEMID, DESCRIPTION  " +
          "FROM   FRAMEWORKITEM " +
          "WHERE  " +
          "(FRAMEWORKID = :{fwkItem.frameworkId} AND DESCRIPTION = :{fwkItem.description})" +
          "INTO :{frameworkId}, :{frameworkItemId}, :{description}"
          , beanHolder, new NVPair("fwkItem", fwkItem, FrameworkItem.class)
          );
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(UploadFrameworkService.class).error("failed to load FrameworkItem data", e);
    }
    FrameworkItem[] array = beanHolder.getBeans();
    if (array.length < 1) return null;
    return array[0];
  }

  private void insertItems(List<FrameworkItem> items, Integer... ids) throws ProcessingException {
    for (FrameworkItem fwkItemXML : items) {
      fwkItemXML.setFrameworkId(ids[0]);
      if (ids.length > 1) {
        fwkItemXML.setParentFrameworkId(ids[0]);
        fwkItemXML.setParentFrameworkItemId(ids[1]);
        SQL.insert(
            "INSERT INTO FRAMEWORKITEM (FRAMEWORKID, DESCRIPTION, PARENTFRAMEWORKID, PARENTFRAMEWORKITEMID) " +
                "VALUES (:{fwkItem.frameworkId}, :{fwkItem.description}, :{fwkItem.parentFrameworkId} ,:{fwkItem.parentFrameworkItemId}) "
            , new NVPair("fwkItem", fwkItemXML, FrameworkItem.class));
      }
      else {
        SQL.insert(
            "INSERT INTO FRAMEWORKITEM (FRAMEWORKID, DESCRIPTION) " +
                "VALUES (:{fwkItem.frameworkId}, :{fwkItem.description}) "
            , new NVPair("fwkItem", fwkItemXML, FrameworkItem.class));
      }
      FrameworkItem fwkItemInDB = getFrameworkItem(fwkItemXML);

      // recursive call
      insertItems(fwkItemXML.getItems(), fwkItemXML.getFrameworkId(), fwkItemInDB.getFrameworkItemId());
    }
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
