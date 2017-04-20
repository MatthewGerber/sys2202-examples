package sys2202.hadoop.mapreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class WordCount {

	public static void main(String[] args) throws Exception {

		// set up a new mapreduce job for submission
		JobConf conf = new JobConf(WordCount.class);
		conf.setJobName("wordcount");

		// tell mapreduce which mapper and reducer to use
		conf.setMapperClass(WordCountMapper.class);
		conf.setCombinerClass(WordCountReducer.class);
		conf.setReducerClass(WordCountReducer.class);

		// tell mapreduce where to find input and how to read it (as text)
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		conf.setInputFormat(TextInputFormat.class);

		// tell mapreduce where to write output and how to write it (as text)
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setOutputFormat(TextOutputFormat.class);

		// tell mapreduce about the key and value types we're going to output
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		// submit the job
		JobClient.runJob(conf);
	}
}