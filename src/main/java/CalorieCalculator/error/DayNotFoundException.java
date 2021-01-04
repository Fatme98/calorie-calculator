package CalorieCalculator.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Day not found")
public class DayNotFoundException  extends RuntimeException{
    public DayNotFoundException(String message) {
        super(message);
    }
}
