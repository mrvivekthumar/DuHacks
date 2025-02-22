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
    @JoinColumn(name = "healthPorduct_id", nullable = false)  // Foreign key to healthPorduct
    private HealthProduct healthPorduct;

    private Boolean isTaken;

    @Column(nullable = false)
    private LocalDateTime takenDate;





}
