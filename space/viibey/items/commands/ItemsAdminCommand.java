package space.viibey.items.commands;

import org.bukkit.entity.Player;
import space.viibey.items.gui.ItemsGUI;
import space.viibey.items.utils.commands.Command;
import space.viibey.items.utils.commands.CommandArgs;
import space.viibey.items.utils.commands.ICommand;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class ItemsAdminCommand extends ICommand {

    @Override
    @Command(name = {"items.admin", "item.admin"}, permissionNode = "items.admin")
    public void onCommand(CommandArgs args) {

        final Player player = args.getPlayer();
        new ItemsGUI(player).open(player);
    }
}
