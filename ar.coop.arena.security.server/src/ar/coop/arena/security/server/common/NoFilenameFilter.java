package ar.coop.arena.security.server.common;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;

public class NoFilenameFilter implements FilenameFilter, Serializable {
  private static final long serialVersionUID = -5978758288694340031L;

  @Override
  public boolean accept(File dir, String name) {
    return true;
  }
}
