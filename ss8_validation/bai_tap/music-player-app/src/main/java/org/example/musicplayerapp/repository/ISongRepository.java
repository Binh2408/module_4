package org.example.musicplayerapp.repository;

import org.example.musicplayerapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepository extends JpaRepository<Song,Long> {
}
