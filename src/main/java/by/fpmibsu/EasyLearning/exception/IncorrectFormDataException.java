package by.fpmibsu.EasyLearning.exception;

public class IncorrectFormDataException extends Exception {
    public IncorrectFormDataException(String p, String value) {
        super(String.format("Empty or incorrect \"%s\" parameter was found: %s", p, value));
    }
}
