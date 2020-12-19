package api;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {
	
	static final String ALLOWEDIPSLIST = "allowedIpsList=";
	static final String DATABASEADDRESS = "dataBaseAddress=";
	static final String DBUSERNAME = "dataBaseUser=";
	static final String DBPASSWORD = "dataBasePassword=";
	
	private static String[] allowedIPsList;
	private static String databaseAddress;
	private static String databaseUser;
	private static String databasePassword;

    public static void main(String[] args) throws IOException {
  	
    	Configurer config = new Configurer();
    	ArrayList<String> configs = new ArrayList<String>();
    	ArrayList<String> configurationArgs = new ArrayList<String>();
    	configurationArgs.add(ALLOWEDIPSLIST);
    	configurationArgs.add(DATABASEADDRESS);
    	configurationArgs.add(DBUSERNAME);
    	configurationArgs.add(DBPASSWORD);
    	configs = config.readConfigFile(configurationArgs);
    	allowedIPsList = configs.get(0).split(":");
    	databaseAddress = configs.get(1);
    	databaseUser = configs.get(2);
    	databasePassword = configs.get(3);
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @EnableWebMvc
    public class WebConfig implements WebMvcConfigurer {

    	@Override
    	public void addCorsMappings(CorsRegistry registry) {
    		for(int i=0;i>allowedIPsList.length;i++) {
    			registry.addMapping("/**").allowedOrigins(allowedIPsList[i]);
    		}
    	}
    }
}