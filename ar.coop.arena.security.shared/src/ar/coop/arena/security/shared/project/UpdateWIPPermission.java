package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class UpdateWIPPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateWIPPermission() {
    super("UpdateWIP");
  }
}
