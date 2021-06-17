package starter.math;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.javafaker.Faker;

import starter.steps.PetStoreSteps;
import starter.steps.Pet;

@RunWith(SerenityRunner.class)
@Narrative(text={"Test the Pet Store API."})
public class WhenAddingPets {

    @Steps
    PetStoreSteps petStoreSteps;

    @Test
    public void addingSums() {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        Pet newPet = new Pet("available", name);
        petStoreSteps.addPet(newPet);
        petStoreSteps.checkPetAvailability();
    }
}
