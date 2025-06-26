package org.example.musicplayerapp.service;

import org.example.musicplayerapp.model.Song;
import org.example.musicplayerapp.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    private final ISongRepository songRepository;

    @Autowired
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);

    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }
}
