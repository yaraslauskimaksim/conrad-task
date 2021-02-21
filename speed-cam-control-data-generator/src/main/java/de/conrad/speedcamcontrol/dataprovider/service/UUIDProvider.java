package de.conrad.speedcamcontrol.dataprovider.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDProvider {

    public UUID provideUuid() {
        return UUID.randomUUID();
    }
}
