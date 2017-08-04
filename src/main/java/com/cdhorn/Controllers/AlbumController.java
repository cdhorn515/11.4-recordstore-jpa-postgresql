package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.AlbumRepository;
import com.cdhorn.Interfaces.BandRepository;
import com.cdhorn.Interfaces.SongRepository;
import com.cdhorn.Models.Album;
import com.cdhorn.Models.Band;
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

    @RequestMapping("/addAlbum")
    public String addAlbum(Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);

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

    @RequestMapping("/albumDetail")
    public String albumSearchResult(@RequestParam("title") String title,
                                   Model model) {
        Iterable<Album> album = albumRepo.findAlbumByTitle(title);
//        Iterable<Song> songs = songRepo.findAllByAlbum(title);
        model.addAttribute("album", album);
//        model.addAttribute("songs", songs);
        return "albumDetail";
    }

}
