package ar.coop.arena.security.shared.runner;

import java.security.BasicPermission;

public class UpdateRunToolPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateRunToolPermission() {
    super("UpdateRunTool");
  }
}
