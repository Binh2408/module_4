package org.example.musicplayerapp.service;

import org.example.musicplayerapp.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
    Song findById(Long id);
}
