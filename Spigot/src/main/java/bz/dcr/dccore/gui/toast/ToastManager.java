package bz.dcr.dccore.gui.toast;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.constants.ConfigKey;
import bz.dcr.dccore.util.AdvancementMessage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class ToastManager {

    private static final String TOAST_DESCRIPTION = "Temporary Advancement";

    private DcCorePlugin plugin;


    public ToastManager(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    public void sendToast(AdvancementMessage toast, Player target) {
        // Format title
        toast.setTitle(formatToastMessage(toast.getTitle()));

        // Send toast
        toast.showTo(target);
    }

    public void sendToast(AdvancementMessage toast, Collection<? extends Player> targets) {
        // Format title
        toast.setTitle(formatToastMessage(toast.getTitle()));

        // Send toast
        toast.showTo(targets);
    }

    public void sendToast(Material icon, String text, Player target) {
        // Build toast
        AdvancementMessage toast = new AdvancementMessage(
                getRandomToastId(),
                text,
                TOAST_DESCRIPTION,
                convertMaterialToIconName(icon),
                plugin
        );

        // Send toast
        sendToast(toast, target);
    }

    public void sendToast(Material icon, String text, Collection<? extends Player> targets) {
        // Build toast
        AdvancementMessage toast = new AdvancementMessage(
                getRandomToastId(),
                text,
                TOAST_DESCRIPTION,
                convertMaterialToIconName(icon),
                plugin
        );

        // Send toast
        sendToast(toast, targets);
    }


    private String formatToastMessage(String message) {
        // Build toast title text
        String text = message;

        // Get max toast title length
        final int maxToastLength = plugin.getConfig().getInt(ConfigKey.TOAST_MAX_LENGTH);

        if (text.length() > maxToastLength) {
            final String dots = ChatColor.translateAlternateColorCodes(
                    '&', plugin.getConfig().getString(ConfigKey.TOAST_DOTS));

            // Truncate toast title
            text = text.subSequence(0, maxToastLength - dots.length()).toString();

            // Append dots
            text += dots;
        }

        return text;
    }


    private static String getRandomToastId() {
        return UUID.randomUUID().toString();
    }

    private static String convertMaterialToIconName(Material material) {
        return "minecraft:" + material.toString().toLowerCase();
    }

}
