package com.cdhorn.Controllers;

import com.cdhorn.Interfaces.BandRepository;
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

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/addBand", method = RequestMethod.POST)
    public String addBand(@RequestParam("bandname") String name,
                          @RequestParam("genre") String genre,
                          @RequestParam("website") String website) {
        Band band = new Band();
        band.setBandname(name);
        band.setGenre(genre);
        band.setWebsite(website);
        bandRepo.save(band);
        return "index";
    }
    //get endpoint
    @RequestMapping("/addBand")
    public String addBand() {
        return "addBand";
    }

    @RequestMapping("/bandDetail")
    public String bandSearchResult(
            @RequestParam("bandname") String bandname,
            Model model) {

        Iterable<Band> band = bandRepo.findBandByBandname(bandname);
        model.addAttribute("band", band);
        return "bandDetail";
    }

}
