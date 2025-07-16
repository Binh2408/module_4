package org.example.quanlyheo.repository;

import org.example.quanlyheo.model.Pig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPigRepository extends JpaRepository<Pig,Long> {
    @Query("SELECT p FROM Pig p WHERE " +
            "(:code IS NULL OR p.code LIKE %:code%) AND " +
            "(:originId IS NULL OR p.origin.id = :originId) AND " +
            "(:status IS NULL OR " +
            " (:status = true AND p.outputDate IS NOT NULL) OR " +
            " (:status = false AND p.outputDate IS NULL))")
    Page<Pig> search(@Param("code") String code,
                     @Param("status") Boolean status,
                     @Param("originId") Long originId,
                     Pageable pageable);
    @Query("SELECT p FROM Pig p WHERE p.outputDate IS NOT NULL ORDER BY p.outputWeight DESC")
    Page<Pig> findTopExportedPigs(Pageable pageable);

}
