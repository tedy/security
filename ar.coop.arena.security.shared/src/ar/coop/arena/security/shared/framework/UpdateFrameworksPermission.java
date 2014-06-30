package ar.coop.arena.security.shared.framework;

import java.security.BasicPermission;

public class UpdateFrameworksPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateFrameworksPermission() {
    super("UpdateFrameworks");
  }
}
