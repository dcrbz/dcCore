package bz.dcr.dccore.commons.prompt;

import java.util.UUID;

public abstract class AbstractPromptManager {

    public abstract void registerPrompt(AbstractPrompt prompt);

    public abstract void unregisterPrompt(UUID playerId);

    protected abstract boolean hasPrompt(UUID playerId);

    protected abstract AbstractPrompt getPrompt(UUID playerId);

}