package ar.coop.arena.security.server.framework.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "framework")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Framework4List {
  private Integer frameworkId;
  private String name;
  private String author;
  private String info;
  private String version;

  public Framework4List() {
  }

  public Framework4List(Integer id, String name, String author, String info, String version) {
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

  @XmlAttribute
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}
