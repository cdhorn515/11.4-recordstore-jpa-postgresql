package com.cdhorn.Interfaces;

import com.cdhorn.Models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findAllByAlbum(String title);
    Iterable<Song> findAllByBand(String name);
}
