
package com.airtelx.app.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResultResponseModel {

    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
