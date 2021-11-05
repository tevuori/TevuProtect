package tev.tevuprotect.opkeylogin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tev.tevuprotect.Main;

public class onMoveEvent implements Listener {
    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e){
        if(Main.getPlugin(Main.class).notcompletedplayers.contains(e.getPlayer().getName())){
             e.setCancelled(true);
        }
    }
}
