package com.example.library.controller;

import java.util.List;

import com.example.library.entity.Pelanggan;
import com.example.library.service.PelangganService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/pelanggan")
public class PelangganController {
    private final PelangganService pelangganServices;
    
    public PelangganController(PelangganService pelangganServices) {
        this.pelangganServices = pelangganServices;
    }

    @GetMapping("")
    public List<Pelanggan> getAll() {
        return pelangganServices.getAll();
    }

    @GetMapping("/{id}")
    public Pelanggan getById(@PathVariable final Long id){
        return pelangganServices.getPelangganById(id);
    }

    @GetMapping("/bykode/{kodepel}")
    public List<Pelanggan> getByKodePel(@PathVariable final String kodepel){
        return pelangganServices.getPelangganByKODEPEL(kodepel);
    }

    @GetMapping("/bynama/{nama}")
    public List<Pelanggan> getByNama(@PathVariable final String nama){
        return pelangganServices.getPelangganByNAMA(nama);
    }

    @PostMapping("")
	public Pelanggan create(@RequestBody final Pelanggan pelanggan) {
		return pelangganServices.createPelanggan(pelanggan);
	}

    @PutMapping("/{id}")
    public Pelanggan edit(@RequestBody final Pelanggan pelanggan, @PathVariable final Long id){
    	return pelangganServices.editPelanggan(pelanggan, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id){
        pelangganServices.deletePelanggan(id);
    }
}
