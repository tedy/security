package ar.coop.arena.security.shared.services;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

public class DesktopFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public DesktopFormData() {
  }

  public TargetsTree getTargetsTree() {
    return getFieldByClass(TargetsTree.class);
  }

  public static class TargetsTree extends AbstractValueFieldData<Long[]> {
    private static final long serialVersionUID = 1L;

    public TargetsTree() {
    }
  }
}
