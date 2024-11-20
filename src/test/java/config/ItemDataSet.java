package config;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemDataSet {
    @DataProvider(name = "ItemCSVData")
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/expenditure.csv";
        List<Object[]> data = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for(CSVRecord csvRecord:csvParser){
            String itemName = csvRecord.get("itemName");
            String quantity = csvRecord.get("quantity");
            String amounts = csvRecord.get("amounts");
            String purchaseDates = csvRecord.get("purchaseDates");
            String months = csvRecord.get("months");
            String remarks = csvRecord.get("remarks");
            data.add(new Object[]{itemName,quantity,amounts,purchaseDates,months,remarks});

        }
        return data.toArray(new Object[0][]);
    }
}
