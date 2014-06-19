package ar.coop.arena.security.server.target.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "html")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"head", "body"})
public class HTMLContent {

  private String head;
  private String body;

  public HTMLContent() {
  }

  public HTMLContent(String body) {
    this.body = body;
  }

//  @XmlElement
  public String getHead() {
    return head;
  }

  public void setHead(String head) {
    this.head = head;
  }

//  @XmlElement
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

}
