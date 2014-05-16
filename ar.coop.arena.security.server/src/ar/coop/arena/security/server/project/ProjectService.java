package ar.coop.arena.security.server.project;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.project.CreateProjectPermission;
import ar.coop.arena.security.shared.project.IProjectService;
import ar.coop.arena.security.shared.project.ProjectFormData;
import ar.coop.arena.security.shared.project.ReadProjectPermission;
import ar.coop.arena.security.shared.project.UpdateProjectPermission;

public class ProjectService extends AbstractService implements IProjectService {

  @Override
  public ProjectFormData prepareCreate(ProjectFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateProjectPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Piojo] business logic here
    return formData;
  }

  @Override
  public ProjectFormData create(ProjectFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateProjectPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.selectInto("SELECT MAX(PROJECTID)+1 FROM PROJECT " +
        //        "WHERE PROJECTID = :projectNr " +
        "INTO :projectNr", formData);
    SQL.insert("" +
        "INSERT INTO PROJECT (PROJECTID, NAME, CUSTOMER, STARTDATE, ENDDATE, AUTHOR"
        //        + ", WORKDIR, FRAMEWORKID, VERSION"
        + ") " +
        "VALUES (:projectNr, :name, :customer, :startDate, :endDate, :author"
        //        + ", :workdir, :fremeworkId, :version"
        + ")"
        , formData);
    return formData;
  }

  @Override
  public ProjectFormData load(ProjectFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadProjectPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.selectInto("SELECT NAME, CUSTOMER, STARTDATE, ENDDATE, AUTHOR "
        //        + ", WORKDIR, FRAMEWORKID, VERSION"
        + " FROM PROJECT "
        + " WHERE PROJECTID = :projectNr "
        + " INTO :name, :customer, :startDate, :endDate, :author "
        //        + ", :workdir, :fremeworkId, :version"
        , formData);
    return formData;
  }

  @Override
  public ProjectFormData store(ProjectFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateProjectPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    SQL.update("UPDATE PROJECT SET"
        + " NAME = :name,"
        + " CUSTOMER = :customer, "
        + " STARTDATE = :startDate, "
        + " ENDDATE = :endDate, "
        + " AUTHOR = :author "
        //        + ", :workdir, :fremeworkId, :version"
        + " WHERE PROJECTID = :projectNr", formData);
    return formData;
  }
}
