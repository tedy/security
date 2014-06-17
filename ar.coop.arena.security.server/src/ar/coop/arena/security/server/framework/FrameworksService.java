package ar.coop.arena.security.server.framework;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.file.RemoteFileService;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.IRemoteFileService;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.server.framework.beans.Framework4List;
import ar.coop.arena.security.shared.framework.FrameworksFormData;
import ar.coop.arena.security.shared.framework.IFrameworksService;
import ar.coop.arena.security.shared.framework.ReadFrameworksPermission;
import ar.coop.arena.security.shared.framework.UpdateFrameworksPermission;

public class FrameworksService extends AbstractService implements IFrameworksService {

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
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public Object[][] loadFrameworksFromFileSystem(Integer ProjectId) throws ProcessingException {
    IRemoteFileService rfs = SERVICES.getService(IRemoteFileService.class);
    ((RemoteFileService) rfs).setRootPath("/home/tedy/security/");
    RemoteFile[] files = rfs.getRemoteFiles("frameworks", new NoFilenameFilter(), null);
    Object[][] result = new Object[files.length][5];
    for (int i = 0; i < files.length; i++) {
      try {
        Framework4List fwk = parseXML(files[i].extractData());
        result[i][0] = fwk.getName();
        result[i][1] = fwk.getAuthor();
        result[i][2] = fwk.getInfo();
        result[i][3] = fwk.getVersion();
        result[i][4] = files[i].getName();
      }
      catch (IOException e) {
        throw new ProcessingException(e.getMessage(), e);
      }
    }
    return result;
  }

  public class NoFilenameFilter implements FilenameFilter, Serializable {
    @Override
    public boolean accept(File dir, String name) {
      return true;
    }
  }

  private Framework4List parseXML(byte[] content) throws ProcessingException {
    ByteArrayInputStream bis = new ByteArrayInputStream(content);

    Framework4List fwk;
    try {
      JAXBContext jc = JAXBContext.newInstance(Framework4List.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      StreamSource source = new StreamSource(bis);
      fwk = (Framework4List) unmarshaller.unmarshal(source);
    }
    catch (JAXBException e) {
      throw new ProcessingException(e.getMessage(), e);
    }
    return fwk;
  }
}
