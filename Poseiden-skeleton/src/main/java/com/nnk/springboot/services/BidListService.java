package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public BidList doSave(BidListDTO bidListDTO){
        BidList bidList = new BidList();
        bidList.setId(bidListDTO.getId());
        bidList.setAccount(bidListDTO.getAccount());
        bidList.setType(bidListDTO.getType());
        bidList.setBidQuantity(bidList.getBidQuantityAsDouble(bidListDTO.getBidQuantity()));
        return bidListRepository.save(bidList);
    }

    public List<BidListDTO> findAll(){
        List<BidListDTO> bidListDTOList = new ArrayList<>();
        List<BidList> bidListList = bidListRepository.findAll();

        for (BidList curseBidList : bidListList
        ) {
            bidListDTOList.add(
                    new BidListDTO(
                    curseBidList.getId(),
                    curseBidList.getAccount(),
                    curseBidList.getType(),
                    curseBidList.getBidQuantityAsString(curseBidList.getBidQuantity()))
            );
        }
        return bidListDTOList;
    }

    public Optional<BidList> findById(Integer id){
        Optional<BidList> bidList = bidListRepository.findById(id);
        return bidList;
    }

    public void doDelete(Integer id){
        bidListRepository.deleteById(id);
    }

    public BidListDTO getBidListDTO(BidList bidList){
        BidListDTO bidListDTO = new BidListDTO();

        bidListDTO.setId(bidList.getId());
        bidListDTO.setAccount(bidList.getAccount());
        bidListDTO.setType(bidList.getType());
        bidListDTO.setBidQuantity(bidList.getBidQuantityAsString(bidList.getBidQuantity()));

        return bidListDTO;
    }
}
