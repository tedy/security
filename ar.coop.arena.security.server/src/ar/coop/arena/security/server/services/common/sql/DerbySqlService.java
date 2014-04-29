package ar.coop.arena.security.server.services.common.sql;

import org.eclipse.scout.rt.services.common.jdbc.AbstractDerbySqlService;
import org.eclipse.scout.service.IService2;

public class DerbySqlService extends AbstractDerbySqlService implements IService2 {

  @Override
  protected String getConfiguredJdbcMappingName() {
    /*try {
      File currentDirectory = new File(new File(".").getAbsolutePath());
      System.out.println(currentDirectory.getCanonicalPath());
      System.out.println(currentDirectory.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }*/

    // TODO Ver de sacarlo y ponerlo en 
    /*URL location = DerbySqlService.class.getProtectionDomain().getCodeSource().getLocation();
    System.out.println(location.getFile());
    String dirLocation = location.getFile();
    //    String dirLocation = "/home/work/projects/arena/security/wks-scout-security/";

    return "jdbc:derby:" + dirLocation + "../db/security";*/
//    return "jdbc:derby:/home/work/projects/arena/security/wks-scout-security-V2/db/security";
//    return "jdbc:derby:security/dbSecurity;create=true";
    //Apunta al /home@user
    return "jdbc:derby:security/dbSecurity";
  }
}
