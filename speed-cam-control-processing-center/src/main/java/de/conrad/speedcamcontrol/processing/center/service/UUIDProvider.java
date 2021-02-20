package de.conrad.speedcamcontrol.processing.center.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDProvider {

    public UUID getUuidFromString(String uuid) {
        return UUID.fromString(uuid);
    }
}
