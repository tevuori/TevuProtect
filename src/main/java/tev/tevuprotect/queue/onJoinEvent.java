package tev.tevuprotect.queue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import tev.tevuprotect.Main;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class onJoinEvent implements Listener {
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        e.getPlayer().teleport(new Location(Bukkit.getWorld("queue_world"),0,0,0));
        Main main = Main.getPlugin(Main.class);
        Player p = e.getPlayer();
        //premium queue
        if(main.getConfig().getBoolean("queue.enabled"))
        if(p.hasPermission(main.getConfig().getString("queue.permission"))){
            main.premium_queue_pos.put(p.getName(), getPremiumHighestPosition()+1);
                boolean completed = false;
                while(completed == false) {
                    if(main.premium_queue_pos.get(p.getName()) != 0){
                        p.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("queue.world_to_teleport")), 0,0,0));
                        p.sendMessage(ChatColor.GREEN + "You are out from queue");
                        main.premium_queue_pos.remove(p.getName());
                        for (Integer o: main.premium_queue_pos.values()) {
                            main.premium_queue_pos.replace(p.getName(), o-1);
                        }
                        completed = true;
                    }else{
                        p.sendMessage(ChatColor.RED + "Your queue position is " + main.premium_queue_pos.get(p.getName()));
                    }
                }
        }
        //normal queue
        if(!p.hasPermission(main.getConfig().getString("queue.permission"))){
            main.normal_queue_pos.put(p.getName(), getNormalHightestPosition()+1);
            if(main.normal_queue_pos.get(p.getName()) != null)
            new BukkitRunnable() {
                @Override
                public void run() {
                    boolean completed = false;
                    if(!completed) {
                        if(main.normal_queue_pos.get(p.getName()) != 0){
                            World world = Bukkit.getWorld("world");
                            if(world.getPlayerCount() == main.getConfig().getInt("queue.max_players")){
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("queue.world_to_teleport")), 0,0,0));
                                    }
                                }.runTask(Main.getPlugin(Main.class));
                                p.sendMessage(ChatColor.GREEN + "You are out from queue");
                                main.normal_queue_pos.remove(p.getName());
                                for (Integer o: main.normal_queue_pos.values()) {
                                    main.normal_queue_pos.replace(p.getName(), o-1);
                                }
                                completed = true;
                            }else{
                                p.sendMessage(ChatColor.RED + "Your queue position is " + main.normal_queue_pos.get(p.getName()));
                            }
                        }else{
                            p.sendMessage(ChatColor.RED + "Your queue position is " + main.normal_queue_pos.get(p.getName()));
                        }
                    }
                }
            }.runTaskTimerAsynchronously(main,50,50);
            if(p.hasPermission("*")){
                p.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("queue.world_to_teleport")),0,0,0));
            }
        }
    }
    public int getPremiumHighestPosition(){
        Main main = Main.getPlugin(Main.class);
        int i = 0;
        for (Integer o: main.premium_queue_pos.values()) {
            if(i == 0){
                i = o;
            }else if(o>i){
                i=o;
            }
        }
        return i;
    }
    public int getNormalHightestPosition(){
        Main main = Main.getPlugin(Main.class);
        int i = 0;
        for (Integer o: main.normal_queue_pos.values()) {
            if(i == 0){
                i = o;
            }else if(o>i){
                i=o;
            }
        }
        return i;
    }
}
