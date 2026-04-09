package css.ch.smartfridgejavabackend.user.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String id,
        String name,
        String email
) {}
