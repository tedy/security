package ar.coop.arena.security.shared.runner;

import java.security.BasicPermission;

public class ReadRunToolPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadRunToolPermission() {
    super("ReadRunTool");
  }
}
