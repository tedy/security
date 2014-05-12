package ar.coop.arena.security.server.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

import ar.coop.arena.security.shared.runner.CreateRunToolPermission;
import ar.coop.arena.security.shared.runner.IRunToolService;
import ar.coop.arena.security.shared.runner.ReadRunToolPermission;
import ar.coop.arena.security.shared.runner.RunToolFormData;
import ar.coop.arena.security.shared.runner.UpdateRunToolPermission;

public class RunToolService extends AbstractService implements IRunToolService {

  @Override
  public RunToolFormData prepareCreate(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public RunToolFormData create(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    String s = null;
    try {
      Process p = Runtime.getRuntime().exec(formData.getCommand().getValue());

      BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

      BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      StringBuilder result = new StringBuilder();
      // read the output from the command
      System.out.println("Here is the standard output of the command:\n");
      while ((s = stdInput.readLine()) != null) {
        result.append(s);
        result.append("\n");
      }
      formData.setResult(result.toString());
      System.out.println(result.toString());

      // read any errors from the attempted command
      System.out.println("Here is the standard error of the command (if any):\n");
      while ((s = stdError.readLine()) != null) {
        System.out.println(s);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return formData;
  }

  @Override
  public RunToolFormData load(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }

  @Override
  public RunToolFormData store(RunToolFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateRunToolPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [tedy] business logic here
    return formData;
  }
}
