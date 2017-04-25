package sys2202.hadoop.mapreduce.text;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class WordCount {

	/* 
	 * An example MapReduce job that processes text files and counts the number
	 * of occurrences of each word.
	 */
	public static void main(String[] args) throws Exception {

		// set up a new mapreduce job for submission
		JobConf job = new JobConf(WordCount.class);
		job.setJobName("wordcount");
		
		// tell mapreduce where to find input and how to read it (as text)
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		job.setInputFormat(TextInputFormat.class);

		// tell mapreduce which mapper and reducer to use
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		// tell mapreduce about the key and value types we're going to output
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
				
		// tell mapreduce where to write output and how to write it (as text)
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setOutputFormat(TextOutputFormat.class);

		// submit the job
		JobClient.runJob(job);
	}
}