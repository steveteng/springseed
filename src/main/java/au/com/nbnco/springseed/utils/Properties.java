package au.com.nbnco.springseed.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(name = "base")
public class Properties {

    private String vara;
    private String varb;
    private String varc;
    private String vard;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    private String fileLocation;

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getVarb() {
        return varb;
    }

    public void setVarb(String varb) {
        this.varb = varb;
    }

    public String getVarc() {
        return varc;
    }

    public void setVarc(String varc) {
        this.varc = varc;
    }

    public String getVard() {
        return vard;
    }

    public void setVard(String vard) {
        this.vard = vard;
    }
}