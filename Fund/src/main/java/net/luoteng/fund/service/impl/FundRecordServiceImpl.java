/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.service.impl;

import java.util.List;
import net.luoteng.fund.dao.FundRecordDAO;
import net.luoteng.fund.entity.FundRecord;
import net.luoteng.fund.enums.FundRecordOperation;
import net.luoteng.fund.service.FundRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * fund record service
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class FundRecordServiceImpl implements FundRecordService {

    @Autowired
    FundRecordDAO recordDAO;
    
    @Override
    public Page<FundRecord> listByUser(String userId, List<FundRecordOperation> operations, Pageable pageable) {
        return recordDAO.listByUser(userId, operations, pageable);
    }

    @Override
    public FundRecord save(FundRecord record) {
        FundRecord entity;
        if (StringUtils.isBlank(record.getId())) {
            entity = recordDAO.get(
                    record.getUserId(), 
                    record.getOrderId(), 
                    record.getOperat(), 
                    record.getPayType());
            
            if (entity == null) entity = record;
            else entity.setId(record.getId());
        } else
            entity = recordDAO.findOne(record.getId());
        
        if (StringUtils.isNotBlank(record.getPriv()))
            entity.setPriv(record.getPriv());
            
        return recordDAO.save(entity);
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FundRecord get(String recordId) {
        return recordDAO.findOne(recordId);
    }

}
