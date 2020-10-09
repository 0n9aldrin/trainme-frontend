package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class ApprovalSparing {
    @SerializedName("pp_id")
    private int ppId;
    @SerializedName("status")
    private String status;
    @SerializedName("reason")
    private String reason;

    public ApprovalSparing() {
    }

    public ApprovalSparing(int ppId, String status, String reason) {
        this.ppId = ppId;
        this.status = status;
        this.reason = reason;
    }
}
