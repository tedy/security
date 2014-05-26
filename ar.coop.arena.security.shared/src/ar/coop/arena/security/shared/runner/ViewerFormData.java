package ar.coop.arena.security.shared.runner;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

public class ViewerFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public ViewerFormData() {
  }

  public Viewer getViewer() {
    return getFieldByClass(Viewer.class);
  }

  public ViewerDND getViewerDND() {
    return getFieldByClass(ViewerDND.class);
  }

  public static class Viewer extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Viewer() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, Integer.MAX_VALUE);
    }
  }

  public static class ViewerDND extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public ViewerDND() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}
