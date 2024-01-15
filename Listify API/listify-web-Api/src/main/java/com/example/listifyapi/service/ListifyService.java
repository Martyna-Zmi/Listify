package com.example.listifyapi.service;

import com.example.listifyapi.dto.AlbumDto;
import com.example.listifyapi.dto.ArtistDto;
import com.example.listifyapi.dto.TrackDto;
import com.example.listifyapi.dtoMapper.IDtoMapper;
import com.example.listifyapi.entities.Album;
import com.example.listifyapi.entities.Artist;
import com.example.listifyapi.entities.Track;
import com.example.listifyapi.entityMapper.IEntityMapper;
import com.example.listifyapi.exceptions.ResourceAlreadyExistsException;
import com.example.listifyapi.exceptions.ResourceNotFoundException;
import com.example.listifyapi.repositories.RepoCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Component
public class ListifyService {
    private static final Logger logger = LogManager.getLogger(ListifyService.class);
    private final RepoCatalog repoCatalog;
    private final IDtoMapper dtoMapper;
    private final IEntityMapper entityMapper;
    @Autowired
    public ListifyService(RepoCatalog repoCatalog, IDtoMapper dtoMapper, IEntityMapper entityMapper){
        this.repoCatalog = repoCatalog;
        this.dtoMapper = dtoMapper;
        this.entityMapper = entityMapper;
    }
    public List<TrackDto> getAllTracks(){
        var entities = repoCatalog.getTrackRepository().findAll();
        return entities.stream().map(dtoMapper::mapTrack).toList();
    }
    public TrackDto getTrackById(String id){
        var entity = repoCatalog.getTrackRepository().getTrackBySpotifyId(id);
        if(entity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: get track");
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapTrack(entity.get());
    }
    public List<TrackDto> getPopularSongs(){
        var entities = repoCatalog.getTrackRepository().searchAllByPopularityGreaterThanEqual(80);
        return entities.stream().map(dtoMapper::mapTrack).toList();
    }
    public List<ArtistDto> getAllArtists(){
        var entities = repoCatalog.getArtistRepository().findAll();
        return entities.stream().map(dtoMapper::mapArtist).toList();
    }
    public TrackDto getRandomTrack(){
        var tracks = repoCatalog.getTrackRepository().findAll();
        var random = new Random();
        return dtoMapper.mapTrack(tracks.get(random.nextInt(tracks.size())));
    }
    public void saveTrack(TrackDto trackDto){
        var trackInDb = repoCatalog.getTrackRepository().getTrackBySpotifyId(trackDto.getSpotifyId());
        if(trackInDb.isPresent()){
            logger.warn("Resource already exists, Action: save new track with id "+trackDto.getSpotifyId());
            throw new ResourceAlreadyExistsException();
        }
        Track track = entityMapper.mapToTrack(trackDto);
        repoCatalog.getTrackRepository().save(track);
        logger.info("Track with id: '"+track.getSpotifyId()+"' saved successfully");
    }
    public ArtistDto getArtistById(String id){
        var entity = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        if(entity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: get artist");
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapArtist(entity.get());
    }
    public List<ArtistDto> getArtistsByGenre(String genre){
        var artists = repoCatalog.getArtistRepository().searchAllByGenresContaining(genre);
        var results = artists.stream().map(dtoMapper::mapArtist).toList();
        return results;
    }
    public ArtistDto getRandomArtist(){
        var artists = repoCatalog.getArtistRepository().findAll();
        var random = new Random();
        return dtoMapper.mapArtist(artists.get(random.nextInt(artists.size())));
    }
    public List<AlbumDto> getArtistsAlbums(String id){
        var artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        List<Album> albums = new ArrayList<>();
        if(artist.isPresent()){
            artist.get().getTracks().forEach(track -> {
                if(!albums.contains(track.getAlbum())){
                    albums.add(track.getAlbum());
                }
            });
        }
        else{
            logger.warn("Couldn't find the resource, Action: get artist's albums");
            throw new ResourceNotFoundException();
        }
        return albums.stream().map(dtoMapper::mapAlbum).toList();
    }
    public List<ArtistDto> getPopularArtists(){
        var entities = repoCatalog.getArtistRepository().searchAllByPopularityGreaterThanEqual(70);
        return entities.stream().map(dtoMapper::mapArtist).toList();
    }
    public void updateTrack(TrackDto trackDto){
        var entity = repoCatalog.getTrackRepository().getTrackBySpotifyId(trackDto.getSpotifyId());
        if(entity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: update track");
            throw new ResourceNotFoundException();
        }
        else{
            var track = entity.get();
            track.setDurationMs(trackDto.getDurationMs());
            track.setExplicit(trackDto.isExplicit());
            track.setPopularity(trackDto.getPopularity());
            track.setName(trackDto.getName());
            repoCatalog.getTrackRepository().save(track);
            logger.info("Track with id: '"+ trackDto.getSpotifyId()+"' updated successfully");
        }
    }
    public void updateArtist(ArtistDto artistDto){
        var dbEntity = repoCatalog.getArtistRepository().getArtistBySpotifyId(artistDto.getSpotifyId());
        if(dbEntity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: update artist");
            throw new ResourceNotFoundException();
        }
        else{
           var entity = dbEntity.get();
           entity.setName(artistDto.getName());
           entity.setPopularity(artistDto.getPopularity());
           entity.setGenres(artistDto.getGenres());
           entity.setImage(artistDto.getImage());
            repoCatalog.getArtistRepository().save(entity);
            logger.info("Artist with id: '"+ artistDto.getSpotifyId()+"' updated successfully");
        }
    }
    public void updateAlbum(AlbumDto albumDto){
        var dbEntity = repoCatalog.getAlbumRepository().findBySpotifyId(albumDto.getSpotifyId());
        if(dbEntity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: update album");
            throw new ResourceNotFoundException();
        }
        else{
            var entity = dbEntity.get();
            entity.setTotalTracks(albumDto.getTotalTracks());
            entity.setReleaseDate(albumDto.getReleaseDate());
            entity.setAlbumType(albumDto.getAlbumType());
            entity.setName(albumDto.getName());
            entity.setImage(albumDto.getImage());
            repoCatalog.getAlbumRepository().save(entity);
            logger.info("Album with id: '"+ albumDto.getSpotifyId()+"' updated successfully");
        }
    }
    public AlbumDto getAlbumById(String id){
        var entity = repoCatalog.getAlbumRepository().findBySpotifyId(id);
        if(entity.isEmpty()){
            logger.warn("Couldn't find the resource, Action get album");
            throw new ResourceNotFoundException();
        }
        return dtoMapper.mapAlbum(entity.get());
    }
    public List<AlbumDto> getAlbums(){
        var entities = repoCatalog.getAlbumRepository().findAll();
        return entities.stream().map(dtoMapper::mapAlbum).toList();
    }

    public void saveArtist(ArtistDto artistDto){
        Optional<Artist> artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(artistDto.getSpotifyId());
        if(artist.isPresent()){
            logger.warn("Resource already exists, Action: save new artist with id "+artistDto.getSpotifyId());
            throw new ResourceAlreadyExistsException();
        }
        var entity = entityMapper.mapToArtist(artistDto);
        repoCatalog.getArtistRepository().save(entity);
        logger.info("Artist with id: '"+artistDto.getSpotifyId()+"' saved successfully");
    }
    public void saveAlbum(AlbumDto albumDto){
        Optional<Album> album = repoCatalog.getAlbumRepository().findBySpotifyId(albumDto.getSpotifyId());
        if(album.isPresent()){
            logger.warn("Resource already exists, Action: save new album with id "+albumDto.getSpotifyId());
            throw new ResourceAlreadyExistsException();
        }
        var entity = entityMapper.mapToAlbum(albumDto);
        repoCatalog.getAlbumRepository().save(entity);
        logger.info("Album with id: '"+albumDto.getSpotifyId()+"' saved successfully");
    }

    public void deleteArtist(String id){
        var artist = repoCatalog.getArtistRepository().getArtistBySpotifyId(id);
        if(artist.isEmpty()){
            logger.warn("Couldn't find the resource, Action: delete artist");
            throw new ResourceNotFoundException();
        }
        else{
            var entity = artist.get();
            entity.setTracks(new ArrayList<>());
            repoCatalog.getArtistRepository().save(entity);
            entity.getTracks().forEach(track -> deleteTrack(track.getSpotifyId()));
            repoCatalog.getArtistRepository().delete(entity);
            logger.info("Artist with id: '"+id+"' deleted successfully");
        }
    }
    public void deleteAlbum(String id){
        var entity = repoCatalog.getAlbumRepository().findBySpotifyId(id);
        if(entity.isEmpty()){
            logger.warn("Couldn't find the resource, Action: delete album");
            throw new ResourceNotFoundException();
        }
        else{
            var album = entity.get();
            var tracks = album.getTracks();
            album.setTracks(new ArrayList<>());
            tracks.forEach(track -> {
                if(track.getAlbum().getSpotifyId().equals(id)){
                    track.setAlbum(null);
                    deleteTrack(track.getSpotifyId());
                }
            });
            repoCatalog.getAlbumRepository().delete(album);
            logger.info("Album with id: '"+id+"' deleted successfully");
        }
    }
    public void deleteTrack(String id){
        var track = repoCatalog.getTrackRepository().getTrackBySpotifyId(id);
        if(track.isEmpty()){
            logger.warn("Couldn't find the resource, Action: delete track");
            throw new ResourceNotFoundException();
        }
        else{
            var entity = track.get();
            entity.getAlbum().setTotalTracks(entity.getAlbum().getTotalTracks()-1);
            var artists = entity.getArtists();
            artists.forEach(artist ->{
                var tempTracks = new ArrayList<>(artist.getTracks());
                tempTracks.remove(entity);
                artist.setTracks(tempTracks);
            });
            entity.setArtists(new ArrayList<>());
            repoCatalog.getTrackRepository().save(entity);
            repoCatalog.getTrackRepository().delete(entity);
            logger.info("Track with id: '"+id+"' deleted successfully");
        }
    }
}
