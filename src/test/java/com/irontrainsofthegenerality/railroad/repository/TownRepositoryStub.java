package com.irontrainsofthegenerality.railroad.repository;

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.irontrainsofthegenerality.railroad.domain.Town;
import com.irontrainsofthegenerality.railroad.domain.TownInformation;

@Component
public class TownRepositoryStub extends TownRepository {

	public TownRepositoryStub(){
		super();
		IntStream.range(65, 91).forEach(i ->{
			save(new Town(new TownInformation(String.valueOf(Character.toChars(i)),0f, 0f), i-65));
		});
		
		IntStream.range(97, 123).forEach(i ->{
			save(new Town(new TownInformation(String.valueOf(Character.toChars(i)),0f, 0f), i-39));
		});
	}
}
