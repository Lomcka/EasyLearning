package by.fpmibsu.EasyLearning.service.validator;

import javax.servlet.http.HttpServletRequest;

import by.fpmibsu.EasyLearning.bean.Bean;
import by.fpmibsu.EasyLearning.exception.IncorrectFormDataException;

public interface Validator<T extends Bean> {
    T validate(HttpServletRequest request) throws IncorrectFormDataException;
}
