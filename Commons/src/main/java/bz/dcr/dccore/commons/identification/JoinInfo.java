package bz.dcr.dccore.commons.identification;

import java.util.Date;

public class JoinInfo {

    private Date time;
    private String ipAddress;


    public JoinInfo() {
    }

    public JoinInfo(String ipAddress) {
        this.time = new Date();
        this.ipAddress = ipAddress;
    }

    public JoinInfo(Date time, String ipAddress) {
        this.time = time;
        this.ipAddress = ipAddress;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
