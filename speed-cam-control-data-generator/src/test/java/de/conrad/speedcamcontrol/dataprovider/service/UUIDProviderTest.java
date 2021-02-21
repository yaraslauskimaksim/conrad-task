package de.conrad.speedcamcontrol.dataprovider.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UUIDProviderTest {

    @Test
    void shouldProvideUuid() {
        final var provider = new UUIDProvider();

        final var uuid1 = provider.provideUuid();
        final var uuid2 = provider.provideUuid();

        assertNotEquals(uuid1, uuid2);
    }
}