package com.example.modulith;

import com.example.modulith.delivery.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class MonolithTest {

    @Test
    void printModuleInfo() {
        ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);
        modules.forEach(System.out::println);
    }

    @Test
    void verifyModuleDependencies() {
        ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);
        modules.verify();
    }

    @Test
    void makeDocumentation() {
        ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);
        Documenter documenter = new Documenter(modules);
        documenter
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation();
    }

}
