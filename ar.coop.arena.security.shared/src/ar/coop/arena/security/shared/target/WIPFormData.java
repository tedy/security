package ar.coop.arena.security.shared.target;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class WIPFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public WIPFormData() {
  }

  public NodeNrProperty getNodeNrProperty() {
    return getPropertyByClass(NodeNrProperty.class);
  }

  /**
   * access method for property NodeNr.
   */
  public Long getNodeNr() {
    return getNodeNrProperty().getValue();
  }

  /**
   * access method for property NodeNr.
   */
  public void setNodeNr(Long nodeNr) {
    getNodeNrProperty().setValue(nodeNr);
  }

  public NodeTypeProperty getNodeTypeProperty() {
    return getPropertyByClass(NodeTypeProperty.class);
  }

  /**
   * access method for property NodeType.
   */
  public int getNodeType() {
    return (getNodeTypeProperty().getValue() == null) ? (0) : (getNodeTypeProperty().getValue());
  }

  /**
   * access method for property NodeType.
   */
  public void setNodeType(int nodeType) {
    getNodeTypeProperty().setValue(nodeType);
  }

  public Content getContent() {
    return getFieldByClass(Content.class);
  }

  public class NodeNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public NodeNrProperty() {
    }
  }

  public class NodeTypeProperty extends AbstractPropertyData<Integer> {
    private static final long serialVersionUID = 1L;

    public NodeTypeProperty() {
    }
  }

  public static class Content extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Content() {
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
}
