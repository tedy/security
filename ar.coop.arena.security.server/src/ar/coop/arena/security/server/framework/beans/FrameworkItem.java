package ar.coop.arena.security.server.framework.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//@XmlAccessorType(XmlAccessType.FIELD)
public class FrameworkItem {
  private Integer frameworkId;
//  private Integer frameworkItemId;
  private Integer itemId;
  private String description;
  private Boolean status;

//  private Integer parentFrameworkId;
//  private Integer parentFrameworkItemId;

  public FrameworkItem() {
  }

  public FrameworkItem(Integer frameworkId, Integer itemId, String description, Boolean status) {
    super();
    this.frameworkId = frameworkId;
    this.itemId = itemId;
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
  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
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
