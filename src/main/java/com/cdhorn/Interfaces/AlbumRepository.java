package com.cdhorn.Interfaces;

import com.cdhorn.Models.Album;
import com.cdhorn.Models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    Album findAlbumByTitle(String title);
    Iterable<Album> findAllByBand(Band band);

}
