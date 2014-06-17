package ar.coop.arena.security.client.framework;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.CancelButton;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.FrameworksField;
import ar.coop.arena.security.client.framework.FrameworksForm.MainBox.OkButton;
import ar.coop.arena.security.shared.framework.FrameworksFormData;
import ar.coop.arena.security.shared.framework.IFrameworksService;
import ar.coop.arena.security.shared.framework.UpdateFrameworksPermission;

@FormData(value = FrameworksFormData.class, sdkCommand = SdkCommand.CREATE)
public class FrameworksForm extends AbstractForm {

  private Long frameworksNr;

  public FrameworksForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Frameworks");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getFrameworksNr() {
    return frameworksNr;
  }

  @FormData
  public void setFrameworksNr(Long frameworksNr) {
    this.frameworksNr = frameworksNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public FrameworksField getFrameworksField() {
    return getFieldByClass(FrameworksField.class);
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
    public class FrameworksField extends AbstractTableField<FrameworksField.Table> {

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected void execInitField() throws ProcessingException {
        /*IRemoteFileService rfs = SERVICES.getService(IRemoteFileService.class);
        ((RemoteFileService) rfs).setRootPath("/home/tedy/security/");
        RemoteFile[] files = rfs.getRemoteFiles("frameworks", new NoFilenameFilter(), null);
        for (int i = 0; i < files.length; i++) {
          System.out.println(files[i].getName());
          try {
            System.out.println(new String(files[i].extractData()));

            ITableRow row = getTable().createRow();
            getTable().getNameColumn().setValue(row, "Smith");
            getTable().getAuthorColumn().setValue(row, "");
            getTable().getInfoColumn().setValue(row, "");
        //            getTable().getVersionColumn().setValue(row, DateUtility.parse("14.12.1970", "dd.MM.yyyy"));
            getTable().getFileNameColumn().setValue(row, files[i].getName());
            getTable().addRow(row, true);

        //            getTable().addRowByArray(files);
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }*/
        IFrameworksService service = SERVICES.getService(IFrameworksService.class);
        getTable().addRowsByMatrix(service.loadFrameworksFromFileSystem(null));
      }

      @Order(10.0)
      public class Table extends AbstractExtensibleTable {

        public InfoColumn getInfoColumn() {
          return getColumnSet().getColumnByClass(InfoColumn.class);
        }

        public VersionColumn getVersionColumn() {
          return getColumnSet().getColumnByClass(VersionColumn.class);
        }

        public FileNameColumn getFileNameColumn() {
          return getColumnSet().getColumnByClass(FileNameColumn.class);
        }

        public AuthorColumn getAuthorColumn() {
          return getColumnSet().getColumnByClass(AuthorColumn.class);
        }

        public NameColumn getNameColumn() {
          return getColumnSet().getColumnByClass(NameColumn.class);
        }

        @Order(10.0)
        public class NameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Name");
          }
        }

        @Order(20.0)
        public class AuthorColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Author");
          }
        }

        @Order(30.0)
        public class InfoColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Info");
          }
        }

        @Order(40.0)
        public class VersionColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Version");
          }
        }

        @Order(50.0)
        public class FileNameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("FileName");
          }
        }
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IFrameworksService service = SERVICES.getService(IFrameworksService.class);
      FrameworksFormData formData = new FrameworksFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateFrameworksPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IFrameworksService service = SERVICES.getService(IFrameworksService.class);
      FrameworksFormData formData = new FrameworksFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  /*public class NoFilenameFilter implements FilenameFilter, Serializable {
    @Override
    public boolean accept(File dir, String name) {
      return true;
    }
  }*/
}
