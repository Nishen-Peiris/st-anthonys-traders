package com.nishenpeiris.StockManagementSystem.controller;

import com.nishenpeiris.StockManagementSystem.Item;
import com.nishenpeiris.StockManagementSystem.ItemCodeAlreadyInUseException;
import com.nishenpeiris.StockManagementSystem.ItemNameAlreadyInUseException;
import com.nishenpeiris.StockManagementSystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(path = "/item")
public class ItemController {
    ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<?> getItems() {
        try {
            return new ResponseEntity<>(itemRepository.query(null), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> create(@RequestBody Item item) {
        try {
            itemRepository.add(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItemNameAlreadyInUseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (ItemCodeAlreadyInUseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<?> update(@RequestBody Item item) {
        try {
            itemRepository.update(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<?> delete(@RequestBody Item item) {
        try {
            itemRepository.remove(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
