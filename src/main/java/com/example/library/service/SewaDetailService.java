package com.example.library.service;

import com.example.library.repository.SewaDetailRepository;
import com.example.library.repository.SewaRepository;
import com.example.library.entity.Sewa;
import com.example.library.entity.SewaDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SewaDetailService {
    @Autowired
    private SewaRepository sewaRepository;

    @Autowired
    private SewaDetailRepository sewaDetailRepository;

    public List<Sewa> getAll() {
        return sewaRepository.findAll();
    }

    public List<SewaDetail> getAllSewaDetails() {
        if (sewaDetailRepository.getAllSewaDetail().isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sewa tidak ada");
        }
        return sewaDetailRepository.getAllSewaDetail();
    }

    public Sewa getById(Long idSewa) {
        Optional<Sewa> sewaOptional = sewaRepository.findById(idSewa);

        if(sewaOptional.isPresent()){
            return sewaOptional.get();
		}
		else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sewa tidak ada");
		}
    }

    public void save(Sewa sewa) {
        Optional<Sewa> sewaOptional = sewaRepository.findByISBN(sewa.getISBN());

        if (sewaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A book with same ISBN number is present!");
        }
        else {
            sewaRepository.save(sewa);
            throw new ResponseStatusException(HttpStatus.OK, "Book data successfully saved!");
        }
    }

    public void delete(Long idSewa) {
        Optional<Sewa> sewaOptional = sewaRepository.findById(idSewa);

        if(sewaOptional.isPresent()){
            sewaRepository.deleteById(idSewa);
			throw new ResponseStatusException(HttpStatus.OK, "Berhasil dihapus");
		}
		else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sewa tidak ada");
		}
    }
    public List<SewaDetail> getSewaDetailById(Long idSewa) {
        List<SewaDetail> result = sewaDetailRepository.getSewaDetailById(idSewa);
        
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Sewa tidak ada!");
        }

        return result;
    }

    public List<Object[]> getSewaDetailArrayById(Long idSewa) {
        List<Object[]> result = sewaRepository.getSewaDetailById(idSewa);
        
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Sewa tidak ada!");
        }

        return result;
    }
}
