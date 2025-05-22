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
@NotBlank(message = "VehicleBrand boş ola bilməz.")
@Size(max = 255, message = "VehicleBrand maksimum 255 simvol ola bilər.")
public @interface VehicleBrand {
    String message() default "Invalid brand";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
