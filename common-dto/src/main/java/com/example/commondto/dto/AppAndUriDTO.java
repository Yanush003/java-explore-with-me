package com.example.commondto.dto;

public class AppAndUriDTO {
    private String app;
    private String uri;

    public AppAndUriDTO(String app, String uri) {
        this.app = app;
        this.uri = uri;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
