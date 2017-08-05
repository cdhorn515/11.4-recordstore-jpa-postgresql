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

//    @RequestMapping(value = "/addSong/{title}", method = RequestMethod.GET)
//    public String addSongAlone(@PathVariable("title") String title,
//                          Model model) {
//        Song song = songRepo.findByTitle(title);
//        model.addAttribute("song", song);
////        or whatever page you want to render
//        return "addSongAlbum";
//    }

    @RequestMapping(value = "/addSongAndAlbum", method = RequestMethod.POST)
    public String addSongAndAlbum(@RequestParam("title") String title,
                                  @RequestParam("song_id") String song_id,
                                  @RequestParam("band_id") String band_id,
                                  @RequestParam("album_id") String album_id,
                          Model model) {

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

    /*
        @RequestMapping(value = "/edit/{movieId}", method = RequestMethod.GET)
    public String editLandingPage(@PathVariable("movieId") long movieId, Model model) {
        Movie movie = movieRepo.findOne(movieId);
        model.addAttribute("movie", movie);
        return "edit";
    }


    @RequestMapping(value = "/edit/{movieId}", method = RequestMethod.POST)
    public String edit(@PathVariable("movieId") long movieId,
                            @RequestParam("title") String title,
                            @RequestParam("genre") String genre,
                            @RequestParam("imdblink") String imdblink,
                            @RequestParam("releasedate") String releasedate,
                            Model model) throws Exception{
        Movie movie = movieRepo.findOne(movieId);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setImdblink(imdblink);
        Date formattedReleaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releasedate);
        movie.setReleasedate(formattedReleaseDate);
        movieRepo.save(movie);
        model.addAttribute(movie);
        return "edit";
    }

     */


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
