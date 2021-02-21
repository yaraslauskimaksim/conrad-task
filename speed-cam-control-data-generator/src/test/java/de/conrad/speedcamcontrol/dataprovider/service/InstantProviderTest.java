package de.conrad.speedcamcontrol.dataprovider.service;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstantProviderTest {

    @Test
    void shouldProvideNowInstant() {
        final var provider = new InstantProvider();

        final var now = Instant.now();
        final var providedInstant = provider.provideNowInstant();

        assertThat(providedInstant.toEpochMilli())
            .isGreaterThanOrEqualTo(now.toEpochMilli());
    }
}