package css.ch.smartfridgejavabackend.items.service;

import css.ch.smartfridgejavabackend.exceptions.InvalidItemAction;
import css.ch.smartfridgejavabackend.exceptions.ItemNotFoundException;
import css.ch.smartfridgejavabackend.items.dto.CreateItemRequest;
import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import css.ch.smartfridgejavabackend.items.dto.UpdateItemRequest;
import css.ch.smartfridgejavabackend.items.mapper.ItemMapper;
import css.ch.smartfridgejavabackend.items.model.Item;
import css.ch.smartfridgejavabackend.items.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Override
    public List<ItemResponse> getItems(String userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public ItemResponse createItem(String userId, CreateItemRequest request) {
        Item item = mapper.map(request);
        item.setUserId(userId);

        return mapper.map(repository.save(item));
    }

    @Override
    public ItemResponse updateItem(String userId, String itemId, UpdateItemRequest request) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item mit id " + itemId + " nicht gefunden"));

        if (!item.getUserId().equals(userId)) {
            throw new InvalidItemAction("Item gehört nicht dem User");
        }

        item.setIcon(request.icon());
        item.setName(request.name());
        item.setDescription(request.description());
        item.setExpirationDate(request.expirationDate());
        item.setAmount(request.amount());

        return mapper.map(repository.save(item));
    }

    @Override
    public void deleteItem(String userId, String itemId) {
        Item item = repository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item mit id " + itemId + " nicht gefunden"));

        if (!item.getUserId().equals(userId)) {
            throw new InvalidItemAction("Item gehört nicht dem User");
        }

        repository.deleteById(itemId);
    }
}
