package com.cdhorn.Interfaces;

import com.cdhorn.Models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    Iterable<Song> findAllByAlbum(String title);
    Iterable<Song> findAllByBand(String name);
}
