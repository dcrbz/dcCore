package bz.dcr.dccore.commons.prompt;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.UUID;

public class DoublePrompt extends AbstractPrompt {

    private Double input;
    private boolean allowZero;
    private boolean allowNegative;
    private boolean germanNotation;


    public DoublePrompt(UUID playerId, boolean allowZero, boolean allowNegative, boolean germanNotation) {
        super(playerId);

        this.allowZero = allowZero;
        this.allowNegative = allowNegative;
        this.germanNotation = germanNotation;
    }


    public Double getInput() {
        return input;
    }

    public void setInput(Double input) {
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

    public boolean isGermanNotation() {
        return germanNotation;
    }

    public void setGermanNotation(boolean germanNotation) {
        this.germanNotation = germanNotation;
    }


    @Override
    protected void success() {
        if (getAction() == null) return;

        final PromptCallback<DoublePrompt> callback = (PromptCallback<DoublePrompt>) getAction();
        callback.onSuccess(this);
    }

    @Override
    protected void failure(String rawInput) {
        if (getAction() == null) return;

        final PromptCallback<DoublePrompt> callback = (PromptCallback<DoublePrompt>) getAction();
        callback.onFailure(this, rawInput);
    }


    @Override
    public boolean handleInput(String input) {
        // Format input string
        if (germanNotation) {
            input = input.replace(".", "").replace(",", ".");
        } else {
            input = input.replace(",", "");
        }

        // Input does not represent an integer
        if (!NumberUtils.isParsable(input)) {
            failure(input);
            return false;
        }

        // Parse input
        final Double number = Double.parseDouble(input);

        // Input is zero
        if (!doesAllowZero() && number == 0.0D) {
            failure(input);
            return false;
        }

        // Input is negative
        if (!doesAllowNegative() && number < 0.0D) {
            failure(input);
            return false;
        }

        this.input = number;

        // Input is valid
        success();
        return true;
    }

}
