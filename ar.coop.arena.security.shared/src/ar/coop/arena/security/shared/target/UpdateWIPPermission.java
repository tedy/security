package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class UpdateWIPPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateWIPPermission() {
    super("UpdateWIP");
  }
}
