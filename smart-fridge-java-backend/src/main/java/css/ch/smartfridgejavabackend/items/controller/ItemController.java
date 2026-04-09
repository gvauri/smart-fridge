package css.ch.smartfridgejavabackend.items.controller;

import css.ch.smartfridgejavabackend.items.dto.CreateItemRequest;
import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import css.ch.smartfridgejavabackend.items.dto.UpdateItemRequest;
import css.ch.smartfridgejavabackend.items.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(service.getItems(userId));
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody CreateItemRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createItem(userId, request));
    }

    @PatchMapping("{itemId}")
    public ResponseEntity<ItemResponse> updateItem(
            @AuthenticationPrincipal String userId,
            @PathVariable String itemId,
            @Valid @RequestBody UpdateItemRequest request
    ) {
        return ResponseEntity.ok(service.updateItem(userId, itemId, request));
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteItem(
            @AuthenticationPrincipal String userId,
            @PathVariable String itemId
    ) {
        service.deleteItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }
}
