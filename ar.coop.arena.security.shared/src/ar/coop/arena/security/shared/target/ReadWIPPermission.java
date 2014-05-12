package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class ReadWIPPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadWIPPermission() {
    super("ReadWIP");
  }
}
