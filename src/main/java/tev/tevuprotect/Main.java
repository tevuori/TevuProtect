package tev.tevuprotect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tev.tevuprotect.opkeylogin.onChatEvent;
import tev.tevuprotect.opkeylogin.onFirstJoin;
import tev.tevuprotect.opkeylogin.onMoveEvent;
import tev.tevuprotect.queue.firstsetup;
import tev.tevuprotect.queue.onJoinEvent;
import tev.tevuprotect.queue.onQuitEvent;

import java.util.*;

public final class Main extends JavaPlugin {
    public List<String> notcompletedplayers = new ArrayList<>();
    public Map<Player, Integer> normal_queue_pos = new HashMap<>();
    public Map<Player, Integer> premium_queue_pos = new HashMap<>();
    @Override
    public void onEnable() {
        loadConfig();
        //premium login
        if(getConfig().getBoolean("premium_key_enabled")){
            getServer().getPluginManager().registerEvents(new onChatEvent(), this);
            getServer().getPluginManager().registerEvents(new onFirstJoin(), this);
            getServer().getPluginManager().registerEvents(new onMoveEvent(), this);
        }
        //queue
        if(Bukkit.getWorld("queue_world") != null){
            firstsetup.run();
        }
        if(getConfig().getBoolean("queue.enabled")){
            getServer().getPluginManager().registerEvents(new onJoinEvent(), this);
            getServer().getPluginManager().registerEvents(new onQuitEvent(), this);
        }
    }

    @Override
    public void onDisable() {

    }
    public void loadConfig(){
        saveConfig();
        reloadConfig();
    }

}
