package com.beaconfire.quizsystem.service;

import com.beaconfire.quizsystem.dao.ChoiceDAO;
import com.beaconfire.quizsystem.dao.ChoiceMapper;
import com.beaconfire.quizsystem.entity.hentity.ChoiceEntity;
import com.beaconfire.quizsystem.entity.mentity.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceMapper choiceMapper;
    @Autowired
    private ChoiceDAO choiceDAO;

    public Choice getChoiceById(int id){
        return choiceMapper.selectByPrimaryKey(id);
    }

    public ChoiceEntity getChoiceById_hibernate(int id){
        ChoiceEntity choice = choiceDAO.getChoiceEntityById(id);
        return choice;
    }

    public void saveQuestionAndItChoice(List<ChoiceEntity> choiceEntities){
        choiceDAO.saveQuestion(choiceEntities);
    }
}
