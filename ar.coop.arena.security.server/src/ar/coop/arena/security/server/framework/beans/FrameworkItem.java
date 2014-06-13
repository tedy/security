package ar.coop.arena.security.server.framework.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//@XmlAccessorType(XmlAccessType.FIELD)
public class FrameworkItem {
  private Integer frameworkId;
  private Integer frameworkItemId;
  private String description;
  private Boolean status;

  private Integer parentFrameworkId;
  private Integer parentFrameworkItemId;

  public FrameworkItem() {
  }

  public FrameworkItem(Integer frameworkId, Integer itemId, String description, Boolean status) {
    super();
    this.frameworkId = frameworkId;
    this.frameworkItemId = itemId;
    this.description = description;
    this.status = status;
  }

  public Integer getFrameworkId() {
    return frameworkId;
  }

  public void setFrameworkId(Integer frameworkId) {
    this.frameworkId = frameworkId;
  }

  @XmlAttribute(name = "id")
  public Integer getFrameworkItemId() {
    return frameworkItemId;
  }

  public void setFrameworkItemId(Integer itemId) {
    this.frameworkItemId = itemId;
  }

  @XmlElement
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Integer getParentFrameworkId() {
    return parentFrameworkId;
  }

  public void setParentFrameworkId(Integer parentFrameworkId) {
    this.parentFrameworkId = parentFrameworkId;
  }

  public Integer getParentFrameworkItemId() {
    return parentFrameworkItemId;
  }

  public void setParentFrameworkItemId(Integer parentFrameworkItemId) {
    this.parentFrameworkItemId = parentFrameworkItemId;
  }

  private List<FrameworkItem> items = new ArrayList<FrameworkItem>();

  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  public List<FrameworkItem> getItems() {
    return items;
  }

  public void setItems(List<FrameworkItem> items) {
    this.items = items;
  }

}
