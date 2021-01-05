package CalorieCalculator.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Ingredient not found. You can go to add it.")
public class IngredientNotFoundException extends RuntimeException{
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
