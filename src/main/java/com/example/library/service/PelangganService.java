package com.example.library.service;

import java.util.List;
import java.util.Optional;

import com.example.library.entity.Pelanggan;
import com.example.library.repository.PelangganRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PelangganService {
    @Autowired
    private PelangganRepository pelangganRepository;

    public List<Pelanggan> getAll() {
        return pelangganRepository.findAll();
    }

    public Pelanggan getPelangganById(Long id) {
        return pelangganRepository.findById(id).get();
    }

    public List<Pelanggan> getPelangganByKODEPEL(String kodepel) {
        if(pelangganRepository.findByKODEPELContainsIgnoreCase(kodepel).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kode Pelanggan Tidak Ditemukan");
        }  
            return pelangganRepository.findByKODEPELContainsIgnoreCase(kodepel);
        }


    public List<Pelanggan> getPelangganByNAMA(String nama) {
        if(pelangganRepository.findByNAMAContainsIgnoreCase(nama).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nama Pelanggan Tidak Ada !");
        }
            return pelangganRepository.findByNAMAContainsIgnoreCase(nama);
    
    }

    public Pelanggan createPelanggan(Pelanggan pelanggan) {
        Optional<Pelanggan> pelangganOptional = pelangganRepository.findByKODEPEL(pelanggan.getKODEPEL());
        if (pelangganOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "KODE PELANGGAN SAMA !");
		}
		else {
	        pelangganRepository.save(pelanggan);
	        throw new ResponseStatusException(HttpStatus.OK, "PELANGGAN BERHASIL DITAMBAHKAN");
		}
    }

    public Pelanggan editPelanggan(Pelanggan pelanggan, Long id) {
        Optional<Pelanggan> postOptional = pelangganRepository.findById(id);
		
		if (postOptional.isPresent()) {
			pelangganRepository.save(pelanggan);
			throw new ResponseStatusException(
				HttpStatus.OK, "SUKSES  EDIT PELANGGAN"
			);
		}
		else {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "PELANGGAN DENGAN [ID=" + pelanggan.getID() + "] TIDAK DITEMUKAN !");
		}
    }

    public void deletePelanggan(Long id) {
        Optional<Pelanggan> pelangganOptional = pelangganRepository.findById(id);
		
		if (pelangganOptional.isPresent()) {
            pelangganRepository.deleteById(id);
			throw new ResponseStatusException(HttpStatus.OK, "Berhasil dihapus");
		}
		else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID tidak ada");
		}
    }
}
