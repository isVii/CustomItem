package space.viibey.items.data;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import space.viibey.items.ItemsMain;
import space.viibey.items.ItemsManager;
import space.viibey.items.utils.InventoryUtils;
import space.viibey.items.utils.nbt.RItemUnsafe;
import space.viibey.items.utils.nbt.RNBTItem;

import java.util.Optional;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public abstract class CustomItem implements Listener {

    private final String itemName;
    private final int id;
    private final boolean listener;
    private final ItemsMain itemsMain = ItemsMain.getInstance();
    private final ItemsManager itemsManager = ItemsManager.getInstance();
    private boolean active;
    private boolean disactivable;

    public CustomItem(String itemName, int id, boolean listener) {
        this.itemName = itemName;
        this.id = id;
        this.listener = listener;
        this.active = true;
        this.disactivable = true;

        if (this.listener) {
            this.itemsMain.getServer().getPluginManager().registerEvents(this, this.itemsMain);
        }
    }

    public boolean isSimilar(ItemStack itemStack) {
        if (InventoryUtils.isNullItem(itemStack)) {
            return false;
        }

        final Optional<CustomItem> optional = this.itemsManager.getCustomItems().stream()
                .filter(customItem -> customItem.getItem().toItemBuilder().toItemStack().getType() == itemStack.getType()).findFirst();

        if (optional.isPresent()) {
            return new RNBTItem(itemStack).getInt("id") == this.id;
        }
        return false;
    }

    public abstract CustomCraft getCustomCraft();

    public abstract RItemUnsafe getItem();

    public String getItemName() {
        return itemName;
    }

    public int getId() {
        return id;
    }

    public boolean isListener() {
        return listener;
    }

    public ItemsMain getItemsMain() {
        return itemsMain;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDisactivable() {
        return disactivable;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDisactivable(boolean disactivable) {
        this.disactivable = disactivable;
    }
}
