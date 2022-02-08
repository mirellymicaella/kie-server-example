package com.drools;

import com.drools.KieServiceConfig.*;

import org.apache.log4j.BasicConfigurator;

public class App 
{
    public static void main( String[] args )
    {
        BasicConfigurator.configure();
        System.out.println("Hello world");

        KieServiceConfig config = new KieServiceConfig();
        KieServiceConfig.initializeKieServerClient();

        String containerId = "firefighter";

        config.createContainer(containerId,"com.drools","firefighter","1.0-SNAPSHOT");

        config.insertPetAndPerson(containerId);
        config.fireAllRules(containerId);
    }
}
