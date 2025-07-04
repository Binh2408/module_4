package org.example.productmanagement.repository;

import org.example.productmanagement.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManufactureRepository extends JpaRepository<Manufacture,Long> {
}
