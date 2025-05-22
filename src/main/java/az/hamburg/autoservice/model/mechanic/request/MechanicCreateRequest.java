package az.hamburg.autoservice.model.mechanic.request;

import az.hamburg.autoservice.validation.mechanic.MechanicName;
import az.hamburg.autoservice.validation.mechanic.MechanicSpecialty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicCreateRequest {

    @MechanicName
    private String name;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email formatı düzgün deyil"
    )
    private String email;

    @MechanicSpecialty
    private String specialty;
}
