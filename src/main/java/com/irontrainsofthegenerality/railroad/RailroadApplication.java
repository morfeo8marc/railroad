package com.irontrainsofthegenerality.railroad;

import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.TownInformation;
import com.irontrainsofthegenerality.railroad.repository.TownRepository;

@SpringBootApplication
public class RailroadApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailroadApplication.class, args);
    }
    

}
