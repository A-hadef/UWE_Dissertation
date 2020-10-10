package com.uwe.dissertation.ins.policybook.contact;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        return String.format("Convictions:%s Claims:%s", prettyPrint(convictions), prettyPrint(claims));
    }

    private String prettyPrint(List<?> list) {
        Iterator<String> iterator = list.stream().map(Object::toString).collect(Collectors.toList()).iterator();
        StringBuilder stringBuilder = new StringBuilder().append("[");
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
