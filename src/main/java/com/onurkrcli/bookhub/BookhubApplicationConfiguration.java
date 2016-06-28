package com.onurkrcli.bookhub;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.onurkrcli.bookhub")
@EnableMongoRepositories(basePackages = "com.onurkrcli.bookhub.repository")
public class BookhubApplicationConfiguration extends WebMvcConfigurerAdapter {


    //~ Connection Params ==============================================================================================

    @Value("${db.url}")
    private String dbHostUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.dbname}")
    private String dbName;

    @Value("${db.port}")
    private Integer dbPort;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
    
    
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	if(!registry.hasMappingForPattern("/assets/*")) {
    		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    		registry.addResourceHandler("/assets/**/*.html").addResourceLocations("/assets/");
    	} 
    	
    	registry.addResourceHandler("/*.html").addResourceLocations("/");
	}



	//~ MongoDB Config =================================================================================================
    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(dbUsername,
                dbName, dbPassword.toCharArray());
        ServerAddress address = new ServerAddress(dbHostUrl, dbPort);
        List<MongoCredential> credentials = Arrays.asList(mongoCredential);

        MongoClient mongoClient = new MongoClient(address, credentials);
        return new SimpleMongoDbFactory(mongoClient, dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public static PropertyPlaceholderConfigurer propConfig() {
        PropertyPlaceholderConfigurer ppc =  new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("config.properties"));
        return ppc;
    }
}
