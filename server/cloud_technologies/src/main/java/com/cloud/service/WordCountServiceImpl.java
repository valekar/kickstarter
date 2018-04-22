package com.cloud.service;


import com.cloud.model.Count;
import com.cloud.model.Word;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.col;

/**
 * <p>Word count example for reference</p>
 */
@Service
public class WordCountServiceImpl implements IWordCountService{

    private SparkSession sparkSession;

    @Autowired
    public WordCountServiceImpl(SparkSession sparkSession){
        this.sparkSession = sparkSession;
    }

    public List<Count> count() {
        String input = "hello world hello hello hello";
        String[] _words = input.split(" ");
        List<Word> words = Arrays.stream(_words).map(Word::new).collect(Collectors.toList());
        Dataset<Row> dataFrame = sparkSession.createDataFrame(words, Word.class);
        dataFrame.show();


        RelationalGroupedDataset groupedDataset = dataFrame.groupBy(col("word"));
        groupedDataset.count().show();
        List<Row> rows = groupedDataset.count().collectAsList();//JavaConversions.asScalaBuffer(words)).count();
        //System.out.println(rows);
        return rows.stream().map(new Function<Row, Count>() {
            @Override
            public Count apply(Row row) {
                return new Count(row.getString(0), row.getLong(1));
            }
        }).collect(Collectors.toList());
    }
}
