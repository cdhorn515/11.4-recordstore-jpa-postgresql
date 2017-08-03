package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.AlbumRepository;
import com.cdhorn.Interfaces.BandRepository;
import com.cdhorn.Interfaces.SongRepository;
import com.cdhorn.Models.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String addBand(@RequestParam("name") String name,
                          @RequestParam("genre") String genre) {
        Band band = new Band();
        band.setName(name);
        band.setGenre(genre);
        bandRepo.save(band);
        return "index";
    }

    @RequestMapping("/addBand")
    public String addBand() {
        return "addBand";
    }

    @RequestMapping("/addAlbum")
    public String addAlbum() {
        return "addAlbum";
    }

    @RequestMapping("/addSong")
    public String addSong() {
        return "addSong";
    }
//    @RequestMapping("/bandSearch")
//    public String bandSearchResult(@RequestParam("name") String name,
//                               Model model) {
//        Iterable<Band> bands = bandRepo.findAll();
//        model.addAttribute("bands", bands);
//        return "bandDetail";
//    }
}
