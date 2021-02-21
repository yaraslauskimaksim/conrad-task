package de.conrad.speedcamcontrol.processing.center.persistence;

import java.util.List;

import de.conrad.speedcamcontrol.processing.center.persistence.model.RoadIncidentEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoadIncidentRepository extends CrudRepository<RoadIncidentEntity, Long> {

    List<RoadIncidentEntity> findAllByObjectNumberOrderByIncidentTsDesc(String objectNumber);

    List<RoadIncidentEntity> findAllByViewedFalseOrderByIncidentTsDesc();
}
