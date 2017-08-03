package com.cdhorn.Interfaces;

import com.cdhorn.Models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {
    Iterable<Band> findBandByName(String name);
}