package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    /**
     *
     * To save or update a trade
     *
     */
    public Trade doSave(TradeDTO tradeDTO){

        Trade trade = new Trade();
        trade.setId(tradeDTO.getId());
        trade.setAccount(tradeDTO.getAccount());
        trade.setType(tradeDTO.getType());
        trade.setBuyQuantity(trade.getBuyQuantityAsDouble(tradeDTO.getBuyQuantity()));

        return tradeRepository.save(trade);
    }

    /**
     *
     * To get the list of tradeDTO
     *
     */
    public List<TradeDTO> findAll(){
        List<TradeDTO> tradeDTOList = new ArrayList<>();
        List<Trade> tradeList = tradeRepository.findAll();

        for (Trade curseTrade: tradeList){
            tradeDTOList.add(new TradeDTO(
              curseTrade.getId(),
              curseTrade.getAccount(),
              curseTrade.getType(),
              curseTrade.getBuyQuantityAsString(curseTrade.getBuyQuantity())
            ));
        }
        return tradeDTOList;
    }

    /**
     *
     * To get a trade
     *
     */
    public Optional<Trade> findById(Integer id){
        Optional<Trade> trade = tradeRepository.findById(id);
        return trade;
    }

    /**
     *
     * To delete a trade
     *
     */
    public void doDelete(Integer id){
        tradeRepository.deleteById(id);
    }

    /**
     *
     * To convert a trade to a tradeDTO
     *
     */
    public TradeDTO getTradeDTO(Trade trade){
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setId(trade.getId());
        tradeDTO.setAccount(trade.getAccount());
        tradeDTO.setType(trade.getType());
        tradeDTO.setBuyQuantity(trade.getBuyQuantityAsString(trade.getBuyQuantity()));

        return tradeDTO;
    }
}
