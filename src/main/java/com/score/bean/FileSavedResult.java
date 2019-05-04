package com.score.bean;

import java.util.Map;


public class FileSavedResult {

    private Map<String, String> saved;

    private Map<String, String> failed;

    public Map<String, String> getSaved() {
        return saved;
    }

    public void setSaved(Map<String, String> saved) {
        this.saved = saved;
    }

    public Map<String, String> getFailed() {
        return failed;
    }

    public void setFailed(Map<String, String> failed) {
        this.failed = failed;
    }
}
