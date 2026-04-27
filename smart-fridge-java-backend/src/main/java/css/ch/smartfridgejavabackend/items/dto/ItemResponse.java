package css.ch.smartfridgejavabackend.items.dto;

import css.ch.smartfridgejavabackend.items.types.IconType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ItemResponse(
   String id,
   String userId,
   IconType icon,
   String name,
   String description,
   LocalDate buyingDate,
   LocalDate expirationDate,
   int amount
) {}
