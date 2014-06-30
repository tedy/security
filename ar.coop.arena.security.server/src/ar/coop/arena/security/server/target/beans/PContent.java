package ar.coop.arena.security.server.target.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class PContent {

  private String id;
  private String content;

  public PContent() {
  }

  public PContent(String id, String content) {
    this.id = id;
    this.content = content;
  }

  @XmlAttribute
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @XmlValue
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
