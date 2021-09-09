package com.example.library.controller;

import java.util.List;
import com.example.library.entity.Buku;
import com.example.library.service.BukuService;

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
@RequestMapping("/buku")
public class BukuController {
    private final BukuService bukuServices;

    //Constructor
    public BukuController(BukuService bukuServices) {
        this.bukuServices = bukuServices;
    }

    @GetMapping("/")
    public List<Buku> getBuku() {
        return bukuServices.getAll();
    }
    
    @GetMapping("/judul/{judul}")
    public List<Buku> getBukuByJudul(@PathVariable final String judul) {
        return bukuServices.getBukuByJudul(judul);
    }
    
    @GetMapping("/{id}")
    public Buku getBukuById(@PathVariable final Long id) {
        return bukuServices.getBukuByID(id);
    }

    @GetMapping("/penerbit/{penerbit}")
    public List<Buku> getBukuByPenerbit(@PathVariable final String penerbit) {
        return bukuServices.getBukuByPenerbit(penerbit);
    }

    @PostMapping("/")
    public void createBuku(@RequestBody final Buku buku) {
        bukuServices.createBuku(buku);
    }

    @DeleteMapping("/{IdBuku}")
    public void deleteBuku(@PathVariable final Long IdBuku) {
        bukuServices.deleteBuku(IdBuku);
    }

    @PutMapping("/{IdBuku}")
    public void editBuku(@PathVariable final Long IdBuku, @RequestBody final Buku buku) {
        bukuServices.editBuku(IdBuku, buku);
    }
}
