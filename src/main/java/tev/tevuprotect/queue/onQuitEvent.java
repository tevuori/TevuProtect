package tev.tevuprotect.queue;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tev.tevuprotect.Main;

public class onQuitEvent implements Listener {
    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e){
        Main main = Main.getPlugin(Main.class);
        if(main.premium_queue_pos.containsKey(e.getPlayer())){
            main.premium_queue_pos.remove(e.getPlayer());
            for (Integer o: main.premium_queue_pos.values()) {
                main.premium_queue_pos.replace(e.getPlayer(), o-1);
            }
        }
        if(main.normal_queue_pos.containsKey(e.getPlayer())){
            main.normal_queue_pos.remove(e.getPlayer());
            for (Integer o: main.normal_queue_pos.values()) {
                main.normal_queue_pos.replace(e.getPlayer(), o-1);
            }
        }
    }
}
