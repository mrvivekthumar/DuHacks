package tech.duhacks.duhacks.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "du_usagerecord")
public class UsageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "healthProduct_id", nullable = false)  // Foreign key to healthProduct
    private HealthProduct healthProduct;

    private Boolean isTaken;

    @Column(nullable = false)
    private LocalDateTime takenDate;





}
