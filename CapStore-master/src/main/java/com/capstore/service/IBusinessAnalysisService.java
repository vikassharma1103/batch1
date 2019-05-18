package com.capstore.service;

import java.util.Date;
import java.util.List;

import com.capstore.model.DispatchAnalysis;
import com.capstore.model.SalesAnalysis;


public interface IBusinessAnalysisService {

	public double getTotalRevenueBetween(Date fromDate, Date toDate);
	public List<SalesAnalysis> getSalesAnalysis(Date fromDate, Date toDate);
	public List<DispatchAnalysis> getDispatchDetailsBetween(Date fromDate, Date toDate);
}
