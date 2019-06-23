package com.fours.service;

import com.fours.domain.Repairorderitem;
import com.fours.service.IBaseService;
import com.fours.domain.Repairorder;

import java.util.List;

public interface IRepairorderService extends IBaseService<Repairorder> {
    Repairorder queryByItems(Long id);

    void updateZt(Long id);

    void updateZt2(Long id);
}
