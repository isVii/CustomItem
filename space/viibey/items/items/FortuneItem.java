package space.viibey.items.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import space.viibey.items.data.CustomCraft;
import space.viibey.items.data.CustomItem;
import space.viibey.items.utils.nbt.ItemBuilder;
import space.viibey.items.utils.nbt.RItemUnsafe;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class FortuneItem extends CustomItem {


    public FortuneItem() {
        super("FortuneItem", 0, true);
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
