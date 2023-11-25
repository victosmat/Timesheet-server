package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Bonus;
import com.timesheet.dto.bonus.BonusDto;
import com.timesheet.repository.BonusRepository;
import com.timesheet.service.BonusService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;

    public BonusServiceImpl(BonusRepository bonusRepository) {
        this.bonusRepository = bonusRepository;
    }

    @Override
    public List<Bonus> getAll(String keyword) {
        return bonusRepository.findAllByKeyword(keyword);
    }

    @Override
    public Bonus save(BonusDto bonusDto) {
        Bonus bonus;
        if (bonusDto.getId() == null){
            bonus = new Bonus();
        } else {
            bonus = bonusRepository.findById(bonusDto.getId()).orElse(null);
            if (bonus == null){
                return null;
            }
        }
        bonus.setName(bonusDto.getName());
        bonus.setDescription(bonusDto.getDescription());
        bonus.setGratuity(bonusDto.getGratuity());
        return bonusRepository.save(bonus);
    }

    @Override
    public Bonus getById(Integer id) {
        return bonusRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        bonusRepository.deleteById(id);
    }
}
