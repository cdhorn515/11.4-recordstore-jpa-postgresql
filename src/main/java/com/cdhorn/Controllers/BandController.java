package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.AlbumRepository;
import com.cdhorn.Interfaces.BandRepository;
import com.cdhorn.Interfaces.SongRepository;
import com.cdhorn.Models.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BandController {

    @Autowired
    BandRepository bandRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/addBand", method = RequestMethod.POST)
    public String addBand(@RequestParam("bandname") String name,
                          @RequestParam("genre") String genre) {
        Band band = new Band();
        band.setBandname(name);
        band.setGenre(genre);
        bandRepo.save(band);
        return "index";
    }
    //get endpoint
    @RequestMapping("/addBand")
    public String addBand() {
        return "addBand";
    }

    @RequestMapping("/addAlbum")
    public String addAlbum() {
        return "redirect:/addSong";
    }

    @RequestMapping("/addSong")
    public String addSong() {
//        @RequestParam("title") String title,
//        @RequestParam("album_title") String album_title,
//        Model model
//        Song song = new Song();
//        Iterable<Album> album = albumRepo.findAlbumByTitle(album_title);
//        Iterable<Band> band = bandRepo.findBandByBandname(album_title);
//        song.setTitle(title);
////        song.setAlbum();
//        songRepo.save(song);
        return "addSong";
    }
    @RequestMapping("/bandDetail")
    public String bandSearchResult(
            @RequestParam("bandname") String bandname,
            Model model) {
        Iterable<Band> band = bandRepo.findBandByBandname(bandname);
        model.addAttribute("band", band);
        return "bandDetail";
    }
//
//    @RequestMapping("/albumDetail")
//    public String albumSearchResult(@RequestParam("title") String title,
//                                   Model model) {
//        Iterable<Album> album = albumRepo.findAlbumByTitle(title);
//        Iterable<Song> songs = songRepo.findAllByAlbum(title);
//        model.addAttribute("album", album);
//        model.addAttribute("songs", songs);
//        return "albumDetail";
//    }

}
