package ar.coop.arena.security.shared.framework;

import java.security.BasicPermission;

public class ReadFrameworksPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadFrameworksPermission() {
    super("ReadFrameworks");
  }
}
