package ar.coop.arena.security.client.framework;

import java.util.Comparator;
import java.util.List;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.extension.client.ui.basic.tree.AbstractExtensibleTree;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import ar.coop.arena.security.client.framework.FrameworkTreeForm.MainBox.FrameworkTreeField;
import ar.coop.arena.security.client.ui.forms.DesktopForm;
import ar.coop.arena.security.shared.framework.FrameworkTreeFormData;
import ar.coop.arena.security.shared.framework.services.lookup.FrameworkLookupCall;

@FormData(value = FrameworkTreeFormData.class, sdkCommand = SdkCommand.CREATE)
public class FrameworkTreeForm extends AbstractForm {

  private Integer frameworkNr;

  public FrameworkTreeForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_W;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Framework");
  }

  @FormData
  public Integer getFrameworkNr() {
    return frameworkNr;
  }

  @FormData
  public void setFrameworkNr(Integer frameworkNr) {
    this.frameworkNr = frameworkNr;
  }

  public FrameworkTreeField getFrameworkTreeField() {
    return getFieldByClass(FrameworkTreeField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredBorderVisible() {
      return false;
    }

    @Order(10.0)
    public class FrameworkTreeField extends AbstractTreeBox<Long> {

      @Override
      protected boolean getConfiguredAutoExpandAll() {
        return true;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected Class<? extends LookupCall> getConfiguredLookupCall() {
        return FrameworkLookupCall.class;
      }

      @Override
      protected void execFilterLookupResult(LookupCall call, List<LookupRow> result) throws ProcessingException {
        java.util.Collections.sort(result, new KeyLookupRowComparator());
      }

      @Override
      protected void execPrepareLookup(LookupCall call, ITreeNode parent) throws ProcessingException {
        DesktopForm desktopForm = getDesktop().findForm(DesktopForm.class);
        call.setKey(desktopForm.getProjectId());
      }

      @Order(10.0)
      public class Tree extends AbstractExtensibleTree {

        @Override
        protected boolean getConfiguredCheckable() {
          return true;
        }
      }
    }
  }

  public class KeyLookupRowComparator implements Comparator<LookupRow> {
    @Override
    public int compare(LookupRow object1, LookupRow object2) {
      if (object1 == null) return -1;
      if (object2 == null) return 1;
      // sort on 'text' (default behaviour)
      //return object1.getText().compareTo(object2.getText());

      // sort on 'key'
      return ((Long) object1.getKey()).compareTo((Long) object2.getKey());
    }
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      //      DesktopService service = SERVICES.getService(IDesktopService.class);
      FrameworkTreeFormData formData = new FrameworkTreeFormData();
      exportFormData(formData);
      //      formData = service.load(formData);
      importFormData(formData);
    }
  }

  public void refresh() throws ProcessingException {
    getFrameworkTreeField().loadRootNode();
  }
}
