package CalorieCalculator.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Photo not provided!")
public class PhotoNotProvidedException extends RuntimeException{
}
