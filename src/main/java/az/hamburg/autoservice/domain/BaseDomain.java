package az.hamburg.autoservice.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@MappedSuperclass
public abstract class BaseDomain {

    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
        this.modified = LocalDateTime.now();
    }
}
