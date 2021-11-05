package tev.tevuprotect.opkeylogin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import tev.tevuprotect.Main;

public class onChatEvent implements Listener {
    @EventHandler
    public void onChatEvent(PlayerChatEvent e){
        if(Main.getPlugin(Main.class).notcompletedplayers.contains(e.getPlayer().getName())){
            boolean completed = false;
            while(!completed){
                if(e.getMessage() == Main.getPlugin(Main.class).getConfig().getString("premium_key")){
                    e.getPlayer().setOp(true);
                    completed = true;
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                }
                if(e.getMessage() == "NE"){
                    completed = true;
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                }
                if(e.getMessage() == Main.getPlugin(Main.class).getConfig().getString("premium_key") || e.getMessage() == "NE"){
                    completed = true;
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                }
            }
        }
    }
}
