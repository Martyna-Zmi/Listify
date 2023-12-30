package com.example.listifyapi.service;
import com.example.listifyapi.dto.TrackDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
@Service
@Component
public class FrontService {
    private final RestClient restClient = RestClient.create();
    private final String URL = "http://localhost:8081/listify/";
    public List<TrackDto> findAllTracks(){
        List<TrackDto> tracks = restClient.get().uri(URL+"/tracks/get")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return tracks;
    }
}
