package sys2202.hadoop.mapreduce.text;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		
		// sum up the values we receive
		int sum = 0;
		while (values.hasNext()) {
			
			sum += values.next().get();
		}
		
		// create the output value
		IntWritable value = new IntWritable(sum);
		
		// emit the output key-value pair
		output.collect(key, value);
	}
}