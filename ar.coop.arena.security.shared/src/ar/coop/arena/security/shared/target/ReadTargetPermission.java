package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class ReadTargetPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadTargetPermission() {
    super("ReadTarget");
  }
}
