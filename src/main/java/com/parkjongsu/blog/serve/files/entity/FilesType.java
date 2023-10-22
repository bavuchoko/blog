package com.parkjongsu.blog.serve.files.entity;

public enum FilesType {

     IMG("img"), FILE("file");

    final private String type;
    FilesType(String type) {
        this.type =type;
    }

    String getType() {
        return this.type;
    }

}
