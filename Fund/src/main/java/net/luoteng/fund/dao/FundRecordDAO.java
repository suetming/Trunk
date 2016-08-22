/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.dao;

import javax.transaction.Transactional;
import net.luoteng.fund.entity.FundRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Transactional
public interface FundRecordDAO extends PagingAndSortingRepository<FundRecord, String> {

    
    
}
