package ar.coop.arena.security.shared.framework;

import java.security.BasicPermission;

public class CreateFrameworksPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateFrameworksPermission() {
    super("CreateFrameworks");
  }
}
