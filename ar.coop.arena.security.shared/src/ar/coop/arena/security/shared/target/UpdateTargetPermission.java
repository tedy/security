package ar.coop.arena.security.shared.target;

import java.security.BasicPermission;

public class UpdateTargetPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateTargetPermission() {
    super("UpdateTarget");
  }
}
