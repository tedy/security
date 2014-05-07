package ar.coop.arena.security.shared.runner;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class RunToolFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public RunToolFormData() {
  }

  public ToolNrProperty getToolNrProperty() {
    return getPropertyByClass(ToolNrProperty.class);
  }

  /**
   * access method for property ToolNr.
   */
  public Long getToolNr() {
    return getToolNrProperty().getValue();
  }

  /**
   * access method for property ToolNr.
   */
  public void setToolNr(Long toolNr) {
    getToolNrProperty().setValue(toolNr);
  }

  public Command getCommand() {
    return getFieldByClass(Command.class);
  }

  public class ToolNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public ToolNrProperty() {
    }
  }

  public static class Command extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Command() {
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
