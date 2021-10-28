# CustomItem

CustomItem is a open sources module item to create items

## Installation

Clone the repository

## Usage

```java
public class FortuneItem extends CustomItem {


    public FortuneItem() {
        super("FortuneItem", 0, true);
        //FortuneItem is the name of item
        //0 is the ID of item
        //true/false, If your item has interactions
    }

    @Override
    public CustomCraft getCustomCraft() {
        CustomCraft customCraft = new CustomCraft(this.getItem().toItemBuilder().toItemStack());

        //First row
        customCraft.getItemsList().put(1, new ItemStack(Material.DIAMOND));
        customCraft.getItemsList().put(2, new ItemStack(Material.DIAMOND));
        customCraft.getItemsList().put(3, new ItemStack(Material.DIAMOND));

        //Second row
        customCraft.getItemsList().put(5, new ItemStack(Material.STICK));

        //Third row
        customCraft.getItemsList().put(8, new ItemStack(Material.STICK));
        return customCraft;
    }

    @Override
    public RItemUnsafe getItem() {
        ItemBuilder itemBuilder = new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName("§6Pioche de richesse")
                .addEnchant(Enchantment.DURABILITY, 5)
                .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5);

        return new RItemUnsafe(itemBuilder).setInt("id", this.getId());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (event.isCancelled()) {
            return;
        }

        if (!this.isSimilar(player.getItemInHand()))  {
            return;
        }

        if (!this.isActive()) {
            event.setCancelled(true);
            player.sendMessage("§cCet item est désactivé.");
            return;
        }

        Block blockAt = block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ());

        if (blockAt.getType() == Material.AIR) {
            return;
        }

        blockAt.breakNaturally(player.getItemInHand());
    }
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
If you are using the module, please star the project :)
