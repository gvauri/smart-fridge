package css.ch.smartfridgejavabackend.items.dto;

import css.ch.smartfridgejavabackend.items.types.IconType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateItemRequest(
        @NotNull IconType icon,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Future LocalDate expirationDate,
        @Min(1) int amount
) {}
