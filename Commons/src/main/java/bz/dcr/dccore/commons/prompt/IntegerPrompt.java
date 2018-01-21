package bz.dcr.dccore.commons.prompt;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.UUID;

public class IntegerPrompt extends AbstractPrompt {

    private Integer input;
    private boolean allowZero;
    private boolean allowNegative;


    public IntegerPrompt(UUID playerId, boolean allowZero, boolean allowNegative) {
        super(playerId);

        this.allowZero = allowZero;
        this.allowNegative = allowNegative;
    }


    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public boolean doesAllowZero() {
        return allowZero;
    }

    public void setAllowZero(boolean allowZero) {
        this.allowZero = allowZero;
    }

    public boolean doesAllowNegative() {
        return allowNegative;
    }

    public void setAllowNegative(boolean allowNegative) {
        this.allowNegative = allowNegative;
    }


    @Override
    protected void success() {
        if (getAction() == null) return;

        final PromptCallback<IntegerPrompt> callback = (PromptCallback<IntegerPrompt>) getAction();
        callback.onSuccess(this);
    }

    @Override
    protected void failure(String rawInput) {
        if (getAction() == null) return;

        final PromptCallback<IntegerPrompt> callback = (PromptCallback<IntegerPrompt>) getAction();
        callback.onFailure(this, rawInput);
    }


    @Override
    public boolean handleInput(String input) {
        // Input does not represent an integer
        if (!NumberUtils.isDigits(input)) {
            failure(input);
            return false;
        }

        // Parse input
        final Integer integer = Integer.parseInt(input);

        // Input is zero
        if (!doesAllowZero() && integer == 0) {
            failure(input);
            return false;
        }

        // Input is negative
        if (!doesAllowNegative() && integer < 0) {
            failure(input);
            return false;
        }

        this.input = integer;

        // Input is valid
        success();
        return true;
    }

}
