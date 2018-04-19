package sys2202.homework7;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.opencsv.CSVParser;

public class ReadingCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	private CSVParser parser;

	public ReadingCountMapper() {

		parser = new CSVParser();

	}

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		
	}
}