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
public class AlbumController {

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    BandRepository bandRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping(value = "/albumInfo", method = RequestMethod.POST)
    public String albumInfo(Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);
        return "addAlbum";
    }
    //GET request
    @RequestMapping("/addAlbum")
    public String addAlbum() {
        return "addAlbum";
    }

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public String addAlbum(@RequestParam("title") String title,
                           @RequestParam("yearReleased") int yearReleased,
                           @RequestParam("bandnameId") String bandnameId,
                           Model model) {
        Album album = new Album();
        album.setTitle(title);
        album.setYearReleased(yearReleased);

        try {
            long bandId = Long.parseLong(bandnameId);
            Band band = bandRepo.findOne(bandId);
            album.setBand(band);

        } catch (Exception ex){

        }
        albumRepo.save(album);
        return "redirect:/";
    }
    //GET request
    @RequestMapping("/albumDetail")
    public String albumSearchResult(@RequestParam("title") String title,
                                   Model model) {
        Album album = albumRepo.findAlbumByTitle(title);
        model.addAttribute("album", album);
        return "albumDetail";
    }

    @RequestMapping(value = "/albumDetail", method = RequestMethod.POST)
    public String updateAlbum(@RequestParam("title") String title,
                              @RequestParam("album_id") String album_id,
                              @RequestParam("band_id") String band_id,
                              Model model) {
        Song song = new Song();
        song.setTitle(title);

        try {
            long albumId = Long.parseLong(album_id);
            Album album = albumRepo.findOne(albumId);
            song.setAlbum(album);

            long bandId = Long.parseLong(band_id);
            Band band = bandRepo.findOne(bandId);
            song.setBand(band);
        } catch (Exception ex) {

        }
        songRepo.save(song);
        return ("redirect:/");
    }

}
