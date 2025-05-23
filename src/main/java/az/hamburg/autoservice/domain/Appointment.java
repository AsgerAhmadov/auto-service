package az.hamburg.autoservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;
    private LocalDateTime date;
    private String serviceDescription;
//    private Boolean statusChange;
    private boolean  isProcessed;//process tamamlanibsa databazadan silmek yeni, usta isini bitiribse
    //gorusu bitirmek

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}
