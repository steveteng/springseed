package au.com.nbnco.springseed.web.security;

import au.com.nbnco.springseed.service.EnvTellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HomeController {

    @Autowired
    private EnvTellerService envTellerService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Welcome to Spring Seed Hotel!   "+this.envTellerService.getEnvName();
    }

}
