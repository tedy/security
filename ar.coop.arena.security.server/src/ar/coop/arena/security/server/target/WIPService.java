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
  public WIPFormData prepareCreate(WIPFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public WIPFormData create(WIPFormData formData) throws ProcessingException {
    //TODO [tedy] business logic here.
    return formData;
  }

  @Override
  public WIPFormData load(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    /*SQL.selectInto("SELECT CONTENT FROM TARGET " +
        "WHERE  TARGETID = :nodeNr INTO   :content", formData);*/
    Object[][] result = SQL.select("SELECT PATH AS ICONID, PORT, PROTOCOL, NAME, CONTENT FROM TARGETITEM "
        + " LEFT JOIN RISK ON (TARGETITEM.RISKID=RISK.RISKID) "
        + " LEFT JOIN ICONS ON (RISK.ICONID=ICONS.ICONID)"
        + "WHERE  TARGETITEMID = :nodeNr", formData);

    if (result != null && result.length > 0 && result[0].length > 0) {
//      HTMLContent content = new HTMLContent((String) result[0][4]);
//      formData.getContent().setValue(unparseHTML(content));
      formData.getContent().setValue(escapeBr((String) result[0][4]));
    }
    return formData;
  }

  @Override
  public WIPFormData store(WIPFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateWIPPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    HTMLContent content = parseHTML(formData.getContent().getValue());
    formData.getContent().setValue(content.getBody().trim());
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

  private HTMLContent parseHTML(String content) throws ProcessingException {
    content = Pattern.compile("<br>").matcher(content).replaceAll("\n");
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
