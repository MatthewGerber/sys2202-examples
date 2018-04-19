package sys2202.hadoop.mapreduce.text;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	/*
	 * This map method is called once per line of text. It breaks the line into words and emits 
	 * intermediate key-value pairs. 
	 */
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		
		// tokenize the input value
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		
		while (tokenizer.hasMoreTokens()) {
			
			// create the intermediate key
			Text word = new Text(tokenizer.nextToken());
			
			// create the intermediate value
			IntWritable one = new IntWritable(1);
			
			// emit the intermediate key-value pair
			output.collect(word, one);
		}
	}
}