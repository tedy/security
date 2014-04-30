package ar.coop.arena.security.shared.project;

import java.security.BasicPermission;

public class ReadTargetsTreePermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadTargetsTreePermission() {
    super("ReadTargetsTree");
  }
}
