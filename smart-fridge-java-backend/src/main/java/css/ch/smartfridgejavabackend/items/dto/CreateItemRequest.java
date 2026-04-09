package css.ch.smartfridgejavabackend.items.dto;

import css.ch.smartfridgejavabackend.items.types.IconType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CreateItemRequest(
        @NotNull IconType icon,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @PastOrPresent LocalDate buyingDate,
        @NotNull @Future LocalDate expirationDate,
        @Min(1) int amount
) {}
