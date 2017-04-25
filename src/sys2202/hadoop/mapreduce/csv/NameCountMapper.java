package sys2202.hadoop.mapreduce.csv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.opencsv.CSVParser;

public class NameCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	private CSVParser parser;
	
	public NameCountMapper() {
		
		parser = new CSVParser();
		
	}
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		
		// the CSV file has a header row, which needs to be skipped. the keys for text input files
		// are the file positions, so the header row corresponds to a key of zero. skip this line.
		if(key.get() == 0)
		{
			return;
		}
		
		// parse the record
		String csvLine = value.toString();
        String[] record = parser.parseLine(csvLine);
        
        // create the intermediate key
        Text firstName = new Text(record[1]);
        
        // create the intermediate value
        IntWritable one = new IntWritable(1);
        
        // emit the intermediate key-value pair
        output.collect(firstName, one);
	}
}