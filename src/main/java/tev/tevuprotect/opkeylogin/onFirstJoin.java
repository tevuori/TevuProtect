package tev.tevuprotect.opkeylogin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tev.tevuprotect.Main;

public class onFirstJoin implements Listener {
    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e){
        if(!e.getPlayer().hasPlayedBefore()){
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.GREEN + "Pokud znáte prémiový kód pro moderátorská práva, napište ho, pokud ne, napište NE");
            Main.getPlugin(Main.class).notcompletedplayers.add(p.getName());
        }
    }
}
