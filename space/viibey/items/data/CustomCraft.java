package space.viibey.items.data;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class CustomCraft {

    private final ItemStack result;
    private final HashMap<Integer, ItemStack> itemsList;

    public CustomCraft(ItemStack result) {
        this.result = result;

        this.itemsList = new LinkedHashMap<>();
    }

    public ItemStack getResult() {
        return result;
    }

    public HashMap<Integer, ItemStack> getItemsList() {
        return itemsList;
    }
}
