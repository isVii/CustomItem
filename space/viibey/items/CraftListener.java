package space.viibey.items;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import space.viibey.items.data.CustomItem;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class CraftListener implements Listener {

    private final ItemsManager itemsManager = ItemsManager.getInstance();

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        final CraftingInventory craftInventory = event.getInventory();

        final List<CustomItem> collect = itemsManager.getCustomItems().stream().filter(customItem -> customItem.getCustomCraft() != null).collect(Collectors.toList());

        if (collect.isEmpty()) {
            return;
        }

        collect.forEach(customItem -> {

            if (!customItem.isActive()) {
                return;
            }

            AtomicInteger integer = new AtomicInteger();
            final HashMap<Integer, ItemStack> itemsList = customItem.getCustomCraft().getItemsList();

            itemsList.forEach((key, value) -> {
                final ItemStack item = craftInventory.getItem(key);
                if (item != null && item.equals(value)) {
                    integer.getAndIncrement();
                }
            });

            Bukkit.broadcastMessage("" + integer.get());

            if (integer.get() != itemsList.size()) {
                return;
            }

            craftInventory.setItem(-1, customItem.getItem().toItemBuilder().toItemStack());
        });


    }
}
