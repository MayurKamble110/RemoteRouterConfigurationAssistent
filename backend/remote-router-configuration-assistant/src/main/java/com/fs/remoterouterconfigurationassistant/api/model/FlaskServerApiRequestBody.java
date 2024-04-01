package com.fs.remoterouterconfigurationassistant.api.model;

public class FlaskServerApiRequestBody {
    private String text;
    private String prompt;

    public FlaskServerApiRequestBody(String text, String prompt) {
        this.text = text;
        this.prompt = prompt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
