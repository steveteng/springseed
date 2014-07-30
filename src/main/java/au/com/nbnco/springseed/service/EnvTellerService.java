package au.com.nbnco.springseed.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
    public class EnvTellerService {

    @Value("${profiles:default}")
    private String profiles;

    public String getEnvName() {
        return "Env:" + this.profiles;
    }

}
