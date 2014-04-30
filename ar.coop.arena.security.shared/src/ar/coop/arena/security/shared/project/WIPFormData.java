package ar.coop.arena.security.shared.project;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

public class WIPFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public WIPFormData() {
  }

  public WIP getWIP() {
    return getFieldByClass(WIP.class);
  }

  public static class WIP extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public WIP() {
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
