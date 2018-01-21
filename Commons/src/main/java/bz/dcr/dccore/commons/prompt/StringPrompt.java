package bz.dcr.dccore.commons.prompt;

import java.util.UUID;

public class StringPrompt extends AbstractPrompt {

    private String input;
    private int minLength;
    private int maxLength;


    public StringPrompt(UUID playerId, int minLength, int maxLength) {
        super(playerId);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }


    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }


    @Override
    protected void success() {
        if (getAction() == null) return;

        final PromptCallback<StringPrompt> callback = (PromptCallback<StringPrompt>) getAction();
        callback.onSuccess(this);
    }

    @Override
    protected void failure(String rawInput) {
        if (getAction() == null) return;

        final PromptCallback<StringPrompt> callback = (PromptCallback<StringPrompt>) getAction();
        callback.onFailure(this, rawInput);
    }


    @Override
    public boolean handleInput(String input) {
        // Input is too short
        if (minLength > 0 && input.length() < minLength) {
            return false;
        }

        // Input is too long
        if (minLength > 0 && input.length() > maxLength) {
            return false;
        }

        this.input = input;

        // Input is valid
        success();
        return true;
    }

}
