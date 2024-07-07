package app.template.models.validation;

import app.template.exceptions.ValidationException;
import app.template.models.Consultatie;

public class ConsultatieValidator implements Validator<Consultatie>{
    @Override
    public void validate(Consultatie entity) throws ValidationException {
        if(entity.getNumePacient() == null || entity.getNumePacient().length() == 0)
            throw new ValidationException("Nume vid!");
        if(entity.getCNPPacient() == null || entity.getCNPPacient().length() == 0)
            throw new ValidationException("CNP vid!");
        if(entity.getData() == null)
            throw new ValidationException("Data vida!");
        if(entity.getOra() == null)
            throw new ValidationException("Ora vida!");
    }
}
