package com.cloud.service;

import com.cloud.ViewModel.ProjectVM;
import com.cloud.model.KickStarter;
import com.cloud.model.PledgeState;
import com.cloud.model.TotalPledge;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

public interface IKickStarterService {
    void test();

     List<KickStarter> findProjects(ProjectVM projectVM);

     List<String> getCategoryList();

     List<String> getSubCategoryList();
     List<String> getCountryList();

     List<TotalPledge> getTotalAmountByCategory(ProjectVM projectVM);
     List<PledgeState> getPledgeState(ProjectVM projectVM);
}
