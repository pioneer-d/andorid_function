package com.android.function.api.post;

import com.android.function.api.get.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    @Expose
    public Data data = new Data();

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
