package tev.tevuprotect.queue;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tev.tevuprotect.Main;

public class firstsetup {
    public static void run(){
        WorldCreator wc = new WorldCreator("queue_world");

        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");

        wc.createWorld();
    }
}
