package bz.dcr.dccore.listener;

import org.bukkit.event.Listener;

public interface SelfRegisteringListener extends Listener {

    void registerListener();

}
