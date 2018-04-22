package com.cloud.service;

import com.cloud.ViewModel.ProjectVM;
import com.cloud.config.CSVReader;
import com.cloud.model.KickStarter;
import com.cloud.model.PledgeState;
import com.cloud.model.TotalPledge;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

//import org.apache.spark.api.java.function.Function;

/**
 * <p>Class for implementing main functionalities of the assignment</p>
 */
@Service
public class KickStarterServiceImpl implements IKickStarterService {

    private CSVReader csvReader;

    @Autowired
    public KickStarterServiceImpl(CSVReader csvReader) {
        this.csvReader = csvReader;
    }


    public void test() {
        Dataset<KickStarter> dataset = csvReader.getCSVRecords();
        RelationalGroupedDataset groupedDataset = dataset.where(col("name").like("%an%")).groupBy(col("ID"));
        groupedDataset.count().show();
    }

    @Override
    public List<KickStarter> findProjects(ProjectVM projectVM) {
        Dataset<KickStarter> dataset = csvReader.getCSVRecords();

        Column main_categoryColumn = col("main_category").isNotNull();
        Column categoryColumn = col("category").isNotNull();
        Column countryColumn = col("country").isNotNull();

        if(!projectVM.getCategory().equals("")){
            main_categoryColumn =  col("main_category").like("%" + projectVM.getCategory() + "%");
        }
        if(!projectVM.getSubCategory().equals("")){
            categoryColumn = (col("category").like("%" + projectVM.getSubCategory() + "%"));
        }
        if(!projectVM.getCountry().equals("")){
            countryColumn = col("country").equalTo(projectVM.getCountry());
        }

        Dataset<KickStarter> searched =
                dataset.where(main_categoryColumn.and(categoryColumn).and(countryColumn))
                        .orderBy(col("ID").desc());
        dataset.show();
        //Dataset<KickStarter> rows = dataset.;
        Dataset<KickStarter> rows = searched.filter(col("ID").isNotNull()
                .and(col("name").isNotNull())
                .and(col("category").isNotNull())
                .and(col("main_category").isNotNull())
                .and(col("currency").isNotNull())
                .and(col("deadline").isNotNull())
                .and(col("goal").isNotNull())
                .and(col("launched").isNotNull())
                .and(col("pledged").isNotNull())
                .and(col("state").isNotNull())
                .and(col("backers").isNotNull())
                .and(col("country").isNotNull())
                .and(col("usd_pledged").isNotNull())
                .and(col("usd_pledged_real").isNotNull())
                .and(col("usd_goal_real").isNotNull())
        );
        List<KickStarter> rowList = rows.orderBy(col("usd_pledged_real").desc()).limit(100).collectAsList();
        return rowList;
    }

    public List<String> getCategoryList(){
        Dataset<KickStarter> dataset = csvReader.getCSVRecords();
        Column[] colList =  { col("main_category")};
        Dataset<Row> searched = dataset.select(colList).orderBy(col("main_category").asc()).distinct();
        searched.show();

        List<Row> rowList = searched.collectAsList();
        List<String> rows = rowList.stream().map((Row row)->new String(row.getString(0)))
                .collect(Collectors.toList());

        return rows;
    }

    @Override
    public List<String> getSubCategoryList() {
        Dataset<KickStarter> dataset = csvReader.getCSVRecords();
        Column[] colList =  { col("category")};
        Dataset<Row> searched = dataset.select(colList).orderBy(col("category").asc()).distinct();
        searched.show();

        List<Row> rowList = searched.collectAsList();
        List<String> rows = rowList.stream().map((Row row)->new String(row.getString(0)))
                .collect(Collectors.toList());

        return rows;
    }

    @Override
    public List<String> getCountryList() {
        Dataset<KickStarter> dataset = csvReader.getCSVRecords();
        Column[] colList =  { col("country")};
        Dataset<Row> searched = dataset.select(colList).distinct().orderBy(col("country").asc());
        //searched.show();

        List<Row> rowList = searched.collectAsList();
        List<String> rows = rowList.stream().map((Row row)->new String(row.getString(0)))
                .collect(Collectors.toList());

        return rows;
    }

    @Override
    public List<TotalPledge> getTotalAmountByCategory(ProjectVM projectVM) {
        Dataset<KickStarter> dataSet = csvReader.getCSVRecords();

        Column categoryColumn = col("main_category").like("%"+projectVM.getCategory()+"%");
        Column countryColumn = col("country").like(("%"+projectVM.getCountry()+"%"));

        if(projectVM.getCategory().equals("")){
            categoryColumn = col("main_category").isNotNull();
        }
        if(projectVM.getCountry().equals("")){
            countryColumn = col("country").isNotNull();
        }
        Dataset<KickStarter> filtered = dataSet.where(categoryColumn.and(countryColumn));
        RelationalGroupedDataset groupedDataset = filtered.groupBy(col("main_category"));
        String[] colList = new String[]{"pledged", "backers"};
        //groupedDataset.sum(colList).show();
        List<Row> finalSet = groupedDataset.sum(colList).orderBy(col("main_category").asc()).collectAsList();

        List<TotalPledge> totalPledges = finalSet.stream().map((Row row)->
                new TotalPledge(row.getString(0),row.getDouble(1),row.getLong(2))
        ).collect(Collectors.toList());

        return totalPledges;

    }


    @Override
    public List<PledgeState> getPledgeState(ProjectVM projectVM) {
        Dataset<KickStarter> dataSet = csvReader.getCSVRecords();

        Column categoryColumn = col("category").like("%"+projectVM.getCategory()+"%");
        Column stateColumn = col("state").like(("%"+projectVM.getState()+"%"));

        if(projectVM.getCategory().equals("")){
            categoryColumn = col("category").isNotNull();
        }
        if(projectVM.getState().equals("")){
            stateColumn = col("state").isNotNull();
        }
        Dataset<KickStarter> filtered = dataSet.where(categoryColumn.and(stateColumn));
        RelationalGroupedDataset groupedDataset = filtered.groupBy("category","state");
       // String[] colList = new String[]{"pledged"};
        System.out.println("testttt");
        groupedDataset.count().show();
        List<Row> finalSet = groupedDataset.count().orderBy(col("category").asc()).collectAsList();

        List<PledgeState> pledgeStates = finalSet.stream().map((Row row)->
                new PledgeState(row.getString(0),row.getString(1),row.getLong(2))
        ).collect(Collectors.toList());

        return pledgeStates;

    }

}


//RelationalGroupedDataset groupedDataset = dataset.groupBy(col("main_category"));

//List<Row> rowList = rows.orderBy(col("usd_pledged_real").desc()).limit(100).collectAsList();

        /*List<KickStarter> kickStarters = rowList.stream().map(new Function<Row, KickStarter>() {
            @Override
            public KickStarter apply(Row row) {
                //System.out.println("+++++++++++++++++++ROW++++++++++++++===");
                //System.out.println(row);

                return new KickStarter(row.getInt(0),
                        row.getString(1),
                        row.getString(2),
                        row.getString(3),
                        row.getString(4),
                        row.getString(5),
                        row.getDouble(6),
                        row.getString(7),
                        row.getDouble(8),
                        row.getString(9),
                        row.getInt(10),
                        row.getString(11),
                        row.getDouble(12),
                        row.getDouble(13),
                        row.getDouble(14)
                );
            }
        }).collect(Collectors.toList());*/
//return rows;