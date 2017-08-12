package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.AlbumRepository;
import com.cdhorn.Interfaces.BandRepository;
import com.cdhorn.Interfaces.SongRepository;
import com.cdhorn.Models.Album;
import com.cdhorn.Models.Band;
import com.cdhorn.Models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SongController {

    @Autowired
    SongRepository songRepo;

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @RequestMapping("/addSong")
    public String addSong(Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);
        return "addSong";
    }

    @RequestMapping(value = "/addSong", method = RequestMethod.POST)
    public String addSong(@RequestParam("title") String title,
                          @RequestParam("band_id") String band_id,
                          @RequestParam("album_id") String album_id) {
        Song song = new Song();
        song.setTitle(title);


        try {
            long bandId = Long.parseLong(band_id);
            Band band = bandRepo.findOne(bandId);
            long albumId = Long.parseLong(album_id);
            Album album = albumRepo.findOne(albumId);
            song.setAlbum(album);
            song.setBand(band);

        } catch (Exception ex) {

        }
        songRepo.save(song);

        return "redirect:/";

    }

    @RequestMapping(value = "/addSongAndBand/", method = RequestMethod.POST)
    public String addSongAndBand(@RequestParam("title") String title,
                          @RequestParam("band_id") String band_id,
                          Model model) {
        Song song = new Song();
        song.setTitle(title);

        try {
            long bandId = Long.parseLong(band_id);
            Band band = bandRepo.findOne(bandId);
            song.setBand(band);
            Iterable<Album> albums = albumRepo.findAllByBand(band);
            songRepo.save(song);
            model.addAttribute("song", song);
            model.addAttribute("albums", albums);

        } catch (Exception ex) {

        }
        return "addSongAlbum";
    }

    @RequestMapping(value = "/addSongAndAlbum", method = RequestMethod.POST)
    public String addSongAndAlbum(
//            @RequestParam("title") String title,
                                  @RequestParam("song_id") String song_id,
//                                  @RequestParam("band_id") String band_id,
                                  @RequestParam("album_id") String album_id) {

        try {
            long songId = Long.parseLong(song_id);
            Song song = songRepo.findOne(songId);
            long albumId = Long.parseLong(album_id);
            Album album = albumRepo.findOne(albumId);
            song.setAlbum(album);
            songRepo.save(song);

        } catch (Exception ex) {

        }
        return "redirect:/";
    }

}
