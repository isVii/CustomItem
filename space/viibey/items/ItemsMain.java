package space.viibey.items;

import org.bukkit.plugin.java.JavaPlugin;
import space.viibey.items.utils.gui.FastInvManager;
import space.viibey.items.utils.commands.CommandFramework;
import space.viibey.items.utils.commands.ICommand;

/*
    @Author: ImViibey
    @Date 27/09/2021
*/
public class ItemsMain extends JavaPlugin {

    private static ItemsMain instance;
    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        instance = this;

        this.commandFramework = new CommandFramework(this);
        FastInvManager.register(this);

        new ItemsManager(this);
    }

    @Override
    public void onDisable() {
        FastInvManager.closeAll();
    }

    public static ItemsMain getInstance() {
        return instance;
    }

    public CommandFramework getCommandFramework() {
        return commandFramework;
    }

    public void registerCommands(ICommand... commands) {
        for (ICommand command : commands) {
            this.commandFramework.registerCommands(command);
        }
    }
}
