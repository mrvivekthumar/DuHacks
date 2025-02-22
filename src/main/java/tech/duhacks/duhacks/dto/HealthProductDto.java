package tech.duhacks.duhacks.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record HealthProductDto(
        Long id,
        String name,
        Integer quantity,
        LocalDate expiryDate,
        Float amount,
        Long userId,
        Set<LocalTime> times
){
}
