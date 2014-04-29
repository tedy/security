package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class ReadItemPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadItemPermission() {
    super("ReadItem");
  }
}
