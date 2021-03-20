package com.modoodesigner.infrastructure.file.local;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalFileServletTest {

    @Test
    void check_environment() {
        List<String> profiles = Arrays.asList("dev", "real");
        List<String> localProfiles = Arrays.asList("local");
        assertFalse(!profiles.stream().filter(localProfiles::contains).findAny().isEmpty());
    }

}