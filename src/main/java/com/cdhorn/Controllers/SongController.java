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
        Iterable<Album> albums = albumRepo.findAll();
        model.addAttribute("albums", albums);
        return "addSong";
    }

//    @RequestMapping("/addSong")
//    public String addSong() {
////        @RequestParam("title") String title,
////        @RequestParam("album_title") String album_title,
////        Model model
////        Song song = new Song();
////        Iterable<Album> album = albumRepo.findAlbumByTitle(album_title);
////        Iterable<Band> band = bandRepo.findBandByBandname(album_title);
////        song.setTitle(title);
//////        song.setAlbum();
////        songRepo.save(song);
//        return "addSong";
//    }
}
