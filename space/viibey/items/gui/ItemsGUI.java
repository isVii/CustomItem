package space.viibey.items.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import space.viibey.items.ItemsManager;
import space.viibey.items.data.CustomItem;
import space.viibey.items.utils.InventoryUtils;
import space.viibey.items.utils.gui.FastInv;

import java.util.Comparator;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class ItemsGUI extends FastInv {

    private final ItemsManager itemsManager = ItemsManager.getInstance();

    public ItemsGUI(Player player) {
        super(54, "Items admin");

        this.itemsManager.getCustomItems().stream().sorted(Comparator.comparingInt(CustomItem::getId)).forEach(customItem -> {
            ItemStack itemStack = customItem.getItem().toItemBuilder().toItemStack();
            this.addItem(itemStack, event -> {
                if (event.isLeftClick()) {
                    InventoryUtils.addItem(player, itemStack);
                } else if (event.isRightClick()) {
                    if (!customItem.isDisactivable()) {
                        return;
                    }

                    customItem.setActive(!customItem.isActive());
                }

                player.closeInventory();
            });
        });
    }
}
