package az.hamburg.autoservice.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotBlank(message = "UserUsername boş ola bilməz.")
@Size(max = 50, message = "UserUsername maksimum 50 simvol ola bilər.")
public @interface UserUsername {
    String message() default "Invalid username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
