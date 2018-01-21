package bz.dcr.dccore.prompt;

import bz.dcr.dccore.commons.prompt.AbstractPrompt;
import bz.dcr.dccore.commons.prompt.AbstractPromptManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PromptManager extends AbstractPromptManager implements Listener {

    private Plugin plugin;

    private Map<UUID, AbstractPrompt> prompts;


    public PromptManager(Plugin plugin) {
        this.plugin = plugin;
        this.prompts = new ConcurrentHashMap<>();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @Override
    public void registerPrompt(AbstractPrompt prompt) {
        prompts.put(prompt.getPlayerId(), prompt);
    }

    public void unregisterPrompt(UUID playerId) {
        prompts.remove(playerId);
    }


    protected boolean hasPrompt(UUID playerId) {
        return prompts.containsKey(playerId);
    }

    protected AbstractPrompt getPrompt(UUID playerId) {
        return prompts.get(playerId);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        // No active prompt for player
        if (!hasPrompt(player.getUniqueId())) {
            return;
        }

        // Cancel event
        event.setCancelled(true);

        // Get active prompt
        final AbstractPrompt prompt = getPrompt(player.getUniqueId());

        // Handle input
        prompt.handleInput(event.getMessage());

        // Unregister prompt
        unregisterPrompt(player.getUniqueId());
    }

}

