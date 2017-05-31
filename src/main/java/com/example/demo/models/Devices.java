package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by mcbec on 5/30/2017.
 */
@Entity
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String operatingSystem;
    @NotNull
    private Long diskFreeSpace;
    @NotNull
    private Long diskTotalSpace;
    @NotNull
    private Long memory;
    @NotNull
    private String model;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Long getDiskFreeSpace() {
        return diskFreeSpace;
    }

    public void setDiskFreeSpace(Long diskFreeSpace) {
        this.diskFreeSpace = diskFreeSpace;
    }

    public Long getDiskTotalSpace() {
        return diskTotalSpace;
    }

    public void setDiskTotalSpace(Long diskTotalSpace) {
        this.diskTotalSpace = diskTotalSpace;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
