package com.example.library.controller;


import java.util.List;

import com.example.library.service.SewaDetailService;
import com.example.library.entity.Sewa;
import com.example.library.entity.SewaDetail;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/sewa")
public class DetailSewaController {
    private SewaDetailService sewaDetailService;

    //Constructor
    public DetailSewaController(SewaDetailService sewaDetailService) {
        this.sewaDetailService = sewaDetailService;
    }

    @GetMapping("/")
    public List<Sewa> getAll() {
        return sewaDetailService.getAll();
    }

    @GetMapping("/{idSewa}")
    public Sewa get(@PathVariable final Long idSewa) {
        return sewaDetailService.getById(idSewa);
    }

    @GetMapping("/detailarray/{idSewa}")
    public List<Object[]> getSewaDetailArrayById (@PathVariable final Long idSewa) {
        return sewaDetailService.getSewaDetailArrayById(idSewa);
    }

    @GetMapping("/detail/")
    public List<SewaDetail> getAllSewaDetail () {
        return sewaDetailService.getAllSewaDetails();
    }

    @GetMapping("/detail/{idSewa}")
    public List<SewaDetail> getSewaDetailById (@PathVariable final Long idSewa) {
        return sewaDetailService.getSewaDetailById(idSewa);
    
    }

    @PostMapping("/save")
    public void createSewa(@RequestBody final Sewa sewa) {
        sewaDetailService.save(sewa);
    }

    @DeleteMapping("/{idSewa}")
    public void deleteSewa(@PathVariable final Long idSewa) {
        sewaDetailService.delete(idSewa);
    }

}