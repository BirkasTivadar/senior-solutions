package org.training360.musicstore;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Instrument> instruments = new ArrayList<>();

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Integer> price) {
        return instruments.stream()
                .filter(instrument -> (brand.isEmpty() || instrument.getBrand().equalsIgnoreCase(brand.get()))
                        && (price.isEmpty() || instrument.getPrice() == price.get()))
                .map(instrument -> modelMapper.map(instrument, InstrumentDTO.class))
                .collect(Collectors.toList());
    }

    public InstrumentDTO addNewInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGenerator.incrementAndGet(), command.getBrand(), command.getType(), command.getPrice(), LocalDate.now());
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAllInstruments() {
        idGenerator = new AtomicLong();
        instruments.clear();
    }

    private Instrument findInstrumentById(Long id) {
        return instruments.stream()
                .filter(instrument -> instrument.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found: " + id));
    }

    public InstrumentDTO getInstrumentById(Long id) {
        return modelMapper.map(findInstrumentById(id), InstrumentDTO.class);
    }

    public void deleteInstrumentById(Long id) {
        Instrument instrument = findInstrumentById(id);
        instruments.remove(instrument);
    }

    public InstrumentDTO updateInstrumentPrice(Long id, UpdatePriceCommand command) {
        Instrument instrument = findInstrumentById(id);
        if (instrument.getPrice() != command.getPrice()) {
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }
        return modelMapper.map(instrument, InstrumentDTO.class);
    }
}
