package css.ch.smartfridgejavabackend.items.mapper;

import css.ch.smartfridgejavabackend.items.dto.CreateItemRequest;
import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import css.ch.smartfridgejavabackend.items.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item map(CreateItemRequest request) {
        Item item = new Item();
        item.setIcon(request.icon());
        item.setName(request.name());
        item.setDescription(request.description());
        item.setBuyingDate(request.buyingDate());
        item.setExpirationDate(request.expirationDate());
        item.setAmount(request.amount());

        return item;
    }

    public ItemResponse map(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .icon(item.getIcon())
                .name(item.getName())
                .description(item.getDescription())
                .buyingDate(item.getBuyingDate())
                .expirationDate(item.getExpirationDate())
                .amount(item.getAmount())
                .build();
    }
}
