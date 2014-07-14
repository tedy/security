package ar.coop.arena.security.server.framework;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.BeanArrayHolder;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.file.RemoteFileService;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.IRemoteFileService;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.server.common.NoFilenameFilter;
import ar.coop.arena.security.server.common.ParserXML;
import ar.coop.arena.security.server.framework.beans.Framework;
import ar.coop.arena.security.server.framework.beans.Framework4List;
import ar.coop.arena.security.server.framework.beans.FrameworkItem;
import ar.coop.arena.security.shared.framework.FrameworksFormData;
import ar.coop.arena.security.shared.framework.IFrameworksService;
import ar.coop.arena.security.shared.framework.ReadFrameworksPermission;
import ar.coop.arena.security.shared.framework.UpdateFrameworksPermission;

public class FrameworksService extends AbstractService implements IFrameworksService {

  // TODO make a config.property
  private String m_frameworksDir = "frameworks/";

  @Override
  public Object[][] loadFrameworksFromFileSystem(Integer ProjectId) throws ProcessingException {
    IRemoteFileService rfs = SERVICES.getService(IRemoteFileService.class);
    ((RemoteFileService) rfs).setRootPath("/home/tedy/security/");
    RemoteFile[] files = rfs.getRemoteFiles(m_frameworksDir, new NoFilenameFilter(), null);
    Object[][] result = new Object[files.length][5];
    for (int i = 0; i < files.length; i++) {
      try {
        ParserXML<Framework4List> parser = new ParserXML<Framework4List>(Framework4List.class);
        Framework4List fwk = parser.unmarshall(files[i].extractData());
        result[i][0] = fwk.getName();
        result[i][1] = fwk.getAuthor();
        result[i][2] = fwk.getInfo();
        result[i][3] = fwk.getVersion();
        result[i][4] = files[i].getName();
      }
      catch (IOException e) {
        throw new ProcessingException("Could not parse or process directory " + m_frameworksDir, e);
      }
    }
    return result;
  }

  @Override
  public FrameworksFormData load(FrameworksFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadFrameworksPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    /*SQL.selectInto("" +
        "SELECT FRAMEWORKID, VERSION, NAME, AUTHOR, INFO  " +
        "FROM   FRAMEWORK " +
        //        "WHERE  " +
    //        "(FRAMEWORKID = :{fwk.frameworkId} AND VERSION = :{fwk.version})" +
    //        " OR NAME = :{fwk.name}" +
        "INTO :frameworks"
        , formData
        );*/
    return formData;
  }

  @Override
  public FrameworksFormData store(FrameworksFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateFrameworksPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    Framework fwkXML;
    try { // get the framework file selected
      IRemoteFileService rfs = SERVICES.getService(IRemoteFileService.class);
      ((RemoteFileService) rfs).setRootPath("/home/tedy/security/");
      RemoteFile remoteFile = rfs.getRemoteFile(new RemoteFile(m_frameworksDir, formData.getSelectedFile(), 0L));

      ParserXML<Framework> parser = new ParserXML<Framework>(Framework.class);
      fwkXML = parser.unmarshall(remoteFile.extractData());
      fwkXML.setLastDate(new Date(remoteFile.getLastModified()));
    }
    catch (IOException e) {
      throw new ProcessingException("Could not parse or process remote file or directory " + m_frameworksDir, e);
    }

    try {
      Framework fwkInDB = getFramework(fwkXML);
      if (fwkInDB == null) { //if do not exist in db, insert it
        fwkInDB = this.insertFramework(fwkXML);
        insertFrameworkItems(fwkXML.getItems(), fwkInDB.getFrameworkId());
      }
      if (fwkInDB != null && formData.getProjectId() != null) { // && fwkXML.getLastDate().equals(fwkInDB.getLastDate())
        SQL.update("UPDATE PROJECT SET"
            + " FRAMEWORKID = :{fwk.frameworkId} ,"
            + " VERSION = :{fwk.version} "
            + " WHERE PROJECTID = :projectId"
            , new NVPair("projectId", formData.getProjectId()
                , Integer.class), new NVPair("fwk", fwkInDB, Framework.class));
      }
//      if (fwkXML.getLastDate().after(fwkInDB.getLastDate()))
    }
    catch (ProcessingException e) {
      SQL.rollback();
      throw new ProcessingException("Could not persist remote file.", e);
    }
    return formData;
  }

  public Framework getFramework(Framework fwk) {
    // Verify if Framework exist with (id and name and version) OR (name and version) 4 recent insert
    BeanArrayHolder<Framework> beanHolder = new BeanArrayHolder<Framework>(Framework.class);
    try {
      SQL.selectInto("" +
          "SELECT FRAMEWORKID, VERSION, NAME, AUTHOR, INFO, LASTDATE " +
          "FROM   FRAMEWORK " +
          "WHERE  " +
          "(FRAMEWORKID = :{fwk.frameworkId} AND NAME = :{fwk.name} AND VERSION = :{fwk.version})" +
          " OR (NAME = :{fwk.name} AND VERSION = :{fwk.version})" + //AND LASTDATE = :{fwk.lastDate}
          "INTO :{frameworkId}, :{version}, :{name}, :{author}, :{info}, :{lastDate}"
          , beanHolder, new NVPair("fwk", fwk, Framework.class)
          );
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(FrameworksService.class).error("failed to load Framework data", e);
    }
    Framework[] array = beanHolder.getBeans();
    if (array.length < 1) return null;
    return array[0];
  }

  // the file name its dosnt important that can rename the file and change version
  public Framework insertFramework(Framework fwk) {
    try {
      SQL.insert(
          //FRAMEWORKID,
          "INSERT INTO FRAMEWORK (VERSION, NAME, AUTHOR, INFO, LASTDATE) " +
              // :{fwk.frameworkId}, 
              "VALUES (:{fwk.version}, :{fwk.name}, :{fwk.author}, :{fwk.info}, :{fwk.lastDate}) "
          , new NVPair("fwk", fwk, Framework.class));
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(FrameworksService.class).error("failed to insert Framework data", e);
    }
    return getFramework(fwk);
  }

  public FrameworkItem getFrameworkItem(FrameworkItem fwkItem) {
    // Verify if FrameworkItem exist with (id and name and version) OR (name and version)
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
      ScoutLogManager.getLogger(FrameworksService.class).error("failed to load FrameworkItem data", e);
    }
    FrameworkItem[] array = beanHolder.getBeans();
    if (array.length < 1) return null;
    return array[0];
  }

  private void insertFrameworkItems(List<FrameworkItem> items, Integer... ids) throws ProcessingException {
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
      insertFrameworkItems(fwkItemXML.getItems(), fwkItemXML.getFrameworkId(), fwkItemInDB.getFrameworkItemId());
    }
  }
}
