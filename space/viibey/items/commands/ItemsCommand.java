package space.viibey.items.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import space.viibey.items.ItemsManager;
import space.viibey.items.data.CustomItem;
import space.viibey.items.utils.InventoryUtils;
import space.viibey.items.utils.Utils;
import space.viibey.items.utils.commands.Command;
import space.viibey.items.utils.commands.CommandArgs;
import space.viibey.items.utils.commands.ICommand;

import javax.rmi.CORBA.Util;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class ItemsCommand extends ICommand {

    private final ItemsManager itemsManager = ItemsManager.getInstance();

    @Override
    @Command(name = {"items", "item", "items.give"}, permissionNode = "items.admin", isConsole = true)
    public void onCommand(CommandArgs args) {
        if (args.length() >= 3) {
            CustomItem customItem = this.itemsManager.getItemByName(args.getArgs(0));

            if (customItem == null) {
                args.getSender().sendMessage("§cAucun item avec ce nom est existant.");
                return;
            }

            Player target = Bukkit.getPlayer(args.getArgs(1));

            if (target == null) {
                args.getSender().sendMessage("§cCe joueur n'est pas connecté ou n'existe pas.");
                return;
            }

            int amount = Utils.isInt(args.getArgs(2)) ? Integer.parseInt(args.getArgs(2)) : 1;

            InventoryUtils.addItem(target, customItem.getItem().toItemBuilder().toItemStack(), amount);
        }
    }
}
