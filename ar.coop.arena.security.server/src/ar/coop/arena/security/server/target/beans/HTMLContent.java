package ar.coop.arena.security.server.target.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "html")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"head", "body"})
public class HTMLContent {

  private String head;
  private List<PContent> ps = new ArrayList<PContent>();
  
  public HTMLContent() {
  }

  public HTMLContent(String content) {
    this.ps.add(new PContent("content", content));
  }

//  @XmlElement
  public String getHead() {
    return head;
  }

  public void setHead(String head) {
    this.head = head;
  }


  @XmlElementWrapper(name = "body")
  @XmlElement(name = "p")
  public List<PContent> getP() {
    return ps;
  }

  public void setItems(List<PContent> ps) {
    this.ps = ps;
  }

  public String getContent() {
    for (PContent p : ps) {
      if ("content".equals(p.getId())) {
        return p.getContent();
      }
    }
    return "";
  }

}
