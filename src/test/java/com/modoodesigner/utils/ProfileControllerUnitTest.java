package com.modoodesigner.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerUnitTest {

    @Test
    public void getProfile_withAll_thenReturnDevProfile() {
        String expectedProfile = "dev";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("dev-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertEquals(profile,expectedProfile);
    }

    @Test
    public void getProfile_withoutDev_thenReturnFirstProfile() {
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("dev-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertEquals(profile,expectedProfile);
    }

    @Test
    public void getProfile_withoutProfile_thenReturnDefaultProfile() {
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertEquals(profile,expectedProfile);
    }

}