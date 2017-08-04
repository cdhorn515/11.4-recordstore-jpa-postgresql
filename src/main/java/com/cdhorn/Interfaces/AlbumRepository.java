package com.cdhorn.Interfaces;

import com.cdhorn.Models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    Iterable<Album> findAlbumByTitle(String Title);

}
