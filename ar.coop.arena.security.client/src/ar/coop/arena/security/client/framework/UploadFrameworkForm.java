package ar.coop.arena.security.client.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.framework.UploadFrameworkForm.MainBox.CancelButton;
import ar.coop.arena.security.client.framework.UploadFrameworkForm.MainBox.FileNameField;
import ar.coop.arena.security.client.framework.UploadFrameworkForm.MainBox.OkButton;
import ar.coop.arena.security.shared.framework.IUploadFrameworkService;
import ar.coop.arena.security.shared.framework.UploadFrameworkFormData;

@FormData(value = UploadFrameworkFormData.class, sdkCommand = SdkCommand.CREATE)
public class UploadFrameworkForm extends AbstractForm {

  private RemoteFile m_remoteFile;

  public UploadFrameworkForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Framework");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public FileNameField getFileNameField() {
    return getFieldByClass(FileNameField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class FileNameField extends AbstractFileChooserField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("FileName");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected boolean getConfiguredTypeLoad() {
        return true;
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IUploadFrameworkService service = SERVICES.getService(IUploadFrameworkService.class);
      UploadFrameworkFormData formData = new UploadFrameworkFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      File file = getFileNameField().getValueAsFile();
      String name = IOUtility.getFileName(file.getAbsolutePath());
      String fileExtension = IOUtility.getFileExtension(name);
      try {
        RemoteFile remoteFile = new RemoteFile(name, System.currentTimeMillis());
        remoteFile.readData(new FileInputStream(file));
        remoteFile.setContentLength((int) file.length());
        remoteFile.setContentTypeByExtension(fileExtension);
        setRemoteFile(remoteFile);
      }
      catch (IOException e) {
        throw new ProcessingException("Could not remote file", e);
      }

      //TODO cuando hace el initialize rootPath lo harcodea null!
      /*IRemoteFileService remoteFileService = SERVICES.getService(IRemoteFileService.class);
      //      remoteFileService.putRemoteFile(getRemoteFile());
      RemoteFile[] files = remoteFileService.getRemoteFiles("security", new NoFilenameFilter(), null);
      for (int i = 0; i < files.length; i++) {
        System.out.println(files[i].getName());
        try {
          System.out.println(new String(files[i].extractData()));
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }*/

      IUploadFrameworkService service = SERVICES.getService(IUploadFrameworkService.class);
      UploadFrameworkFormData formData = new UploadFrameworkFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }

  @FormData
  public RemoteFile getRemoteFile() {
    return m_remoteFile;
  }

  @FormData
  public void setRemoteFile(RemoteFile remoteFile) {
    m_remoteFile = remoteFile;
  }

  /*private class NoFilenameFilter implements FilenameFilter, Serializable {
    @Override
    public boolean accept(File dir, String name) {
      return true;
    }
  }*/
}
