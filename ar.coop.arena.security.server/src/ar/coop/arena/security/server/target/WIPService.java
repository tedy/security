package ar.coop.arena.security.server.target;

import java.io.StringReader;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.server.target.beans.HTMLContent;
import ar.coop.arena.security.shared.target.IWIPService;
import ar.coop.arena.security.shared.target.ReadWIPPermission;
import ar.coop.arena.security.shared.target.UpdateWIPPermission;
import ar.coop.arena.security.shared.target.WIPFormData;

public class WIPService extends AbstractService implements IWIPService {

  @Override
  public WIPFormData load(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }

    if (formData.getNodeType() != 0) {
      String sql = "SELECT PATH AS ICONID, PORT, PROTOCOL, NAME, CONTENT FROM TARGETITEM "
          + " LEFT JOIN RISK ON (TARGETITEM.RISKID=RISK.RISKID) "
          + " LEFT JOIN ICONS ON (RISK.ICONID=ICONS.ICONID)";
      if (formData.getNodeType() == 1) sql += "WHERE  TARGETID = :nodeNr";
      if (formData.getNodeType() == 2) sql += "WHERE  TARGETITEMID = :nodeNr";

      Object[][] result = SQL.select(sql, formData);

      if (result != null) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
//      HTMLContent content = new HTMLContent((String) result[0][4]);
//      formData.getContent().setValue(unparseHTML(content));
          if (i > 0) sb.append("<br/><br/><hr/><br/>");

          sb.append("<p style=\"color:red;font-size:14px;\" id=\"header\">");
          sb.append(result[i][1]);
          sb.append("  ");
          sb.append(result[i][2]);
          sb.append("  ");
          sb.append(result[i][3]);
          sb.append("</p>");
          sb.append("<br/><hr/>");
          sb.append("<p id=\"content\">");
          sb.append(escapeBr((String) result[i][4]));
          sb.append("<br/></p>");
        }
        formData.getContent().setValue(sb.toString());
      }
    }
    return formData;
  }

  @Override
  public WIPFormData store(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    HTMLContent content = unmarshalHTML(formData.getContent().getValue());
    formData.getContent().setValue(content.getContent().trim());
    SQL.update("UPDATE TARGETITEM SET CONTENT = :content "
        + "WHERE  TARGETITEMID = :nodeNr", formData);
    return formData;
  }

  /*private String unparseHTML(HTMLContent html) throws ProcessingException {
    StringWriter content = new StringWriter();
    try {
      JAXBContext jc = JAXBContext.newInstance(HTMLContent.class);
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
  //      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(html, content);
    }
    catch (JAXBException e) {
      throw new ProcessingException(e.getMessage(), e);
    }
    return content.toString();
  }*/

  private HTMLContent unmarshalHTML(String content) throws ProcessingException {
    content = Pattern.compile("<br>").matcher(content).replaceAll("\n");
    content = Pattern.compile("<hr>").matcher(content).replaceAll("");
//    content = content.replaceAll("<br>", "\\n");
    HTMLContent html;
    try {
      JAXBContext jc = JAXBContext.newInstance(HTMLContent.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      StreamSource source = new StreamSource(new StringReader(content));
      html = (HTMLContent) unmarshaller.unmarshal(source);
    }
    catch (JAXBException e) {
      throw new ProcessingException(e.getMessage(), e);
    }
    return html;
  }

  public static String escapeBr(String original) {
    if (original == null) return "";
    StringBuffer out = new StringBuffer("");
    char[] chars = original.toCharArray();
    for (int i = 0; i < chars.length; i++)
    {
      boolean found = true;
      switch (chars[i])
      {
        case '\n':
          out.append("<br/>");
          break; //newline
        case '\r':
          break;
        default:
          found = false;
          break;
      }
      if (!found) out.append(chars[i]);

    }
    return out.toString();
  }
}
