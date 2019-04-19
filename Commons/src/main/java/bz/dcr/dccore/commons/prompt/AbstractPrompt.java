package bz.dcr.dccore.commons.prompt;

import java.util.UUID;

public abstract class AbstractPrompt {

    private UUID playerId;
    private PromptCallback<? extends AbstractPrompt> action;


    public AbstractPrompt(UUID playerId) {
        this.playerId = playerId;
    }


    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    protected PromptCallback<? extends AbstractPrompt> getAction() {
        return action;
    }

    public void setAction(PromptCallback<? extends AbstractPrompt> action) {
        this.action = action;
    }


    protected abstract void success();

    protected abstract void failure(String rawInput);


    public abstract boolean handleInput(String input);


    public static abstract class PromptCallback<T extends AbstractPrompt> {

        public abstract void onSuccess(T prompt);

        public abstract void onFailure(T prompt, String rawInput);

    }

}
