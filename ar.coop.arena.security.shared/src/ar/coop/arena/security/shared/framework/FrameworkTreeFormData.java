package ar.coop.arena.security.shared.framework;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import ar.coop.arena.security.shared.framework.services.lookup.FrameworkLookupCall;

public class FrameworkTreeFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public FrameworkTreeFormData() {
  }

  public FrameworkNrProperty getFrameworkNrProperty() {
    return getPropertyByClass(FrameworkNrProperty.class);
  }

  /**
   * access method for property FrameworkNr.
   */
  public Integer getFrameworkNr() {
    return getFrameworkNrProperty().getValue();
  }

  /**
   * access method for property FrameworkNr.
   */
  public void setFrameworkNr(Integer frameworkNr) {
    getFrameworkNrProperty().setValue(frameworkNr);
  }

  public FrameworkTree getFrameworkTree() {
    return getFieldByClass(FrameworkTree.class);
  }

  public class FrameworkNrProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public FrameworkNrProperty() {
    }
  }

  public static class FrameworkTree extends AbstractValueFieldData<Long[]> {
    private static final long serialVersionUID = 1L;

    public FrameworkTree() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, FrameworkLookupCall.class);
    }
  }
}
