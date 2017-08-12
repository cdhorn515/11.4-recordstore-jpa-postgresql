package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.AlbumRepository;
import com.cdhorn.Interfaces.BandRepository;
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

    @RequestMapping(value = "/albumInfo", method = RequestMethod.POST)
    public String albumInfo(Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);
        return "addAlbum";
    }
    //GET request
    @RequestMapping("/addAlbum")
    public String addAlbum(Model model) {
        Iterable<Band> bands = bandRepo.findAll();
        model.addAttribute("bands", bands);

        return "addAlbum";
    }

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public String addAlbum(@RequestParam("title") String title,
                           @RequestParam("yearReleased") int yearReleased,
                           @RequestParam("bandnameId") String bandnameId) {
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
                                   Model model) throws Exception{
        try{
            Album album = albumRepo.findAlbumByTitle(title);
            model.addAttribute("album", album);

        } catch (Exception ex) {
            return "albumDetail";
        }
        return "albumDetail";
    }

}
