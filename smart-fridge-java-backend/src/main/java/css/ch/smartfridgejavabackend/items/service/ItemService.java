package css.ch.smartfridgejavabackend.items.service;

import css.ch.smartfridgejavabackend.items.dto.CreateItemRequest;
import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import css.ch.smartfridgejavabackend.items.dto.UpdateItemRequest;

import java.util.List;

public interface ItemService {

    List<ItemResponse> getItems(String userId);

    ItemResponse createItem(String userId, CreateItemRequest request);

    ItemResponse updateItem(String userId, String itemId, UpdateItemRequest request);

    void deleteItem(String userId, String itemId);
}
