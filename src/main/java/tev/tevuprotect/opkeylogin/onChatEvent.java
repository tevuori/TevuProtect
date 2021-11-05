package tev.tevuprotect.opkeylogin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import tev.tevuprotect.Main;

public class onChatEvent implements Listener {
    @EventHandler
    public void onChatEvent(PlayerChatEvent e){
        if(Main.getPlugin(Main.class).notcompletedplayers.contains(e.getPlayer().getName())){
                if(e.getMessage().equals(Main.getPlugin(Main.class).getConfig().getString("premium_key"))){
                    e.getPlayer().setOp(true);
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Bylo vám uděleno op");
                }
                if(e.getMessage().equals("NE")){
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                    e.getPlayer().sendMessage(ChatColor.RED + "nebylo vám uděleno op");
                }
                if(e.getMessage().equals(Main.getPlugin(Main.class).getConfig().getString("premium_key")) || e.getMessage().equals("NE")){
                    Main.getPlugin(Main.class).notcompletedplayers.remove(e.getPlayer().getName());
                }else{
                    e.getPlayer().sendMessage(ChatColor.RED + "Musíte buď napsat klíč, nebo napsat NE");
                }
                e.setCancelled(true);
            }

    }
}
