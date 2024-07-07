package app.template.models.validation;

import app.template.exceptions.ValidationException;

public interface Validator<T> {
    /**
     * Valideaza un obiect
     * @param entity - obiectul de validat
     * @throws ValidationException - daca obiectul nu este valid
     */
    void validate(T entity) throws ValidationException;
}