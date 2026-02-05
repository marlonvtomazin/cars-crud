package com.marlonvtomazin.carscrud.client.web.dto.mapper;


import com.marlonvtomazin.carscrud.client.entity.Client;
import com.marlonvtomazin.carscrud.client.web.dto.ClientCreateDto;
import com.marlonvtomazin.carscrud.client.web.dto.ClientResponseDto;
import com.marlonvtomazin.carscrud.client.web.dto.ClientUpdateDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    // Could be same method for both but using two is easier to control the flow
    public static Client toCreateEntity(ClientCreateDto clientCreateDto){
        return modelMapper.map(clientCreateDto, Client.class);
    }

    public static Client toUpdateEntity(ClientUpdateDto clientCreateDto){
        return modelMapper.map(clientCreateDto, Client.class);
    }

    public static ClientResponseDto toDto(Client car){
        return modelMapper.map(car, ClientResponseDto.class);
    }


//
//    public static List<ClientResponseDto> toListDto(List<Client> clients) {
//        //return clients.stream().map(client -> toDto(client)).collect(Collectors.toList());
//        return clients.stream().map(ClientMapper::toDto).collect(Collectors.toList());
//    }
}
