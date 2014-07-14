package ar.coop.arena.security.server.framework.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Framework {
  private Integer frameworkId;
  private String name;
  private String author;
  private String info;
  private String version;
  private Date lastDate;

  public Framework() {
  }

  public Framework(Integer id, String name, String author, String info, String version) {
    super();
    this.frameworkId = id;
    this.name = name;
    this.author = author;
    this.info = info;
    this.version = version;
  }

  @XmlAttribute(name = "id")
  public Integer getFrameworkId() {
    return frameworkId;
  }

  public void setFrameworkId(Integer id) {
    this.frameworkId = id;
  }

  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @XmlElement
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @XmlElement
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @XmlTransient
  public Date getLastDate() {
    return lastDate;
  }

  public void setLastDate(Date lastDate) {
    this.lastDate = lastDate;
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
