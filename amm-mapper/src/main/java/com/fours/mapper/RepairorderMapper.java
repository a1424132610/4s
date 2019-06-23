package com.fours.mapper;

import com.fours.domain.Repairorder;
import com.fours.domain.Repairorderitem;

import java.util.List;

public interface RepairorderMapper extends BaseMapper<Repairorder>{
    Repairorder queryByItems(Long id);

    void updateByZt(Long id);

    void updateByZt2(Long id);
}