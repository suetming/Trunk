/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.dao;

import java.awt.print.Pageable;
import java.util.List;
import javax.transaction.Transactional;
import net.luoteng.fund.entity.FundRecord;
import net.luoteng.fund.enums.FundRecordOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Transactional
public interface FundRecordDAO extends PagingAndSortingRepository<FundRecord, String> {

    @Query("select fr from FundRecord fr where fr.userId=:userId and fr.operation in :operations")
    public Page<FundRecord> listByUser(@Param("userId") String userId, @Param("operations") List<FundRecordOperation> operations, Pageable pageable);
    
}
