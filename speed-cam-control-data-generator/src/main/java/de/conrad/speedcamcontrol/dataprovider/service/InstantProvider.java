package de.conrad.speedcamcontrol.dataprovider.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class InstantProvider {

    public Instant provideNowInstant() {
        return Instant.now();
    }
}
