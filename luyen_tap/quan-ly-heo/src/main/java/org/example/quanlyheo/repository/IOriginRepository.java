package org.example.quanlyheo.repository;

import org.example.quanlyheo.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOriginRepository extends JpaRepository<Origin,Long> {
}
