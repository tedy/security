package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class ReadWIPPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadWIPPermission() {
    super("ReadWIP");
  }
}
