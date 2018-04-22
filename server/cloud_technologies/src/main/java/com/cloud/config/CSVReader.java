package com.cloud.config;

import com.cloud.model.KickStarter;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CSVReader {


    private SparkSession sparkSession;
    @Value("${csv.path}")
    private String csvPath;

    @Autowired
    public CSVReader(SparkSession sparkSession){
        this.sparkSession = sparkSession;
    }

    public Dataset<KickStarter> getCSVRecords() {

        Dataset<Row> rows = sparkSession.read().format("csv")
                .option("inferSchema", "true")
                .option("header", true).load(csvPath);
        Encoder encoder = Encoders.bean(KickStarter.class);
        Dataset<KickStarter> kickStarterDataset = rows.as(encoder);
        return kickStarterDataset;
    }


}
