package az.hamburg.autoservice.validation.appointment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotBlank(message = "AppointmentServiceDescription boş ola bilməz.")
@Size(max = 255, message = "AppointmentServiceDescription maksimum 255 simvol ola bilər.")
public @interface AppointmentServiceDescription {
    String message() default "Invalid service description";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
