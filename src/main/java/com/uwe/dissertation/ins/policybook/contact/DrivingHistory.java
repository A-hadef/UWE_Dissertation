package com.uwe.dissertation.ins.policybook.contact;

import java.util.List;

public class DrivingHistory {
    
    private List<Claim> claims;
    private List<Conviction> convictions;

    public List<Conviction> getConvictions() {
        return convictions;
    }

    public void setConvictions(List<Conviction> convictions) {
        this.convictions = convictions;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
    
    public String toString() {
        return String.format("Convictions:%s Claims:%s", convictions, claims);
    }
}
