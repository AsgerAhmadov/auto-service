package az.hamburg.autoservice.validation.vehicle;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotBlank(message = "VehicleModel boş ola bilməz.")
@Size(max = 255, message = "VehicleModel maksimum 255 simvol ola bilər.")
public @interface VehiclePlateNumber {
    String message() default "Invalid plate number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
