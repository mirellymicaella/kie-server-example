package com.drools;

import java.util.Arrays;
import java.util.List;

import com.drools.model.Person;
import com.drools.model.Pet;

import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.KieServiceResponse.ResponseType;

public class KieServiceConfig {
    private static final String URL = "http://localhost:8090/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    public static void initializeKieServerClient() {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
    }

    public void listContainers() {
        KieContainerResourceList containersList = kieServicesClient.listContainers().getResult();
        List<KieContainerResource> kieContainers = containersList.getContainers();
        System.out.println("Available containers: ");
        for (KieContainerResource container : kieContainers) {
            System.out.println("\t" + container.getContainerId() + " (" + container.getReleaseId() + ")");
        }
    }

    public void createContainer(String containerId, String groupId, String artifactId, String version) {
        ReleaseId releaseId = new ReleaseId(groupId, artifactId, version);
        KieContainerResource resource = new KieContainerResource(releaseId);
        ServiceResponse<KieContainerResource> response = kieServicesClient.createContainer(containerId, resource);

        if (response.getType() == ResponseType.FAILURE) {
            System.out.println("Error creating " + containerId);
            return;
        }

        System.out.println("Container created with success!");
    }

    public void insertPetAndPerson(String containerId) {
        System.out.println("== Inserting objects ==");

        Pet pet = new Pet("Planck", "on the limb", "CAT", 1, 0);
        Person person = new Person(1, "Mirelly");

        RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();

        Command<?> insertPet = commandsFactory.newInsert(pet);
        Command<?> insertPerson = commandsFactory.newInsert(person);

        Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insertPet,insertPerson));

        ServiceResponse<String> executeResponse = rulesClient.executeCommands(containerId, batchCommand);

        if (executeResponse.getType() == ResponseType.SUCCESS) {
            System.out.println("Commands executed with success! Response: ");
            System.out.println(executeResponse.getResult());
        } else {
            System.out.println("Error executing rules. Message: ");
            System.out.println(executeResponse.getMsg());
        }

    }

    public void fireAllRules(String containerId){
        RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        KieCommands commandsFactory = KieServices.Factory.get().getCommands();

        Command<?> fireAllRules = commandsFactory.newFireAllRules();

        Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(fireAllRules));

        ServiceResponse<String> executeResponse = rulesClient.executeCommands(containerId, batchCommand);
        
        if (executeResponse.getType() == ResponseType.SUCCESS) {
            System.out.println("Commands executed with success! Response: ");
            System.out.println(executeResponse.getResult());
        } else {
            System.out.println("Error executing rules. Message: ");
            System.out.println(executeResponse.getMsg());
        }
    }
}
