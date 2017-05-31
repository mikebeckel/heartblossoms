package com.example.demo.repositories;

import com.example.demo.models.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by mcbec on 5/30/2017.
 */
public interface DevicesRepository extends JpaRepository<Devices, Long> {
    List<Devices> findByName(final String name);
}
