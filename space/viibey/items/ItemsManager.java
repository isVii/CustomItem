package space.viibey.items;

import space.viibey.items.commands.ItemsAdminCommand;
import space.viibey.items.commands.ItemsCommand;
import space.viibey.items.data.CustomItem;
import space.viibey.items.items.FortuneItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class ItemsManager {

    private final List<CustomItem> customItems;
    private static ItemsManager instance;
    private final ItemsMain itemsMain;

    public ItemsManager(ItemsMain itemsMain) {
        instance = this;
        this.itemsMain = itemsMain;

        this.customItems = new ArrayList<>();

        this.getItemsMain().getServer().getPluginManager().registerEvents(new CraftListener(), this.itemsMain);

        this.getItemsMain().registerCommands(
                new ItemsCommand(),
                new ItemsAdminCommand()
        );

        this.registerItems(
                new FortuneItem()
        );
    }

    public CustomItem getItemByName(String name) {
        return this.customItems.stream().filter(customItem -> customItem.getItemName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void registerItems(CustomItem... customItems) {
        this.customItems.addAll(Arrays.asList(customItems));
    }

    public List<CustomItem> getCustomItems() {
        return customItems;
    }

    public static ItemsManager getInstance() {
        return instance;
    }

    public ItemsMain getItemsMain() {
        return itemsMain;
    }
}
