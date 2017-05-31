package com.example.demo.controllers;

import com.example.demo.models.Devices;
import com.example.demo.repositories.DevicesRepository;
import com.example.demo.util.CustomErrorType;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

/**
 * Created by mcbec on 5/30/2017.
 */
@RestController
@RequestMapping("/api/v1")
public class DevicesControllerV1 {

    @Autowired
    DevicesRepository devicesRepository;

    // -------------------Populate DB
    @RequestMapping(value = "/devicesinit/", method = RequestMethod.GET)
    public ResponseEntity<?> populateDB() {

        devicesRepository.deleteAll();
        Integer i;
        for (i = 0; i < 6; i++) {
            Devices device = new Devices();
            device.setName("7GW0CZ1_" + i.toString());
            device.setModel("Precision T1700");
            device.setOperatingSystem("Windows Me");
            device.setDiskFreeSpace(100L);
            device.setDiskTotalSpace(1000L);
            device.setMemory(4096L);
            devicesRepository.save(device);
        }
        if (devicesRepository.findAll().size() != 6) {
            return new ResponseEntity(new CustomErrorType("Failed To Initialize DB"), HttpStatus.I_AM_A_TEAPOT);
        }

//        UserSpecification
//        devicesRepository.findAll()

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // -------------------Retrieve All Devices---------------------------------------------

    @RequestMapping(value = "/devices/", method = RequestMethod.GET)
    public ResponseEntity<List<Devices>> getAllDevices() {
        List<Devices> devices = devicesRepository.findAll();
        if (devices.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Devices>>(devices, HttpStatus.OK);
    }

    // -------------------Retrieve Single Device-------------------------------------------
    @RequestMapping(value = "/devices/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDevice(@PathVariable("id") long id) {
        Devices device = devicesRepository.findOne(id);
        if (device == null) {
            return new ResponseEntity(new CustomErrorType("No Device With ID: " + id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Devices>(device, HttpStatus.OK);
    }

    // -------------------Create A Device--------------------------------------------------
    @RequestMapping(value = "/devices/", method = RequestMethod.POST)
    public ResponseEntity<?> createDevice(@RequestBody Devices device, UriComponentsBuilder ucBuilder) {
        if ( devicesRepository.findByName(device.getName()).size() > 0) {
            return new ResponseEntity(new CustomErrorType("Unable To Create"), HttpStatus.CONFLICT);
        }

        devicesRepository.save(device);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/devices/{id}").buildAndExpand(device.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
