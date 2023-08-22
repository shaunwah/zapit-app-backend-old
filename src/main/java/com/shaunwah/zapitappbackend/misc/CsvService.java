package com.shaunwah.zapitappbackend.misc;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;

@Service
public class CsvService {
    public <T> List<T> readAllLines(File file, Class<T> clazz) throws Exception {
        try (Reader reader = Files.newBufferedReader(file.toPath())) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .build();
            List<T> lines = csvToBean.parse();
            reader.close();
            return lines;
        }
    }

    public <T> String writeAllLines(File file, List<T> lines) throws Exception {
        try (Writer writer = Files.newBufferedWriter(file.toPath())) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withOrderedResults(true)
                    .build();
            beanToCsv.write(lines);
            writer.close();
            return file.getPath();
        }
    }
}
